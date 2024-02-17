package com.app

import groovy.xml.*

def filePath = "src/com/app/data.xml"

//Method to read XML from a file
def readXml(filePath) {
	def file = new File(filePath)
	if (file.exists()) {
		return new XmlSlurper().parse(file)
	} else {
		println "File not found: $filePath"
	}
}
// Method to update a specific element in XML and write back to the file
def updateXml(filePath, elementToUpdate, newValue) {
	def file = new File(filePath)
	if (file.exists()) {
		def xmlData = new XmlSlurper().parse(file)
		
		xmlData.person."${elementToUpdate}" = newValue //modifying a field
		/*xmlData.person.appendNode{
		 * country('UK')
		 * }
		 */ //to append a node
		// xmlData.person."${elementToUpdate}".replaceNode{""} // to remove a node
		def writer = new FileWriter(file)
		writer.write(XmlUtil.serialize(xmlData))
		writer.close()
		
		println "Element '$elementToUpdate' updated to '$newValue' in XML file."
	} else {
		println "File not found: $filePath"
	}
}
// Read and parse the XML file
def xmlData = readXml(filePath)
if (xmlData) {
	// Update the 'city' element in XML
	updateXml(filePath, 'city', 'London')
	// Read and print the updated XML file
	println "Updated XML content:"
	def updatedXmlData = readXml(filePath)
	if (updatedXmlData) {
		println XmlUtil.serialize(updatedXmlData)
	} else {
		println "No content to display"
	}	
} else {
	println "No content to display"
	
}