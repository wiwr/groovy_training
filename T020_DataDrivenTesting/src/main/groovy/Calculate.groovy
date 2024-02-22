package main.groovy

class Calculate {
	String calculateGrade(int marks) {
		if (marks>=60) {
			return "passed"
		}
		return "failed"
	}
}
