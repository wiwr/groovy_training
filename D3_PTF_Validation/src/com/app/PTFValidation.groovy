package com.app

class ErrorList {
    def listOfErrors = []
    
    def add(int lineNumber, String orgLine, String errorDescription) {
        listOfErrors.add(['lineNumber': lineNumber, 'orgLine': orgLine, 'errorDescription': errorDescription])
    }
    
    def show() {
        println("##########  Errors:  ############")
        listOfErrors.each { line ->
            println(line)
        }
    }
}

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
            bolFieldsOutput = true
            break
        case "Code Lists":
            currentSection = trimLine
            bolCodeLists = true
            break
        default:
            if (currentSection != null) {
                print intCountLine + " "
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
                        println("Fields Input")
                        break
                    case "Fields Output":
                        println("Fields Output")
                        break
                    case "Code Section":
                        println("Code Section")
                        codeSectionLines.add(['lineNumber': intCountLine, 'trim': trimLine, 'orgLine': orgLine])
                }
            }
    }
}

// Define errors before using it
ErrorList errors = new ErrorList()
errors.add(1, "test", "test")

// Define the function before using it
def checkIfTabsUsed(codeLines) {
    if (codeLines.orgLine.contains("\t")) {
        errors.add(codeLines.lineNumber, codeLines.orgLine, "Tab used")
    }
}

// Define the function before using it
def validateCodeSection(codeLines) {
    // where original line is required
    checkIfTabsUsed(codeLines)
}

codeSectionLines.each { mapLine ->
    validateCodeSection(mapLine)
}

errors.show()
