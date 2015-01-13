package com.bensites.java.EasyCalc.GUI;

import com.bensites.java.EasyCalc.Main;

import javax.swing.*;
import java.awt.event.*;


public class MainGUI extends JDialog implements KeyListener {
	private JPanel contentPane;
	private JTextField equationField;
	private JLabel Answer;
	private JButton optionsButton;

	public void keyTyped(KeyEvent e) {

	}
	public void keyPressed(KeyEvent e) {

	}
	public void keyReleased(KeyEvent e) {
		String equation = equationField.getText();
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


	public MainGUI(){
		setTitle("EasyCalc v" + Main.version());
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
