package test.groovy
import groovy.sql.Sql
import spock.lang.*

class DataBaseTestSpec extends Specification {
	def "test connection"() {
		given:
		def dbUrl = 'jdbc:postgresql://localhost:5432/testing'
		def dbUser = 'student'
		def dbPassword = 'student'
		
		when:
		def sql = Sql.newInstance(
				url: dbUrl,
				user: dbUser,
				password: dbPassword,
				driver: 'org.postgresql.Driver'
		)
		then:
		sql.getConnection() != null
	}
	
}
