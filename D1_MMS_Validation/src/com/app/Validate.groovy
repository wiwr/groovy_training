package com.app

import groovy.xml.*
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import org.xml.sax.SAXException

def filePath = "src/MMS.xml"

def readXml(filePath) {
	try { 
		def factory = DocumentBuilderFactory.newInstance()
		factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
		def builder = factory.newDocumentBuilder()
	    def file = new File(filePath)
	    if (file.exists()) {
	        return new XmlSlurper().parse(file)
	    } else {
	        println "File not found: $filePath"
		}
	} catch (ParserConfigurationException | SAXException e) {
		println "Error parsing XML: ${e.message}"
		return null
	}

}

def xmlData = readXml(filePath)
if (xmlData) {
	println XmlUtil.serialize(xmlData)
}