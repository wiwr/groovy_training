package com.app

import groovy.xml.*
//import java.io.File

def filePath = "src/MMS.xml"
def AllowedTagList = ["Map", "MapSettings", "MapAudit", "MapTrace", "WorkSpace.File", "Input", "Output", "Schema", "MapRule","objectset","objectrule"]

class ErrorList {
	def listOfErrors = []
	
	def add(String mapName, String errorDescription) {
		listOfErrors.add(['MapName': mapName, 'errorDescription': errorDescription])
	}
	
	def show() {
		if (listOfErrors.empty) {
			println("####### Status OK ###############")
		} else {
			println("##########  Errors:  ############")
			listOfErrors.each { line ->
				println(line)
			}
		}
	}
}

def readXml(filePath) {
    def xmlParser = new XmlParser()
    xmlParser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
	xmlParser.setFeature("http://xml.org/sax/features/external-general-entities", false)
	xmlParser.setFeature("http://xml.org/sax/features/external-parameter-entities", false)
	xmlParser.setProperty("http://javax.xml.XMLConstants/property/accessExternalDTD", "all")
	// Configure the parser to ignore DTD loading
	xmlParser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
	
    def file = new File(filePath)
    if (file.exists()) {
        try {
            return xmlParser.parse(file)
        } catch (Exception e) {
            println "Error parsing XML: ${e.message}"
            return null
        }
    } else {
        println "File not found: $filePath"
        return null
    }
}

def buildMapFromXml(node, AllowedTagList) {
    def returnMap = [:]
    if (node.attributes()) {
        node.attributes().each { attr ->
            returnMap[attr.key] = attr.value
        }
    }
    if (node.children()) {
        node.children().each { child ->
            if (child instanceof String) {
                if (child.trim()) {
                    if (returnMap instanceof List) {
                        returnMap << child.trim()
                    } else if (returnMap) {
                        returnMap = [returnMap, child.trim()]
                    } else {
                        returnMap = child.trim()
                    }
                }
            } else {
				if (child.name() in AllowedTagList) {
	                if (child.attributes() || child.children() || child.text()) {
	                    def childMap = buildMapFromXml(child, AllowedTagList)
	                    if (returnMap[child.name()] instanceof List) {
	                        returnMap[child.name()] << childMap
	                    } else if (returnMap[child.name()]) {
	                        returnMap[child.name()] = [returnMap[child.name()], childMap]
	                    } else {
	                        returnMap[child.name()] = childMap
	                    }
	                }
				}
            }
        }
    } else if (node.text()) {
        returnMap = node.text()
    }
    return returnMap
}

def validateMap(maps, errors) {
	maps.each { map ->
		map.value.each { mapOut ->
			def mapName =  mapOut.name
			//def mapAuditSwitch = mapOut.MapSettings.MapAudit.Switch
			//println mapOut.MapSettings
			chectIfIsOff(errors, mapOut.MapSettings.MapAudit.Switch, "MapAudit Switch", mapName)
			chectIfIsOff(errors, mapOut.MapSettings.MapTrace.Switch, "MapTrace Switch", mapName)
			checkNumberEquel(errors, mapOut.MapSettings."WorkSpace.File".PageSize, "64", "MapTrace Switch", mapName)
			checkNumberEquel(errors, mapOut.MapSettings."WorkSpace.File".PageCount, "8", "MapTrace Switch", mapName)
			checkNumberEquel(errors, mapOut.MapSettings."WorkSpace.File".DataSize, "64", "MapTrace Switch", mapName)
			println mapOut
			def inputs = mapOut.Input
			println "INPUT"
			println inputs.Schema.cardnumber
			println inputs.Schema.cardname
			def outputs = mapOut.Output
			
			outputs.each { output ->
				println "OUTPUT"
				println(output.Schema.cardnumber)
				println(output.Schema.cardname)
				def mapRules = output.MapRule
				if (mapRules instanceof List) {
					mapRules.each { mapRule ->
					println(mapRule.rulenumber)
					println(mapRule.objectset)
					println(mapRule.objectrule)
					}
				} else {
					println(mapRules.rulenumber)
					println(mapRules.objectset)
					println(mapRules.objectrule)
				}
			}
		}
//			if (mapOut.containsKey("MapSettings")) {
	//			def mapSettings = mapOut.MapSettings
		//		mapSettings.each { mapSettingsOut ->
					//println mapSettingsOut
	//			}
		//	}
		}
	}


def chectIfIsOff(errors, value, what, mapName) {
	if (value != "OFF") {
		errors.add(mapName, "For '${what}' value is not set to 'OFF'")
	}
}
def checkNumberEquel(errors, value, expected_value, what, mapName) {
	if (value != expected_value) {
		errors.add(mapName, "Value for '${what}' is '${value} but expected '${expected_value}'") 
	}
}

ErrorList errors = new ErrorList()

def xmlData = readXml(filePath)
//println xmlData.getClass()
if (xmlData) {
	def resultMap = buildMapFromXml(xmlData, AllowedTagList)
	println resultMap
	//errors.add("test", "test")
	validateMap(resultMap, errors)
	//println XmlUtil.serialize(xmlData)
} else {
	println "Failed to read XML data"
}
errors.show()