package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.Util.Console.Console

class Parser {

	final Console console
	public Parser(Console c) {
		console = c
	}
	def stringToArray(String s){
		StringBuilder temp = new StringBuilder()
		def array = []
		s.toCharArray().each {
			if(it != ' ')
				temp.append it
			else{
				array.add temp.toString()
				temp = new StringBuilder()
			}
		}
		array.add temp.toString()
		return array
	}
	def run(ArrayList<String> equation) {
		boolean failed = false
		try {
			Main.order.each { level ->
				for (i in 0..equation.size() - 1)
					if (level.contains(equation[i])) {
						try {
							String answer = (String) Main.getRegistry()
									.get(equation[i])((Double.parseDouble(equation[i - 1])),
									Double.parseDouble(equation[i + 1]))
							for (a in 1..3) equation.remove(i - 1); equation.add(i - 1, answer);
						} catch (MissingMethodException e) {
							Main.reload() 1
						}

					}
			}

			if (equation.size() > 1)
				return run(equation)
			else
				return equation[0]
		} catch (StackOverflowError e) {
			Main.console.println("Something went wrong. Please try again.\nYou might have typed an operator wrong.")
			return Double.NaN
		} catch (Exception e){
			Main.console.println("Something went wrong. Please try again.\nPlease check your equation.")
			return Double.NaN
		}
	}

}
