package com.bensites.java.EasyCalc.GUI;

import com.bensites.java.EasyCalc.Main;

import javax.swing.*;
import java.awt.event.*;


public class MainGUI extends JDialog {
	private JPanel contentPane;
	private JTextField equationField;
	private JLabel Answer;

	public MainGUI(){
		setContentPane(contentPane);
		setModal(true);

		equationField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (Main.parser.run(Main.parser.stringToArray(equationField.getText())).toString() != "NaN")
					Answer.setText("= " +
							Main.parser.run(Main.parser.stringToArray(equationField.getText())).toString());
				else Answer.setText("Error!");
				if (equationField.getText().equals("")){
					Answer.setText("");
				}
			}
		});

// call dispose() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0);
			}
		});
	}

	public static void main(String[] args){
		MainGUI dialog = new MainGUI();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
