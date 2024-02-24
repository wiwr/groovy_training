package com.app

import groovy.xml.*

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

def xmlData = readXml(filePath)
if (xmlData) {
	println XmlUtil.serialize(xmlData)
}