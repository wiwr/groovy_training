package test.groovy

import spock.lang.*
import main.groovy.*

class CalculatorServiceSpec extends Specification {
	def "test calculate method"() {
		given:
		def calculatorMock = Mock(Calculator)
		def calculatorService = new CalculatorService(calculator: calculatorMock)
	
		when:
		def result = calculatorService.calculate(2,3)
		
		then:
		1 * calculatorMock.add(2, 1) >> 5
		result == 5
	}
}
