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
                new File(System.getProperty("user.home")+"/Library/Application Support/EasyCalc/mods/EasyCalc Addons/")
        println("public final static LinkedHashMap<String, String> Suggested = [")
        addonsFolder.eachFile {
            println("\""+Main.removeEcal(it.name) + "\":\"\"\"" + it.text + "\"\"\",")
        }
        println("]")
        println("""

""")
        println("public final static String Order = \"\"\"")
        println(new File(System.getProperty("user.home")+ "/Library/Application Support/EasyCalc/order.ecal").text)
        println("\"\"\"")
        println("""


""")
        println("""public final static String modinfo = \"\"\"""")
        println((new File(addonsFolder, "modinfo")).text)
        println("""\"\"\"""")
    }

    //Copy paste below before a release
    public final static LinkedHashMap<String, String> Suggested = [
            "%in":"""return x * 100 / y""",
            "^":"""java.lang.Math.pow(x, y)""",
            "PPMin":"""return x * 1000000 / y""",
            "rt":"""java.lang.Math.pow(x, 1 / y)""",
    ]



    public final static String Order = """
["^","rt"],
["*","/"],
["+","-"],
["Other"]
"""
}
