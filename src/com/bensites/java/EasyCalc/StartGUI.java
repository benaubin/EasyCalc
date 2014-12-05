package com.bensites.java.EasyCalc;

import javax.swing.*;
import java.awt.event.*;


public class StartGUI extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;

	public StartGUI(){
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				onOK();
			}
		});

// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();System.exit(0);
			}
		});
	}

	private void onOK(){
// add your code here
		dispose();
	}
}
