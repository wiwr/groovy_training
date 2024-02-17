package com.app

//@Grapes([
//		@Grab(group='org.codehaus.groovy',module='groovy-json',version='3.0.20'),
		//@GrabConfig( systemClassLoader=true)
//])
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
def filePath = "src/com/app/data.json"

def readJson(filePath) {
	def file = new File(filePath)
	if (file.exists()) {
		def jsonSlurper = new JsonSlurper()
		return jsonSlurper.parse(file)
	} else {
		println "File not found: $filePath"
		return null
	}
}

def updateJson(filePath, keyToUpdate, newValue) {
	def file = new File(filePath)
	if (file.exists()) {
		def jsonSlurper = new JsonSlurper()
		def jsonData = jsonSlurper.parse(file)
		
		jsonData[keyToUpdate] = newValue // Update the specific key
		//jsonData.remove("city") // removes a filed
		jsonData.put("country", "UK")
		def jsonOutput = new JsonOutput()
		//def updatedJson = jsonOutput.toJson(jsonData)
		def updatedJson = jsonOutput.prettyPrint(jsonOutput.toJson(jsonData))
		file.write(updatedJson)
		
		println "Key '$keyToUpdate' updated to '$newValue' in JSON file."
	} else {
		println "File not found: $filePath"
	}
}

updateJson(filePath, 'city', 'New York')

def jsonData = readJson(filePath)
if (jsonData) {
	for (entry in jsonData) {
		println(entry.key + ' ' + entry.value)
	}
} else {
	println("Nothing to display")
}