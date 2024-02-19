package com.app

import groovy.sql.Sql

// Define the connection details for PostgresSQL
def dbUrl = 'jdbc:postgresql://localhost:5432/testing'
def dbUser = 'student'
def dbPassword = 'student'

// Create a data source
def dataSource = Sql.newInstance(
	url: dbUrl, 
	user: dbUser,
	password: dbPassword,
	driver: 'org.postgresql.Driver'
)

// Execute a query
def query = "SELECT * FROM example"

 dataSource.execute("INSERT into example(name, age) values ('Przemek', 50)")
// dataSource.execute("DELETE FROM example where id = 13")
 dataSource.execute("UPDATE example SET name = 'BOND' where id = 15")
def results = dataSource.rows(query)
results.each{ row ->
	println("User: ${row.name}, Age: ${row.age}")
}