package com.app

class Example {

	static void main(args) {
		// TODO Auto-generated method stub
		def closure = {
			print("Hello ${it}")
		}
		closure("Alex")
		def code = { name -> 
			name = 123
			//it = 100
			name = 99
			name = 293939
		}
		println(code("77"))
		def list = ["gRRoovy","ANSIBLE","Jenkins"]
		println("Entering transform")
		def transform = { word ->
			word[0].toUpperCase() + word[1..-1].toLowerCase()
		}
		def updateList = list.collect(transform) // collect is used for iteration
		println(updateList)
		
		def year = { year ->
			if (year %4 == 0) {
				println("Leap")
				year
			}
			else {
				println("Not leap")
				year
			}
		}
		println(year(2012))
	}
}
