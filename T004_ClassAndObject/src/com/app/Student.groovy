package com.app

class Student {
	def studentID
	def studentName
	
	static void main(args) {
		// TODO Auto-generated method stub
		Student student = new Student()
		student.studentName = "James"
		student.studentID = 50
		println("Student ID is " + student.studentID)
		println("Student name is " + student.studentName)	
	}
}
