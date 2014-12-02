package com.bensites.java.EasyCalc

class Operator {
	 Closure<Double> operation = {return 0.0}
	 String symbols = []
	 Operator(String[] Symbols, Closure<Double> Operation){
		 operation = Operation
		 symbols = Symbols
		 addTo(Main.Registry)
	 }
	 HashMap<String, Closure> addTo(HashMap<String, Closure> Registry) {
		 symbols.each {
			 Registry.put(it, operation)
		 }
		 Registry
	 }

}
