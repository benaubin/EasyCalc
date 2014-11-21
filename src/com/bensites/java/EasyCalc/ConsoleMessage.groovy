package com.bensites.java.EasyCalc;

class ConsoleMessage {

	final Console
	Object value
	String hi
	
	ConsoleMessage(Console belongsTo, Object message) {
		Console = belongsTo
		value = message
	}
	void setMessage(Object message){
		value = message
		Console.update()
	}
	void combineWith(ConsoleMessage message) {
		value = value + message.getValue()
	}
	void combineWith(Object message) {
		value = value + message
	}

}
