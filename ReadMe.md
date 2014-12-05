EasyCalc
=======
######The modular calculator built for developers.

Usage
-------
To use, just start up the program, wait just a few seconds for it to load, and type in a problem. Easy Calc follows order of operations, so you don't have to think.

Core Operators
------------------
All operators in EasyCalc are modular, but inorder to start you off, we give you the following operators listed below. If you want more, take the operators.ecal, and the order.ecal file from this repository, and add it to your download's runtime directory.

Operator| Function
----------	| ----------
^		| Finds _ to the power of _
*		| Multiplies _ by _
/		| Divides _ by _
+		| Adds _ to _
-		| Finds the differnce of _ and _
roundTo	| Rounds _ to _ decimals

Installing Operators
-----------------------
To install operators, it's pretty easy. You should have been given a snipet of code to install, looking something like this. If you do not have one, you might want to create your own. Scroll down this page to learn how.
~~~groovy
"potato":{ x, y ->
            y * 2 + x
        },
~~~
All you have to do, is open your operators.ecal file in the runtime directory of the program, and add the snipet to the bottom.
~~~groovy
[
        "+":{ x, y ->
            x + y
        },
        "-":{ x, y ->
            x - y
        },...
        "potato":{ x, y ->
            y * 2 + x
        },
]
~~~
If there is no comma at the end of that snipet, **add one**. That is very important, and might result in errors if not followed. Now, open up order.ecal.
~~~groovy
[
        ["^"],
        ["*","/"],
        ["+","-"],
        ["roundTo"]
]
~~~
In order to add an operation to run, you have to decide when you want it to run. If you want it to run during multiplication and division, place it in that list..
~~~groovy
[
        ["^"],
        ["*","/","potato"],
        ["+","-"],
        ["roundTo"]
]
~~~
So, you can test is out, and see that it works.
~~~json
Equation: 4 potato 7 + 1
Answer: 19.0
~~~


Contributing
--------------
By contributing, you are giving us your code under an MIT liscense. If you don't like that, don't code for us.
Other things to keep in mind:
- Keep your code short, efficient, & readable.
- Commit often
- Keep changes that might break the program in a seprate branch, if you are a core developer
- Do not add core operators, instead, add operators to the operators.ecal file.

Making Operators
---------------------
Making operators is easy. All you have to do is write about 4 lines of code.
Let's open Operators.ecal. You should see something that looks like this sample of code.
~~~groovy
[
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
        "^":{ double x, double y ->
            java.lang.Math.pow(x, y)
        },...
]
~~~
If you have every worked with Groovy before, you might reconise this. It is a simple HashMap, that will be interprated at runtime.
It's easy to make your own operator, just add this to the end of the file:
~~~groovy
 "potato":{ x, y ->
            
        },
~~~
You can notice that you are passed in two arguments, both of which are doubles, but groovy does not make you write that. Instead, you can just type *x,y ->*.
Let's make potato add y times 2 to x.
~~~groovy
 "potato":{ x, y ->
            y * 2 + x
        },
~~~
We just added  y * 2 + x. Groovy evaluates using Pemdas, so it's pretty easy to write one line of code. The final line of code is always returned in Groovy, so you don't have to type return, or one of those pesky simicolons. Now, in order for the program to run this operator in the right order, we need to add it to order.ecal. It looks something like this:
~~~groovy
[
        ["^"],
        ["*","/"],
        ["+","-"],
        ["roundTo"]
]
~~~
Order.ecal is basically an array of arrays of operators. During parsing, the operators in the first array is done from left to right, then the second, and so on. That means if we want our potato operator to evaluate first, then we can put it on top.
~~~groovy
[
	["potato"],
  	["^"],
  	["*","/"],
  	["+","-"],
  	["roundTo"]
]
~~~
Now, when we run the program, potato is evaluted first, so 7 potato 7 is 18.
~~~json
Equation: 4 potato 7
Answer: 18.0
~~~
That is correct, since 7 * 2 = 14, since is takes the second one, multiply's it by 2, and adds the first one. Now, let's try adding 1 to that.
~~~json
Equation: 4 potato 7 + 1
Answer: 19.0
~~~
That is also correct, since in the order of operations we made, potato goes before '+'.
You just made your own operator! Do what ever you want with it, sell it, all you have to do is make sure that your end user cannot only look at the code, but also edit, sell and redistribute it. Make sure to distribute it with a comma at the end, or it might break.