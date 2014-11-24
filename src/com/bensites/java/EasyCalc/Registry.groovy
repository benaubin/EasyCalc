package com.bensites.java.EasyCalc

class Registry {

	private HashMap<String,Operator> Registry = [:]
	void register(String s, Operator o) {
		Registry.put(s,o)
	}
	void registry(String[] s, Operator o) {
		for(S in s) {
			register(S,o)
		}
	}
	Operator getByString(String s) {
		Registry.get(s)
	}

}
