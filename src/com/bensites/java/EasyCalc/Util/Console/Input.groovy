package com.bensites.java.EasyCalc.Util.Console

class Input extends ConsoleMessage {
	final String input;
	final String message;

	public Input(Console belongsTo, Object Message) {
		super(belongsTo, Message)
		message = Message
		belongsTo.lock()
		print("$message> ")
		input = belongsTo.scan.nextLine()
		belongsTo.print((ConsoleMessage) this, true, false)
		belongsTo.unlock()
	}
	@Override
	String toString(){
		message + ": " + input
	}
}