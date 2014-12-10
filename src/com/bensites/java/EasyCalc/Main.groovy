package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.GUI.InstallOperators
import com.bensites.java.EasyCalc.GUI.MainGUI
import com.bensites.java.EasyCalc.Other.Files
import com.bensites.java.EasyCalc.Util.Console.*

/** Main class.
 * This class sets up dependencies, and imports the core operators.
 * It also stores the parser and registry for this runtime of the program.
 * @author Ben
 */
class Main {

	static File easyCalcFolder = new File("EasyCalc/")
	static File operatorsFile
	static File orderFile
	static File modsFolder
	static ArrayList<ArrayList<String>> order;
	static LinkedHashMap<String,Closure> Registry = [:]
	static LinkedHashMap<String,LinkedHashMap> meta
	final static Console console = new Console()
	static shell = new GroovyShell()
	public static Parser parser
	static main(args) {
		console.println "Your OS:" + System.getProperty("os.name")
		if(System.getProperty("os.name") == "Mac OS X")
			easyCalcFolder = new File("${System.getProperty("user.home")}/Library/Application Support/EasyCalc")
		else console.println "EasyCalc isn't fully supported with your OS. Please paste this into a GitHub issue."
		if(!easyCalcFolder.exists()) easyCalcFolder.mkdirs()
		printTitle(console)
		console.println "Welcome to EasyCal"
		console.println "Written by Ben of bensites.com"
		def GUIThread = new Thread({
			MainGUI mainGUI = new MainGUI()
			mainGUI.pack()
			mainGUI.setVisible(true)
		},"GUIthread")
		def loadingSteps = ["Filing files","Operating Operators","Finishing up","Loading complete"]
		def loadingBar = new ProgressBar(console, loadingSteps)
		console.print(loadingBar,true,true)
		parser = new Parser(console)

		//Create files
		operatorsFile = new File(easyCalcFolder, "operators.ecal")
		orderFile = new File(easyCalcFolder, "order.ecal")
		modsFolder = new File(easyCalcFolder, "mods")
		if(!operatorsFile.exists()){
			operatorsFile.createNewFile()
			operatorsFile.setText(Files.DefaultOperators)
			InstallOperators installOperators = new InstallOperators()
			installOperators.pack()
			installOperators.setVisible(true)
		}
		if(!orderFile.exists()){
			orderFile.createNewFile()
			orderFile.setText(Files.Order)
		}
		if(!modsFolder.exists()){
			modsFolder.mkdirs()
		}
		loadingBar.progress()
		//Load all operators
		reload()
		loadingBar.progress()
		//Finish up
		loadingBar.progress()
		//Start the program
		GUIThread.start()
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

	static void useSuggestedOperators(){
		addMod(Files.Suggested, "EasyCalc Addons")
	}
	static String runOperator(String argLeft, String operator, String argRight){
		try {
			(String) getRegistry()[operator](Double.parseDouble(argLeft), Double.parseDouble(argRight),  meta.get(operator))
		}catch (NumberFormatException e){
			(String) getRegistry()[operator](argLeft, argRight, meta.get(operator))
		}
	}
	static void reload(){
		println("Reloading.")
		load()
	}
	private static void load(){
		Registry = shell.evaluate("["+operatorsFile.getText()+"]")
		order = (shell.evaluate("[\n${orderFile.getText()}\n]"))
		modsFolder.eachFileRecurse {
			if(it.isDirectory()){
				console.println "Loading mod ${it.name}"
				it.eachFileRecurse { modFile ->
					if (modFile.name.endsWith(".ecal")) {
						console.println("	Found Operator: " + modFile.name.take(modFile.name.length() - 5))
						Registry.put(
								(modFile.name.take(modFile.name.length() - 5).toString()),
								(Closure<String>) shell.evaluate("{ x, y, meta ->"+modFile.getText()+"}"))
						meta.put(modFile.name.take(modFile.name.length() - 5).toString(),[
								"file":modFile, "mod":it])
					}
				}
			}
		}
	}
	static File addMod(LinkedHashMap operators, String name){
		File modFolder = new File(modsFolder, name)
		modFolder.mkdirs()
		operators.each {
			def operator = new File(modFolder, (String) it.key + ".ecal")
			operator.createNewFile()
			operator.setText((String)it.value)
		}
		modFolder
	}

}