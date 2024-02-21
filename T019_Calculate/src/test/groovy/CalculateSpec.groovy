package test.groovy
import main.groovy.*
import spock.lang.*

class CalculateSpec extends Specification {
	def calculate() {
		given:
		Calculate calculate = new Calculate()
		
		when:
		def result = calculate.calculateGrade(70)
		def nextresult = calculate.calculateGrade(30)
		
		then:
		nextresult == 'failed'
		result == "passed"
	}


}
