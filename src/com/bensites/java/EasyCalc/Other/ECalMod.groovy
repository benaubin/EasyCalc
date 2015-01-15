package com.bensites.java.EasyCalc.Other

import com.bensites.java.EasyCalc.Main

/**
 * Created by Ben on 1/12/15.
 */
class ECalMod extends File{

    final String name
    final String url

    final String author
    final String authorLink

    public def config = [:]

    final String modHelp;

    final HashMap<String, HashMap<String, String>> operatorHelp = [:]

    ECalMod(File ModDir){
        super(ModDir.toURI())
        config = new ConfigSlurper().parse(new File(ModDir,'modinfo').toURI().toURL())
        name = config.mod.name
        author = config.mod.author.name
        authorLink = config.mod.author.homepage
        url = config.mod.url
        modHelp = config.help.mod

        Main.console.println("Loading $name by $author")
        (String[]) config.operators.each {
            Main.console.println("\tLoading: " + it)
            Main.registerOperator(this,new File(this, "${it}.ecal"))
            operatorHelp.put(it, config.help.operators.get(it))
        }
        Main.mods.add(this)

    }
    @Override
    String toString(){
        name
    }
    String getHelp(String operator){
        operatorHelp[operator]["Help"]
    }
    String getUsage(String operator){
        operatorHelp[operator]["Usage"]
    }
    String[] getOperators() {
        config.operators
    }
}