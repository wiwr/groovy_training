package com.app

class Condition {
	static void main(args) {
		def num1 = 250
		def name = "sai"
		if(num1 == 150 && name == "sai") {
			println("num1 is equal to 150 and name is sai")
		}
		else if(num1 < 150 && name == "sai") {
			println("num1 is less then 150")
			println("Name is sai")
			println("Both match")
		}
		else {
			println("num1 is greater then 150")
			println("and maybe different or equal to sai")
		}
	}
}
