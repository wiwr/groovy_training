package com.app

class Example {

	static void main(args) {
		// TODO Auto-generated method stub
		def list1 = [1,2,3,4,5]
		println("The full list is " + list1)
		println("Index position 0 is " + list1[0])
		
		def list2 = [1,2,3,4,5,5,4,3,3,2,1]
		println("The full list2 is " + list2)
		println("Index position 4 is " + list2[4])
		
		def set1 = [1,2,2,2,2].toSet()
		println("The full list is " + set1)
		println("Index position 2 is " + set1[2])
		println("Index position 10 is " + list1[10])
		println("Index position -1 is " + list1[-1])
		println("Index position -5 is " + list1[-5])
		//println("Index position -6 is " + list1[-6]) //Negative array index [-6] too large for array size 5
		def dict1 = [
			"name": "Sai",
			"Name": "Jake"
		]
		println("The full dictionary is " + dict1)
		println(dict1.age)
		
		def list3=(2..10).toList()
		println(list3)
		def list4=(0..4).toList()
		println(list4)
		def result = [*list3, *list4].toSet().toList()
		println(result)
		def result2 = [*list3, *list4].toList()
		println(result2)
	}
}
