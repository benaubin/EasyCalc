package com.bensites.java.EasyCalc

class Console{
	def console = []
	def continueLine = false
	/**
	 * Print a message, followed by a new line
	 * @param objectToPrint Object to print
	 * @return A console message
	 * @see ConsoleMessage
	 * @author Ben
	 */
	ConsoleMessage println(Object objectToPrint) {
		ConsoleMessage toPrint = new ConsoleMessage(this, objectToPrint)
		print(toPrint,true,true)
	}
	/**
	 * Print a message
	 * @param objectToPrint Object to print
	 * @return A console message
	 * @see ConsoleMessage
	 * @author Ben
	 */
	ConsoleMessage print(Object objectToPrint) {
		ConsoleMessage toPrint = new ConsoleMessage(this, objectToPrint)
		print(toPrint,false,true)
	}
	/**
	 * More advanced version of printing
	 * @param toPrint A console message to print
	 * @param newLine If it should put a new line at the end of the text (similar to ln)
	 * @param update If it should update the console after printing the message (normally do yes)
	 * @return <b>toPrint</b> console message passed in. Useful for returning
	 * @author Ben  
	 */
	ConsoleMessage print(ConsoleMessage toPrint, boolean newLine, boolean update) {
		if(continueLine)
			console.set(console.size()-1,console.get(console.size()-1).combineWith(toPrint))
		else
			console.add(toPrint)
		continueLine = !newLine
		if(update) this.update()
		toPrint
	}
	void update() {
		for (i in 1..100)
			System.out.println()
		for (line in console)
			System.out.println line.getValue()
	}
}