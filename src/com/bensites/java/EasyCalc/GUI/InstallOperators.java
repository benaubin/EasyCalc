package com.bensites.java.EasyCalc.GUI;

import com.bensites.java.EasyCalc.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class InstallOperators extends JDialog {
	private JPanel contentPane;
	private JButton buttonNO;
	private JButton buttonYES;
	private JLabel question;

	public InstallOperators(){
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonYES);
		buttonYES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Main.useSuggestedOperators();
				buttonNO.setVisible(false);
				buttonYES.setVisible(true);
				question.setText("Please relaunch EasyCalc");
				try {
					Thread.sleep(5000);
				} catch(Exception ex){
				}
				System.exit(0);
			}
		});
		buttonNO.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
	}

}
