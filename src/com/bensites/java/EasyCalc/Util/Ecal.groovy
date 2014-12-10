package com.bensites.java.EasyCalc.Util

import com.bensites.java.EasyCalc.*

/**
 * Created by Ben on 12/9/14.
 */
class Ecal extends File{
    public Ecal(String name){
        super(Main.easyCalcFolder,name+".ecal")
    }

    void addTextToHash(String toAdd){
        StringBuilder toAppend = StringBuilder(toAdd)
        StringBuilder newFile = StringBuilder(toAdd)
        toAppend.deleteCharAt(0)
        newFile.append(toAppend)
    }
}
