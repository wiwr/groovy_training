package test.groovy

import spock.lang.*

class SampleSpec extends Specification {
	def "testing"() {
		expect:
		1 + 1 == 2
		2 + 2 == 4
		"Test".toLowerCase() == "test"
	}
	
	

}
