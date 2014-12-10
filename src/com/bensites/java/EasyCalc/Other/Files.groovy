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
    public final static String Suggested = """
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
            def df = new java.text.DecimalFormat("#" + s.toString())
            Double.valueOf(df.format(value))
        },
        "rt":{ double root, double number ->
            if(root < 0){
                return Double.POSITIVE_INFINITY
            } else if (root == 1){
                return number
            } else if (root == 2){
                return java.lang.Math.sqrt(number)
            } else if (root == 3){
                return java.lang.Math.cbrt(number)
            } else {
                return Double.NaN
            }
        },
"""
}
