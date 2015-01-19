package com.bensites.java.EasyCalc

import com.bensites.java.EasyCalc.GUI.*
import com.bensites.java.EasyCalc.Other.ECalMod
import com.bensites.java.EasyCalc.Other.Files
import com.bensites.java.EasyCalc.Util.Console.*

/** Main class.
 * This class sets up dependencies, and imports the core operators.
 * It also stores the parser and registry for this runtime of the program.
 * @author Ben
 */
class Main {
	/**
	 * Major,Minor,Fix
	 *
	 * Major: Noticable syntax changes, GUI changes, File Storage Changes, etc.
	 * Minor: Operator Additions
	 * Fix: Small fixes
	 *
	 * Major: Triggers a file deletion and reinstallation
	 * Minor: Asks user to trigger an operators installer
	 * Fix: Does nothing
	 */
	static final version = ["1", "2", "0"]
	static File easyCalcFolder = new File("EasyCalc/")
	static File operatorsFile
	static File orderFile
	static File modsFolder
	static File versionFile
	static ArrayList<ArrayList<String>> order = []
	static LinkedHashMap<String, Closure> Registry = [:]
	static LinkedHashMap<String, LinkedHashMap> meta = [:]
	static ArrayList<ECalMod> mods = []

	final static Console console = new Console()
	static shell = new GroovyShell()
	public static Parser parser

	static main(args) {
		console.println "Your OS:" + System.getProperty("os.name")
		if (System.getProperty("os.name") == "Mac OS X")
			easyCalcFolder = new File("${System.getProperty("user.home")}/Library/Application Support/EasyCalc")
		else console.println "EasyCalc isn't fully supported with your OS. Please paste this into a GitHub issue."
		if (!easyCalcFolder.exists()) easyCalcFolder.mkdirs()
		printTitle(console)
		console.println "Welcome to EasyCal"
		console.println "Written by Ben of bensites.com"
		def GUIThread = new Thread({
			MainGUI mainGUI = new MainGUI()
			mainGUI.pack()
			mainGUI.setVisible(true)
		}, "GUIthread")
		def loadingSteps = ["Filing files", "Operating Operators", "Finishing up", "Loading complete"]
		def loadingBar = new ProgressBar(console, loadingSteps)
		console.print(loadingBar, true, true)
		parser = new Parser(console)
		//Create files
		operatorsFile = new File(easyCalcFolder, "operators.ecal")
		orderFile = new File(easyCalcFolder, "order.ecal")
		modsFolder = new File(easyCalcFolder, "mods")
		versionFile = new File(easyCalcFolder, "version.ecal")
		if (!operatorsFile.exists()) {
			operatorsFile.createNewFile()
			operatorsFile.setText(Files.DefaultOperators)
		}
		if (!orderFile.exists()) {
			orderFile.createNewFile()
			orderFile.setText(Files.Order)
		}
		if (!modsFolder.exists()) {
			modsFolder.mkdirs()
		}
		loadingBar.progress()
		//Load all operators
		load()
		loadingBar.progress()
		if (updatedMinor()) {
			versionFile.createNewFile()
			versionFile.text = version.join(".")
			InstallOperators installOperators = new InstallOperators()
			installOperators.pack()
			installOperators.setVisible(true)
		}
		loadingBar.progress()
		//Start the program
		GUIThread.start()
	}

	ConsoleMessage println(Object toPrint) {
		console.println(toPrint)
	}

	ConsoleMessage print(Object toPrint) {
		console.print(toPrint)
	}

	static void printTitle(Console console) {
		console.println "  ______                 _____      _      "
		console.println " |  ____|               / ____|    | |     "
		console.println " | |__   __ _ ___ _   _| |     __ _| | ___ "
		console.println " |  __| / _` / __| | | | |    / _` | |/ __|"
		console.println " | |___| (_| \\__ \\ |_| | |___| (_| | | (__ "
		console.println " |_____|\\__,_|___/\\__, |\\_____\\__,_|_|\\___|"
		console.println "                   __/ |    "
		console.println "                  |___/   "
	}

	static void useSuggestedOperators() {
		addMod(Files.Suggested, "EasyCalc Addons")
	}

	static String runOperator(String argLeft, String operator, String argRight) {
		def answer
		try {
			answer = getRegistry()[operator](Double.parseDouble(argLeft), Double.parseDouble(argRight), meta.get(operator))
		} catch (NumberFormatException e) {
			answer = getRegistry()[operator](argLeft, argRight, meta.get(operator))
		}
		if (answer instanceof LinkedHashMap) {
			meta.put(operator, (LinkedHashMap) answer["meta"])
			return answer["answer"]
		} else
			return (String) answer
	}

	static void reload() {
		console.println("Reloading")
		load()
	}

	private static void load() {
		Registry = shell.evaluate("[" + operatorsFile.getText() + "]")
		order = (shell.evaluate("[\n${orderFile.getText()}\n]"))
		modsFolder.eachFileRecurse {
			if (it.isDirectory()) {
				new ECalMod(it)
			}
		}
	}

	public static void registerOperator(ECalMod mod, File operator) {
		shell.evaluate("{ x, y, meta ->" + operator.getText() + "}")
		meta.put(removeEcal(operator.name), [
				"file": operator, "mod": mod])
	}

	static String removeEcal(String fileName) {
		fileName.take(fileName.length() - 5)
	}

	static File addMod(LinkedHashMap operators, String name) {
		File modFolder = new File(modsFolder, name)
		modFolder.mkdirs()
		operators.each {
			File operator;
			if (it.key != "modinfo")
				operator = new File(modFolder, (String) it.key + ".ecal")
			else
				operator = new File(modFolder, (String) it.key)
			operator.createNewFile()
			operator.setText((String) it.value)
		}
		modFolder
	}

	static String getOrderFileText() {
		orderFile.getText()
	}

	static void setOrderFileText(String s) {
		orderFile.text = s
	}

	static boolean updatedMinor() {
		!versionFile.exists() || !versionFile.text.startsWith([version[0], version[1]].join("."))
	}

	static boolean updatedMajor() {
		null == versionFile || !versionFile.exists() || !versionFile.text.startsWith([version[0], version[1]].join("."))
	}

	static String version() {
		version.join(".")
	}
}