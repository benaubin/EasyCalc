package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.Console.*
/** Main class.
 * This class sets up dependencies, and imports the core operators.
 * It also stores the parser and registry for this runtime of the program.
 * @author Ben
 */
class Main {
	private HashMap<String,Closure> Registry = [:]
	final static Console console = new Console()
	static Parser parser
	static Main instance
	static main(args) {
		instance = new Main()
		console.println "  ______                 _____      _      "
		console.println " |  ____|               / ____|    | |     "
		console.println " | |__   __ _ ___ _   _| |     __ _| | ___ "
		console.println " |  __| / _` / __| | | | |    / _` | |/ __|"
		console.println " | |___| (_| \\__ \\ |_| | |___| (_| | | (__ "
		console.println " |_____|\\__,_|___/\\__, |\\_____\\__,_|_|\\___|"
		console.println "                   __/ |    "
		console.println "                  |___/   "
		console.println "Welcome to EasyCal"
		console.println "Written by everyone, supported by Ben of bensites.com"
		def loadingSteps = ["Registering Registries","Parsing Parsers","Operating Core Operators","Loading Mods","Finishing up","Loading complete"]
		def loadingBar = new ProgressBar(console, loadingSteps)
		console.print(loadingBar,true,true)
		loadingBar.progress()
		parser = new Parser(console)
		loadingBar.progress()
		//TODO: Load core operators
		loadingBar.progress()
		//TODO: Load mods
		loadingBar.progress()
		//TODO: Finish up
		loadingBar.progress()
		def ask = new Input(console, "Equation")

	}

	ConsoleMessage println(Object toPrint){
		console.println(toPrint)
	}
	ConsoleMessage print(Object toPrint){
		console.print(toPrint)
	}


}