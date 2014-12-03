package com.bensites.java.EasyCalc.Util


class Util {
    static void repeat(int times, Closure toDo){
        for (i in 1..times)
            toDo.run()
    }
}
