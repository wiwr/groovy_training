package test.groovy
import spock.lang.*
import main.groovy.*

class CalculatorServiceSpec extends Specification {

	def "test calculate method"() {
		given:
		def calculatorStub = Stub(Calculator)
		calculatorStub.add(_,_) >> 5 
		def calculatorService = new CalculatorService(calculator: calculatorStub)
		
		when:
		def result = calculatorService.calculate(2,3)
		
		then:
		result == 5
	}
}
