package com.app

class Example {

	static void main(args) {
		// TODO Auto-generated method stub
		
		def list = [5, 6, 7, 8]
		def listA = ['a','b','c', 'a','b','c']
		assert list.get(2) == 7 : "My message"
		assert list[2] == 7 : "My message two"
		
		[1,2,3].forEach {
			println("Item: $it")
		}
		['a','b','c'].eachWithIndex { it, i ->
			println "$i: $it"
		}
		
		assert [1,2,3].collectNested { it * 2 } == [2,4,6]
		assert [1,2,3]*.multiply(2) == [1,2,3].collectNested { it.multiply(2) }
		
		println(list.find { it > 7 })
		println(list.findAll { it > 6 })
		println(listA.findIndexOf { it in ['c', 'd'] })
		println(list.every { it < 6 })
		println(list.every {  it < 9 })
		println(list.any { it < 6 })
		println(list.sum())
		println(listA.sum())
		println(list.join('-'))
		println(listA.join('-'))
		println(list.min())
		println(list.max())
		
		def list2 = []
		println(list2)
		list2 << 5
		println(list2)
		list2 << 11 << 12 << 17
		println(list2)
		list2 << ['a', 'b']
		println(list2)
		list2.add(3)
		list2.addAll([50,60,70])
		println(list2)
		list2.remove(2)
		println(list2)
		list2.remove('17')
		println(list2)
		list2 << 54 << 34 << 44
		println(list2)
		
		println(listA)
		println(listA.sort())
		println(list2.sort())
		
		def map = [name: 'Gromit', likes: 'cheese', id: 1234]
		println(map.get('name'))
		println(map.get('id'))
		
		def emptyMap = [:]
		emptyMap.put("foo", 5)
		println(emptyMap)
		
		def mapMix = [
			simple: 123,
			complex: [a: 1, b: 3]	
		]
		println(mapMix)
		def mapNew = mapMix.clone()
		println(mapNew)
		println(mapNew.get('simple'))
		println(mapNew.get('complex'))
		mapNew.get('complex').put('c', 3)
		println(mapNew)
		
		mapNew.each { entry ->
			println "Name: $entry.key Age: $entry.value"
		}
		mapNew.eachWithIndex { entry, i ->
			println "$i - Name: $entry.key Age: $entry.value"
		}
		mapNew.each { key, value ->
			println "Name: $key Age: $value"
		}
		mapNew.eachWithIndex { key, value, i ->
			println "$i - Name: $key Age: $value"
		}
	}
}
