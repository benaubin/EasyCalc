package com.bensites.java.EasyCalc.Console

class Input extends ConsoleMessage {
	final String input;
	final String message;

	public Input(Console belongsTo, Object Message) {
		super(belongsTo, Message)
		message = Message
		belongsTo.lock()
		print("$message> ")
		Scanner scan = new Scanner(System.in)
		input = scan.nextLine()
		scan.close()
		belongsTo.print((ConsoleMessage) this, true, false)
		belongsTo.unlock()
	}
	@Override
	String toString(){
		message + ": " + input
	}
}