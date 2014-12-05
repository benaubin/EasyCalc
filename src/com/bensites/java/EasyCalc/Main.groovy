package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.Util.Console.*
/** Main class.
 * This class sets up dependencies, and imports the core operators.
 * It also stores the parser and registry for this runtime of the program.
 * @author Ben
 */
class Main {
	final static operatorsFile = new File("operators.ecal")
	final static orderFile = new File("order.ecal")
	static ArrayList<ArrayList<String>> order;
	static LinkedHashMap<String,Closure> Registry = [:]
	final static Console console = new Console()
	static shell = new GroovyShell()
	static Parser parser
	static main(args) {
		printTitle(console)
		console.println "Welcome to EasyCal"
		console.println "Written by everyone, supported by Ben of bensites.com"
		StartGUI dialog = new StartGUI()
		dialog.pack()
		dialog.setVisible(true)
		def loadingSteps = ["Filing files","Operating Operators","Finishing up","Loading complete"]
		def loadingBar = new ProgressBar(console, loadingSteps)
		console.print(loadingBar,true,true)
		parser = new Parser(console)

		//Make sure files exist.
		if(!operatorsFile.exists()){
			operatorsFile.createNewFile()
			operatorsFile.setText("""[
        "+":{ x, y ->
            x + y
        },
        "-":{ x, y ->
            x - y
        },
        "*":{ x, y ->
            x * y
        },
        "/":{ x, y ->
            x / y
        },
        "^":{ double x, double y ->
            java.lang.Math.pow(x, y)
        },
        "roundTo":{ double value, double places ->
            if (places < 0) throw new IllegalArgumentException();
            def s = new StringBuffer()
            if (places > 0){
                s.append(".")
                for(i in 1..places)
                    s.append("#")
            }
            com.bensites.java.EasyCalc.Main.println(s.toString())
            def df = new java.text.DecimalFormat("#" + s.toString())
            Double.valueOf(df.format(value))
        },
]""")
		}
		if(!orderFile.exists()){
			orderFile.createNewFile()
			orderFile.setText("""[
        ["^"],
        ["*","/"],
        ["+","-"],
        ["roundTo"]
]""")
		}

		loadingBar.progress()
		//Load all operators
		Registry = shell.evaluate(operatorsFile.getText())

		loadingBar.progress()
		//Do things like order operations, and finish up
		order = ((ArrayList<ArrayList<String>>)shell.evaluate(orderFile.getText()))
		loadingBar.progress()
		//Start the program
		while(true) {
			def Equation = new Input(console, "Equation")
			console.println("Answer: ${parser.run(parser.stringToArray(Equation.input))}")
		}
	}

	ConsoleMessage println(Object toPrint){
		console.println(toPrint)
	}
	ConsoleMessage print(Object toPrint){
		console.print(toPrint)
	}
	static void printTitle(Console console){
		console.println "  ______                 _____      _      "
		console.println " |  ____|               / ____|    | |     "
		console.println " | |__   __ _ ___ _   _| |     __ _| | ___ "
		console.println " |  __| / _` / __| | | | |    / _` | |/ __|"
		console.println " | |___| (_| \\__ \\ |_| | |___| (_| | | (__ "
		console.println " |_____|\\__,_|___/\\__, |\\_____\\__,_|_|\\___|"
		console.println "                   __/ |    "
		console.println "                  |___/   "
	}
	static void reload(){
		Registry = shell.evaluate(operatorsFile.getText())
		order = ((ArrayList<ArrayList<String>>)shell.evaluate(orderFile.getText()))
	}


}