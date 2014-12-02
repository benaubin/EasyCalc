package com.bensites.java.EasyCalc

class Operator {
	 Closure<Double> operation = {}
	 String symbols = []
	 Operator(String[] Symbols, Closure<Double> Operation){
		 operation = Operation
		 symbols = Symbols

	 }
	 HashMap<String, Closure> addTo(HashMap<String, Closure> Registry) {
		 symbols.each {
			 Registry.put(it, operation)
		 }
		 Registry
	 }

}
