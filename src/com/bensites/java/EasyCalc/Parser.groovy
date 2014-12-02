package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.Console.Console

class Parser {

	final Console console
	public Parser(Console c) {
		console = c
	}
	def parse(String s){
		StringBuilder temp = new StringBuilder()
		def equation = []
		s.toCharArray().each {
			if(it != ' ')
				temp.append it
			else{
				equation.add temp.toString()
				temp = new StringBuilder()
			}
		}
		run(equation)
	}
	def lookFor(Object something, Object[] list){
		def i = 0
		def where = []
		list.every {
			if(it == something)
				where.add(i)
			i++
		}
		if(where.size() == 0)
			return false
		else if(where.size() == 1)
			return where[0]
		else
			return where
	}
	def run(String[] equation){
		Main.Registry.get(equation[1])(equation[0],equation[2])
	}

}
