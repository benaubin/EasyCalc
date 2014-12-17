package com.bensites.java.EasyCalc.GUI;

import com.bensites.java.EasyCalc.Main;

import javax.swing.*;
import java.awt.event.*;


public class MainGUI extends JDialog implements KeyListener {
	private JPanel contentPane;
	private JTextField equationField;
	private JLabel Answer;
	public void keyTyped(KeyEvent e) {
		String equation = equationField.getText() + e.getKeyChar();
		try {
			String answer = Main.parser.run(Main.parser.stringToArray(equation)).toString();
			if (!(answer.equals("NaN") || equationField.getText().equals("") || equation.equals("= ")))
				Answer.setText("= " + answer);
			else {
				Answer.setText("");
			}
		} catch(NullPointerException error) {
			Answer.setText("");
		}
	}
	public void keyPressed(KeyEvent e) {

	}
	public void keyReleased(KeyEvent e) {

	}


	public MainGUI(){

		setContentPane(contentPane);
		setModal(true);
		equationField.addKeyListener(this);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0);
			}
		});

	}
}
