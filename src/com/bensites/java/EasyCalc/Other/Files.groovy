package com.bensites.java.EasyCalc.Other


class Files {
    public final static String DefaultOperators = """
        "+":{ x, y, meta ->
            x + y
        },
        "-":{ x, y, meta ->
            x - y
        },
        "*":{ x, y, meta ->
            x * y
        },
        "/":{ x, y, meta ->
            x / y
        },
"""
    public final static String Order = """
        ["^","rt"],
        ["*","/"],
        ["+","-"],
        ["roundTo"]
"""
    public final static LinkedHashMap<String, String> Suggested = [
        "^":"""java.lang.Math.pow(x, y)""",
        "rt":"""if(x < 0){
                return Double.POSITIVE_INFINITY
            } else if (x == 1){
                return number
            } else if (x == 2){
                return java.lang.Math.sqrt(y)
            } else if (x == 3){
                return java.lang.Math.cbrt(y)
            } else {
                return Double.NaN
            }""",
    ]
}
