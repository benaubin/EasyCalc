package com.bensites.java.EasyCalc.Other

/**
 * Created by Ben on 2/2/15.
 */
enum ParserType {
    before("before"),
    after("after"),

    final type

    public ParserType(String Type){
        type = Type
    }
    @Override String toString(){
        type
    }
}
