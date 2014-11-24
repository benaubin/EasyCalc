package com.bensites.java.EasyCalc.Console

class Input extends ConsoleMessage{

	public Input(Console belongsTo, Object message) {
		super(belongsTo, message)
		setMessage("$message>")

	}

}
