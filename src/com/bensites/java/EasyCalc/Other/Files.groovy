package com.bensites.java.EasyCalc.Other

import com.bensites.java.EasyCalc.Main


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

    static main(args) {
        File addonsFolder =
                new File(System.getProperty("user.home") + "/Library/Application Support/EasyCalc/mods/EasyCalc Addons/")
        println("public final static LinkedHashMap<String, String> Suggested = [")
        addonsFolder.eachFile {
            if (it.name == "modinfo")
                println("\"" + it.name + "\":\"\"\"" + it.text + "\"\"\",")
            else
                println("\"" + Main.removeEcal(it.name) + "\":\"\"\"" + it.text + "\"\"\",")
        }
        println("]")
        println("""

""")
        println("public final static String Order = \"\"\"")
        println(new File(System.getProperty("user.home") + "/Library/Application Support/EasyCalc/order.ecal").text)
        println("\"\"\"")
        println("""


""")
    }

    //Copy paste below before a release
    public final static LinkedHashMap<String, String> Suggested = [
            "%in"    : """return x * 100 / y""",
            "^"      : """java.lang.Math.pow(x, y)""",
            "modinfo": """mod {
    author {
        name = "Ben"
        homepage = "bensites.com"
    }
    name = "EasyCalc Addons"
    url = "bsite.cc/calc"
}
operators = [
    "%in","^","PPMin","rt"
]
help {
    mod = "A few add-ons installed with EasyCalc."
    operators = [
        "%in": [Usage: "x %in y", Help: "calculates the percent of x in y, if they were mixed."],
        "^": [Usage: "x ^ y", Help: "calculates x to the power of y."],
        "PPMin": [Usage: "x PPMin y", Help: "calculates the parts of x per million y, if they were mixed."],
        "rt": [Usage: "x rt y", Help: "calculates x to the root of y."]
    ]
}""",
            "PPMin"  : """return x * 1000000 / y""",
            "rt"     : """java.lang.Math.pow(x, 1 / y)""",
    ]


    public final static String Order = """

["^","rt"],
["*","/"],
["+","-"],
["Other"]

"""


}