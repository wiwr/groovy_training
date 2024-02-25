package com.app

import groovy.xml.*
import java.io.File

def filePath = "src/MMS.xml"

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

def buildMapFromXml(xmlData) {
	def resultMap = [:]
	if (xmlData instanceof groovy.util.Node) {
		resultMap['root'] = xmlData.name()
		xmlData.children().each { child -> 
			println "Child: $child - Type: ${child.getClass()}"
			if (child instanceof groovy.util.Node) {
				resultMap[child.name()] = buildMapFromXml(child)
			} else if (child instanceof String){
				resultMap['value'] = child
			}
		}
	} else {
		println "XML data is not of type groovy.util.Node"
	}
	return resultMap
}

def xmlData = readXml(filePath)
println xmlData.getClass()
if (xmlData) {
	def resultMap = buildMapFromXml(xmlData)
	//println resultMap
	//println XmlUtil.serialize(xmlData)
} else {
	println "Failed to read XML data"
}