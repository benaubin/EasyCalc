package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.Util.Console.Console
class Parser {

	final Console console
	public Parser(Console c) {
		console = c
	}
	ArrayList<String> stringToArray(String s){
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
	def containsGrouping(ArrayList<String> equation){
		boolean containsGrouping = false
		for(it in equation) {
			if(it.toString().startsWith("(") || it.toString().endsWith(")")) containsGrouping = true
		}
		containsGrouping
	}
	def removeFromString(int index, String string){
		def t = new StringBuffer()
		t.append(string)
		if(index != -1)
			t.deleteCharAt(index)
		else
			t.deleteCharAt(t.length() - 1)
		t.toString()
	}
	def run(ArrayList<String> equation) {
		if (containsGrouping(equation)) {
			for (i in 0..(equation.size() - 1)) {
				def it = equation[i]
				int openGroups
				int openIndex
				int closeIndex
				if (it.startsWith("(")) {
					openGroups++
					if (!openIndex) openIndex = i
				} else if (it.endsWith(")")) {
					if (openGroups == 1) {
						if (!closeIndex) closeIndex = i
					}
						openGroups--
				}
			}
			
		} else {
			try {
				Main.order.each { level ->
					for (i in 0..equation.size() - 1)
						if (level.contains(equation[i])) {
							def answer = Main.runOperator(equation[i - 1],equation[i],equation[i + 1])
							for (a in 1..3) equation.remove(i - 1); equation.add(i - 1, answer);
						}
				}

				if (equation.size() > 1)
					return run(equation)
				else
					return equation[0]
			} catch (Exception e) {
				return Double.NaN
			} catch (StackOverflowError e){
				return Double.NaN
			}
		}
	}
}
