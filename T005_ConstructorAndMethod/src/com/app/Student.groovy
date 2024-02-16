package com.app

class Student {
	private int studentID
	private String studentName
	
	Student(int ID, String Name) {
		this.studentID = ID
		this.studentName = Name
	}
	
	void setstudentID(int ID) {
		this.studentID = ID
	}
	
	void setstudentName(String Name) {
		this.studentName = Name
	}
	
	int getstudentID() {
		return this.studentID
	}
	
	String getstudentName() {
		return this.studentName
	}
	
	static void main(args) {
		// TODO Auto-generated method stub
		Student student = new Student(40,"Jones")
		println(student.getstudentID())
		println(student.getstudentName())
		student.setstudentID(77)
		student.setstudentName("John")
		println(student.getstudentID())
		println(student.getstudentName())
	}
}
