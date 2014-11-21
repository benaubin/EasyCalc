package com.bensites.java.EasyCalc

import java.text.DecimalFormat

class ProgressBar extends ConsoleMessage{
	
	int stepsLeft
	def steps
	int percentLeft
	String finishMessage
	
	ProgressBar(Console belongsTo, ArrayList<String> Steps) {
		super(belongsTo, "")
		finishMessage = Steps.get(Steps.size()-1)
		Steps.remove(Steps.size()-1)
		stepsLeft = Steps.size()
		steps = Steps
		percentLeft = 100
		update()
	}
	void progress() {
		if(stepsLeft > 0) {
			int nextStepsLeft = stepsLeft - 1
			percentLeft = percentLeft - (100 - ((nextStepsLeft / stepsLeft)*100))
			stepsLeft = nextStepsLeft
			
		} else 
			stepsLeft = 0
		update()
	}
	void update() {
		if(stepsLeft != 0) {
			int pDone = 100 - percentLeft
			if(pDone > 100) {
				pDone = 100
				percentLeft = 0
			}
			String pBar = ""
			if(pDone > 0) {
				for(i in 1..pDone)
					pBar = pBar + "#"
			}
			if (percentLeft > 0) {
				for(i in 1..percentLeft)
					pBar = pBar + " "
			}
			this.setMessage "[$pBar] $pDone% ${steps[steps.size() - stepsLeft]}"
		} else
			this.setMessage finishMessage
	}
}
