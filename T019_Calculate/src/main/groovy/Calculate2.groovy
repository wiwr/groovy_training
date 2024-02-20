package main.groovy

class Calculate2 {
	String calculateGrade(int marks) {
		if (marks>=60) {
			return "passed"
		}
		return "failed"
	}
}