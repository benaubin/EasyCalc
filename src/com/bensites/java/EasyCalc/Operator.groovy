package com.bensites.java.EasyCalc

class Operator {
	 Closure<Double> operation = {return 0.0}
	 String symbols = []
	 Operator(ArrayList<String> Symbols, Closure<Double> Operation){
		 operation = Operation
		 symbols = Symbols
		 addTo(Main.Registry)
	 }
	 Operator(String Symbol, Closure<Double> Operation){
		 this([Symbol],Operation)
	 }
	 HashMap<String, Closure> addTo(HashMap<String, Closure> Registry) {
		 symbols.each {
			 Registry.put(it, operation)
		 }
		 Registry
	 }

}
