package com.app

def readFile(filePath) {
	def file = new File(filePath)
	if (file.exists()) {
		return file.readLines()
	} else {
		println "File not found: $filePath"
	}
}

def filePath = "src/com/app/data.txt"

def names = readFile(filePath)
if (names) {
	println "Names and their lengths:"
	names.each { name ->
		println "$name - ${name.length()}"
	}
} else {
	println "No content to display"
}