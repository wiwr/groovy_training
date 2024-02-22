package main.groovy

interface Calculator {
	int add(int a, int b)
}

class CalculatorService {
	Calculator calculator
	int calculate(int a, int b) {
		return calculator.add(a,b)
	}
}