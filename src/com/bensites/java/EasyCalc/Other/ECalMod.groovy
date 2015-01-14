package com.bensites.java.EasyCalc.Other

import com.bensites.java.EasyCalc.Main

/**
 * Created by Ben on 1/12/15.
 */
class ECalMod extends File{

    final String name

    final String author
    final String authorLink

    def config

    ECalMod(File ModDir){
        super(ModDir.toURI())
        config = new ConfigSlurper().parse(new File(ModDir,'modinfo').toURI().toURL())
        name = config.mod.name
        author = config.mod.author.name
        authorLink = config.mod.author.homepage
        Main.console.println("Loading $name by $author")
        (String[]) config.operators.each {
            Main.console.println("\tLoading: " + it)
            Main.registerOperator(this,new File(this, "${it}.ecal"))
        }
    }
}