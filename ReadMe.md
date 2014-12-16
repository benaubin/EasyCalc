EasyCalc
=======
######The modular calculator built for developers.

Usage
-------
To use, just start up the program, wait just a few seconds for it to load, and type in a problem. Easy Calc follows order of operations, so you don't have to think.

Core Operators
------------------
All operators in EasyCalc are modular, but in order to start you off, we give you the following operators listed below.

Operator| Function
----------	| ----------
*		| Multiplies _ by _
/		| Divides _ by _
+		| Adds _ to _
-		| Finds the differnce of _ and _

If you want, you can choose to add the operators below at the first run.

Operator	|Function
----------	|----------
rt		| Finds the _ root of _
^		| Finds _ to the power of _


Installing Operators
-----------------------
To install operators, it's pretty easy. You should have been given a folder of operators to install. If you do not have one, you might want to create your own. Scroll down this page to learn how. But, if you have one, just drag the file into your EasyCalc folder. After that, add the operators to your order.ecal file.

Contributing
--------------
By contributing, you are giving us your code under an MIT license. If you don't like that, don't code for us.
Other things to keep in mind:
- Keep your code short, efficient, & readable.
- Commit often while coding.
- Keep changes that might break the program in a separate branch, if you are a core developer
- Do not add core operators, instead, add operators to the recommended 

Modding EasyCalc
----------------------
Making operators is easy. All you have to do is write about 1 line of code.
First, open up your EasyCalc dirrectory, on a Mac it's at ~/Library/EasyCalc, and on all other devices, you will see it where you ran the program.

Create a new directory in the /mods/ folder. Name it the name of your mod. Then create a new file. Call it by the name you want it to run with ("+" for 1 + 1, or "addTo" for 6 addTo 3), and then .ecal (for a "add" operator, call it "add.ecal").

Then, open up the file in your favorite text editor (I reccomend Sumblime Text), and start coding Groovy. 'x' is the text on the left, and 'y' is the text on the right. If possable, they are already converted to a double, if not, they are strings. You should return a double, if possable. If there is an Error, just return NaN, and EasyCalc will know what you mean. All execption handling should be taken care of,. Any Groovy or Java will work.

Importing code will not work.

Example "add" operator:
~~~groovy
return x + y
~~~
