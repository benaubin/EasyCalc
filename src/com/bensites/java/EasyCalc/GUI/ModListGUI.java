package com.bensites.java.EasyCalc.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import com.bensites.java.EasyCalc.Main;
import com.bensites.java.EasyCalc.Other.ECalMod;


public class ModListGUI extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JList GUIModList;
	private JList Operators;
	private JLabel modInfo;
	private JLabel links;
	private JLabel Operator;
	private JLabel Operator2;

	private MainGUI MainGUI;

	private ECalMod selectedMod;

	ECalMod getMod(int i){
		return (ECalMod) Main.getMods().toArray()[i];
	}
	public ModListGUI(MainGUI mainGUI){
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		Operators.setVisible(false);
		MainGUI = mainGUI;
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				onOK();
			}
		});
		GUIModList.setListData(Main.getMods().toArray());
		GUIModList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e){
				GUIModList.setVisible(false);
				Operators.setListData(getMod(e.getLastIndex()).getOperators());
				Operators.setVisible(true);
				selectedMod = getMod(e.getLastIndex());
				modInfo.setText(getMod(e.getLastIndex()).toString() + " is by " + selectedMod.getAuthor());
				links.setText("You can update it at: " + selectedMod.getUrl() +
						". Or visit the author's site at: " + selectedMod.getAuthorLink() + ".");
				pack();
			}
		});
		Operators.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e){
				Operators.setVisible(false);
				String operator = selectedMod.getOperators()[e.getLastIndex()];
				Operator.setText(operator + " " + selectedMod.getHelp(operator));
				Operator2.setText("Usage: " + selectedMod.getUsage(operator));
				pack();
			}
		});
// call onCancel() when cross is clicked
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();
				MainGUI.setVisible(true);
			}
		});

// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e){

			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK(){
// add your code here
		dispose();
		MainGUI.setVisible(true);
		this.setVisible(false);
	}
}
