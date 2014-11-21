package com.bensites.java.EasyCalc

class Main {
	final static console = new Console()
	static main(args) {
		console.println "  ______                 _____      _      "
		console.println " |  ____|               / ____|    | |     "
		console.println " | |__   __ _ ___ _   _| |     __ _| | ___ "
		console.println " |  __| / _` / __| | | | |    / _` | |/ __|"
		console.println " | |___| (_| \\__ \\ |_| | |___| (_| | | (__ "
		console.println " |_____|\\__,_|___/\\__, |\\_____\\__,_|_|\\___|"
		console.println "                   __/ |    "               
		console.println "                  |___/   "
		console.println "Welcome to EasyCal By Ben of bensites.com"
		def pieSteps = ["Baking Pie","Preparing Pie","Eating Pie","Cleaning Dishes","Pie is Done"]
		def loadingBar = new ProgressBar(console, pieSteps)
		console.print(loadingBar,true,true)
		for(i in pieSteps) {
			sleep(1000)
			loadingBar.progress()
		}
	}

}
