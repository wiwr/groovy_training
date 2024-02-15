package com.app

class Switch {

	static void main(args) {
		// TODO Auto-generated method stub
		def name = 20.00
		switch(name) {
			case "Sherlock":
				println("Watson")
				break
			case "Bonnie":
				println("Clyde")
				break
			case "Jekyll":
				println("Hyde")
				break
			case 20:
				println("20 == 20")
				break
			default:
				println("No conditions matched")
				break
		}
	}
}
