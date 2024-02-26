package com.app

import groovy.xml.*
//import java.io.File

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

def buildMapFromXml(node) {
    def map = [:]
    if (node.attributes()) {
        node.attributes().each { attr ->
            map['@' + attr.key] = attr.value
        }
    }
    if (node.children()) {
        node.children().each { child ->
            if (child instanceof String) {
                map = child
            } else {
                if (child.children().size() > 1 || child.attributes() || child.text()) {
                    map[child.name()] = buildMapFromXml(child)
                } else {
                    if (map[child.name()]) {
                        if (map[child.name()] instanceof List) {
                            map[child.name()] << child.text()
                        } else {
                            map[child.name()] = [map[child.name()], child.text()]
                        }
                    } else {
                        map[child.name()] = child.text()
                    }
                }
            }
        }
    } else if (node.text()) {
        map = node.text()
    }
    return map
}

def xmlData = readXml(filePath)
println xmlData.getClass()
if (xmlData) {
	def resultMap = buildMapFromXml(xmlData)
	println resultMap
	//println XmlUtil.serialize(xmlData)
} else {
	println "Failed to read XML data"
}