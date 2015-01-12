package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.Util.Console.Console
class Parser {

	final Console console

	public Parser(Console c) {
		console = c
	}

	static ArrayList<String> stringToArray(String s) {
		StringBuilder temp = new StringBuilder()
		def array = []
		s.toCharArray().each {
			if (it != ' ')
				temp.append it
			else {
				array.add temp.toString()
				temp = new StringBuilder()
			}
		}
		array.add temp.toString()
		return array
	}

	def run(ArrayList<String> equation) {
		try {
			Main.order.each { level ->
				for (i in 0..equation.size() - 1)
					if ((level.contains(equation[i])) ||
							(level.contains("Other") && Main.getRegistry().containsKey(equation[i]))) {
						def answer = Main.runOperator(equation[i - 1], equation[i], equation[i + 1])
						for (a in 1..3) equation.remove(i - 1); equation.add(i - 1, answer);
					}
			}
			if (equation.size() > 1)
				return run(equation)
			else
				return equation[0]
		} catch (Exception e) {
			return Double.NaN
		} catch (StackOverflowError e) {
			return Double.NaN
		}
	}
}
