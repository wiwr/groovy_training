package com.app

class ErrorList {
    def listOfErrors = []
    
    def add(int lineNumber, String orgLine, String errorDescription) {
        listOfErrors.add(['lineNumber': lineNumber, 'orgLine': orgLine, 'errorDescription': errorDescription])
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

class FieldsList {
    def listOfFields = []
    	
	def add(int lineNumber, String Side, String Name, String Type, String Format) {
		listOfFields.add(['lineNumber': lineNumber, 'side': Side, 'name': Name, 'type': Type, 'format': Format])
	}
	
	def show() {
		if (listOfFields.empty) {
			println("### List of Fields is empty ###")
		} else {
			println("####### List of Fileds ########")
			listOfFields.each { line ->
				println(line)
			}
		}
	}
	
	def fullList() {
		return listOfFields
	}
}

def validateFields(fieldsList, errors) {
	def list = fieldsList.fullList()
	list.each { line ->
		switch (line.type) {
			case "String":
				if (line.format != "Free Format" && line.format != "" ) {
					errors.add(line.lineNumber, line.name, "String filed is not Free Format")
				}
				break
			case "Integer":
				if (line.format == "") {
					errors.add(line.lineNumber, line.name, "Integer filed is without format")
				}
				break
			case "Date/Time":
				if (line.format == "") {
					errors.add(line.lineNumber, line.name, "Date/Time filed is without format")
				}
				break
		}	
	}
}

def validateCodeSection(codeLines, errors) {
	checkIfTabsUsed(codeLines,errors)
	checkKeywordsLowercase(codeLines, errors)
}

def checkIfTabsUsed(codeLines, errors) {
	if (codeLines.orgLine.contains("\t")) {
		errors.add(codeLines.lineNumber, codeLines.orgLine, "Tab used")
	}
}

def checkKeywordsLowercase(codeLines, errors) {
	def KeywordsList = ["if", "begin", "end", "else if", "while", "string", "real", "integer", "object", "dateti", "me", "break", "continue", "delete", "empty", "messagebox", "select", "sort", "strdate", "unreadblock", "update", "writeblock", "else", "accum", "atoi", "aton", "concat", "count", "date", "eof", "exist", "left", "len", "mid", "ntoa", "readblock", "right", "strstr", "sumtotal", "trim", "trimleft", "trimright", "then", "sum"]
	KeywordsList.each { keyword ->
		def pattern = ~/(?i)\b${keyword}\b/
		def quoteOut = ~/"(.*?)"/
		def matches = codeLines.trim.replaceAll(quoteOut, '""').findAll(pattern)
		def occurrence = 0
		matches.each {
			occurrence++
			if (it != keyword ) {
				errors.add(codeLines.lineNumber, codeLines.orgLine, "Keyword '${keyword}'is not lowercase (occurrence: ${occurrence})")
			}
		}
	}
}


ErrorList errors = new ErrorList()
FieldsList fieldsList = new FieldsList()

def inputFile = new File('src/com/app/PTF.txt')
def codeSectionLines = []
def currentSection = null
def intCountLine = 0
def bolGeneralSection = false
def bolBranchInput = false
def bolBranchOutput = false
def bolFieldsInput = false
def bolFieldsOutput = false
def bolCodeSection = false
def bolCodeLists = false

inputFile.eachLine { line ->
    def orgLine = line
    def trimLine = line.trim()
    intCountLine++
    
    switch(trimLine) {
        case "General Section":
            currentSection = trimLine
            bolGeneralSection = true
            break
        case "Branch Input":
            currentSection = trimLine
            bolBranchInput = true
            break
        case "Branch Output":
            currentSection = trimLine
            bolBranchOutput = true
            break
        case "Fields Input":
            currentSection = trimLine
            bolFieldsInput = true
            break
        case "Fields Output":
            currentSection = trimLine
            bolFieldsOutput = true
            break
        case "Code Section":
            currentSection = trimLine
            bolCodeSection = true
            break
        case "Code Lists":
            currentSection = trimLine
            bolCodeLists = true
            break
        default:
            if (currentSection != null) {
                switch (currentSection) {
                    case "General Section":
                        println("General Section")
                        break
                    case "Branch Input":
                        println("Branch Input")
                        break
                    case "Branch Output":
                        println("Branch Output")
                        break
                    case "Fields Input":
						if (orgLine.length() > 37) {
							fieldsList.add(intCountLine, "Input", orgLine.substring(2,22).trim(), orgLine.substring(22,36).trim(), orgLine.substring(36).trim())
						}
						break
                    case "Fields Output":
						if (orgLine.length() > 37) {
							fieldsList.add(intCountLine, "Output", orgLine.substring(2,22).trim(), orgLine.substring(22,36).trim(), orgLine.substring(36).trim())
						}
                        break
                    case "Code Section":
                        codeSectionLines.add(['lineNumber': intCountLine, 'trim': trimLine, 'orgLine': orgLine])
                        break
                    case "Code Lists":
                        println("Code List")
                        break
                }
            }
    }
}

if (!bolGeneralSection) {
	errors.add(0, "GeneralSection", "Missing General Section")
}
if (!bolBranchInput) {
	errors.add(0, "BranchInput", "Missing Branch Input")
}
if (!bolBranchOutput) {
	errors.add(0, "BranchOutput", "Missing Branch Output")
}
if (!bolFieldsInput) { 
	errors.add(0, "FieldsInput", "Missing Fields Input")
}
if (!bolFieldsOutput) {
	errors.add(0, "FieldsOutput", "Missing Fields Output")
}	
if (!bolCodeSection) {
	errors.add(0, "CodeSection", "Missing Code Section")
}
if (!bolCodeLists) {
	errors.add(0, "CodeLists", "Missing Code Lists")
}

validateFields(fieldsList, errors)

codeSectionLines.each { mapLine ->
	validateCodeSection(mapLine, errors)
}

errors.show()
