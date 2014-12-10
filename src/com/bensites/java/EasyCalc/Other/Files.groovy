package com.bensites.java.EasyCalc.Other


class Files {
    public final static String DefaultOperators = """
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
"""
    public final static String Order = """
        ["^","rt"],
        ["*","/"],
        ["+","-"],
        ["roundTo"]
"""
    public final static LinkedHashMap<String, String> Suggested = [
        "^":"""java.lang.Math.pow(x, y)""",
        "roundTo":"""if (y < 0) throw new IllegalArgumentException();
            def s = new StringBuffer()
            if (y > 0){
                s.append(".")
                for(i in 1..y)
                    s.append("#")
            }
            def df = new java.text.DecimalFormat("#" + s.toString())
            Double.valueOf(df.format(x))""",
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
