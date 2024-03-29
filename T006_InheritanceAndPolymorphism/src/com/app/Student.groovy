package com.app

class Parent {
	def studentID = 100
	void display() {
		println("parent class")
	}
	def getStudentID() {
		return this.studentID
	}
}

class Child extends Parent {
	def studentID = 75
	def studentName = "Jake"
	void display() {
		super.display()
		super.studentID = 700
		println(studentName)
		println(super.studentID)
		println("child class")
	}
}

class Child3 extends Parent {
	void display() {
		println(super.studentID)
		super.studentID=1000
	}
}
class Student {
	static void main(args) {
		Parent parent = new Parent()
		Child child = new Child()
		Child2 child2 = new Child2()
		Child3 child3 = new Child3()
		child.display()
		child3.display()
		println(child3.getStudentID())
		child2.display()
	}
}
class Child2 extends Child {
	void display() {
		super.display()
	}
}
