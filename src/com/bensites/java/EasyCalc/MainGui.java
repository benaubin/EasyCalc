package com.bensites.java.EasyCalc;

import javax.swing.*;
import java.awt.event.*;


public class MainGUI extends JDialog {
	private JPanel contentPane;
	private JButton buttonCalculate;
	private JTextField equationField;
	private JLabel Answer;

	public MainGUI(){
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonCalculate);

		buttonCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				equationField.setText(equationField.getText() + " = " + Main.parser.run(Main.parser.stringToArray(equationField.getText())).toString());
			}
		});



// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				onCancel();
			}
		});

// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK(){
// add your code here
		dispose();
	}

	private void onCancel(){
// add your code here if necessary
		dispose();
	}

	public static void main(String[] args){
		MainGUI dialog = new MainGUI();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
