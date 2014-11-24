package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.Console.Console
import com.bensites.java.EasyCalc.Console.ConsoleMessage
import com.bensites.java.EasyCalc.Console.ProgressBar
/** Main class.
 * This class sets up dependencies, and imports the core operators.
 * It also stores the parser and registry for this runtime of the program.
 * @author Ben
 */
class Main {
	final static Console console = new Console()
	static Registry registry
	static Parser parser
	static main(args) {
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
		console.println "Copyright 2014 BenSites. Released on the terms of an MIT license"
		def loadingSteps = ["Registering Registries","Parsing Parsers","Operating Core Operators","Loading Mods","Finishing up","Loading complete"]
		def loadingBar = new ProgressBar(console, loadingSteps)
		console.print(loadingBar,true,true)
		registry = new Registry()
		loadingBar.progress()
		parser = new Parser(registry, console)
		loadingBar.progress()
		//TODO: Load core operators
		loadingBar.progress()
		//TODO: Load mods
		loadingBar.progress()
		//TODO: Finish up
		loadingBar.progress()

		
	}

}