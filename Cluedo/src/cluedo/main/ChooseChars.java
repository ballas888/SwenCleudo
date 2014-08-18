package cluedo.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import cluedo.character.Chars;
import cluedo.character.CharsName;

/*
 * Sets up the players using a button selected 
 */

public class ChooseChars {
	//players to choose from
	private JRadioButtonMenuItem MS = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem CM = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem MW = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem RG = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem MP = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem PP = new JRadioButtonMenuItem();
	
	//radio button group
	private final ButtonGroup bj = new ButtonGroup();
	private final JPanel charPanel = new JPanel(new GridLayout(0,1,1,1));
	
	//buttons
	private final JButton select = new JButton("Select");
	private final JButton done = new JButton("Done");
	
	//where players enter their name
	private JTextField pName = new JTextField();

	//list of playable players
	private ArrayList<Chars> charList;
	
	//temp player name strings
	private String MSName = null;
	private String CMName = null;
	private String MWName = null;
	private String RGName = null;
	private String MPName = null;
	private String PPName = null;
	private int numSelec = 0;
	
//	private TheChars MSName = new TheChars(CharsName.MISS_SCARLET);
//	private ArrayList<TheChars> the_chars = new ArrayList<TheChars>();
	
	private JDialog dialog;
	private JFrame frame;
	
//	public class TheChars{
//		public CharsName name;
//		public String pname;
//		
//		public TheChars(CharsName name){
//			this.name = name;
//		}
//	}
	
	public ChooseChars(ArrayList<Chars> allChars, JFrame frame){
		this.charList = allChars;
		this.frame = frame;
		 dialog = new JDialog(this.frame);
		
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (numSelec > 0) {//FOR TESTING
				//add selected to the list
//					for(int i = 0; i < the_chars.size();i++){
//						for(int j = 0; j < charList.size();j++){
//							if(the_chars.get(i).name == charList.get(j).get_name()){
//								charList.get(j).setPlayName(the_chars.get(i).pname);
//							}
//						}
//					}
					
					for(Chars c : charList){
						if(MSName != null && !MSName.isEmpty() && c.get_name().equals(CharsName.MISS_SCARLET)){
							c.setPlayName(MSName);
							c.setPlayable(true);
						}else if(CMName != null && !CMName.isEmpty() && c.get_name().equals(CharsName.COLONEL_MUSTARD)){
							c.setPlayName(CMName);
							c.setPlayable(true);
						}else if(MWName != null && !MWName.isEmpty() && c.get_name().equals(CharsName.MRS_WHITE)){
							c.setPlayName(MWName);
							c.setPlayable(true);
						}else if(RGName != null && !RGName.isEmpty() && c.get_name().equals(CharsName.REVEREND_GREEN)){
							c.setPlayName(RGName);
							c.setPlayable(true);
						}else if(MPName != null && !MPName.isEmpty() && c.get_name().equals(CharsName.MRS_PEACOCK)){
							c.setPlayName(MPName);
							c.setPlayable(true);
						}else if(PPName != null && !PPName.isEmpty() && c.get_name().equals(CharsName.PROFESSOR_PLUM)){
							c.setPlayName(PPName);
							c.setPlayable(true);
						}
					}
					dialog.dispose();
					
				} else {
					doneError();
				}
			}
		});
		
		
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				//get what has been selected
				ButtonModel b = bj.getSelection();
				
				//user hasnt entered name
				if(pName.getText() == null || pName.getText().isEmpty()){
					nameError();
					
				//name is longer than 15 chars
				//TODO: unlimited size
				}else if(pName.getText().length() > 100){
					nameErrorLeng();	
					
				//user hasnt selected anything!
				}else if(b == null){
					charselectError();
					
				//everything checks out so move on
				}else if (MS.isSelected()) {
					MS.setEnabled(false);					
					MSName = pName.getText();
					numSelec++;
					
				} else if (CM.isSelected()) {
					CM.setEnabled(false);
					CMName = pName.getText();
					numSelec++;
					
				} else if (MW.isSelected()) {
					MW.setEnabled(false);
					MWName = pName.getText();
					numSelec++;
					
				} else if (RG.isSelected()) {
					RG.setEnabled(false);
					RGName = pName.getText();
					numSelec++;
					
				} else if (MP.isSelected()) {
					MP.setEnabled(false);
					MPName = pName.getText();
					numSelec++;
					
				} else if (PP.isSelected()) {					
					PP.setEnabled(false);
					PPName = pName.getText();
					numSelec++;
				} 				
				pName.setText(null);
				bj.clearSelection();
				pName.requestFocus();
			}

			
		});
		
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		choosePlayers();
	}
	
	
	public void choosePlayers() {
		// JFrame guiFrame = new JFrame();
		// guiFrame.setContentPane(panel2);
		// guiFrame.setBounds(0, 0, 200, 200);
		// guiFrame.setVisible(true);

		MS.setText("Miss Scarlett");
		CM.setText("Colonol Mustard");
		MW.setText("Mrs White");
		RG.setText("Reverend Green");
		MP.setText("Mrs Peacock");
		PP.setText("Professor Plum");

		MS.setBackground(new Color(238, 0, 0));
		MS.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				BorderFactory.createEmptyBorder()));

		CM.setBackground(new Color(233, 165, 6));
		CM.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				BorderFactory.createEmptyBorder()));

		MW.setBackground(Color.white);
		MW.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				BorderFactory.createEmptyBorder()));

		RG.setBackground(new Color(1, 120, 64));
		RG.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				BorderFactory.createEmptyBorder()));

		MP.setBackground(new Color(107, 150, 200));
		MP.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				BorderFactory.createEmptyBorder()));

		PP.setBackground(new Color(145, 115, 157));
		PP.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				BorderFactory.createEmptyBorder()));

		dialog.setContentPane(charPanel);
		charPanel.add(pName);
		
		charPanel.setBackground(Color.white);
		
		
		bj.add(MS);
		bj.add(CM);
		bj.add(MW);
		bj.add(RG);
		bj.add(MP);
		bj.add(PP);
		
		charPanel.add(MS);
		charPanel.add(CM);
		charPanel.add(MW);
		charPanel.add(RG);
		charPanel.add(MP);
		charPanel.add(PP);
		charPanel.add(select);
		charPanel.add(done);

		dialog.setTitle("Choose Player");
		dialog.setSize(new Dimension(200, 300));
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(this.frame);
		dialog.setModal(true);
		dialog.setVisible(true);

	}
	
	/**
	 * Various user logic errors to throw in their face
	 */
	private void doneError(){
		final JDialog notify = new JDialog(dialog);
		JButton ok = new JButton("OK!");
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				notify.dispose();							
			}						
		});
		JPanel dPanel = new JPanel();
		notify.setTitle("Warning");
		notify.setVisible(true);
		JTextField notification = new JTextField();
		notification.setText(" You must choose at least 3 players! ");
		notification.setEditable(false);
		notification.setBorder(BorderFactory.createEmptyBorder()
				);
		notification.setBackground(Color.white);
		dPanel.add(notification);
		dPanel.add(ok);
		dPanel.setBackground(Color.white);
		notify.add(dPanel);					
		notify.setBackground(Color.white);
		notify.setSize(new Dimension(250, 100));
		notify.setResizable(false);
		notify.setLocationRelativeTo(dialog);
		notify.setModal(true);
		notify.setVisible(true);
	}
	
	private void nameError(){
		final JDialog notify = new JDialog(dialog);
		JButton ok = new JButton("OK!");
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				notify.dispose();							
			}						
		});
		JPanel dPanel = new JPanel();
		notify.setTitle("Warning");
		notify.setVisible(true);
		JTextField notification = new JTextField();
		notification.setText("Please Enter Name!");		
		notification.setEditable(false);
		notification.setBorder(BorderFactory.createEmptyBorder()
				);
		notification.setBackground(Color.white);
		dPanel.add(notification);
		dPanel.add(ok);
		dPanel.setBackground(Color.white);
		notify.add(dPanel);					
		notify.setBackground(Color.white);
		notify.setSize(new Dimension(150, 100));
		notify.setResizable(false);
		notify.setLocationRelativeTo(dialog);
		notify.setModal(true);
		notify.setVisible(true);
	
	}
	
	private void charselectError(){
		final JDialog notify = new JDialog(dialog);
		JButton ok = new JButton("OK!");
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				notify.dispose();							
			}						
		});
		JPanel dPanel = new JPanel();
		notify.setTitle("Warning");
		notify.setVisible(true);
		JTextField notification = new JTextField();
		notification.setText("Please Select a Character!");		
		notification.setEditable(false);
		notification.setBorder(BorderFactory.createEmptyBorder()
				);
		notification.setBackground(Color.white);
		dPanel.add(notification);
		dPanel.add(ok);
		dPanel.setBackground(Color.white);
		notify.add(dPanel);					
		notify.setBackground(Color.white);
		notify.setSize(new Dimension(200, 100));
		notify.setResizable(false);
		notify.setLocationRelativeTo(dialog);
		notify.setModal(true);
		notify.setVisible(true);
	
	}
	
	private void nameErrorLeng() {
		final JDialog notify = new JDialog(dialog);
		JButton ok = new JButton("OK!");
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				notify.dispose();							
			}						
		});
		JPanel dPanel = new JPanel();
		notify.setTitle("Warning");
		notify.setVisible(true);
		JTextField notification = new JTextField();
		notification.setText("The Name Entered is to long. It must be 15 or less characters");		
		notification.setEditable(false);
		notification.setBorder(BorderFactory.createEmptyBorder()
				);
		notification.setBackground(Color.white);
		dPanel.add(notification);
		dPanel.add(ok);
		dPanel.setBackground(Color.white);
		notify.add(dPanel);					
		notify.setBackground(Color.white);
		notify.setSize(new Dimension(400, 100));
		notify.setResizable(false);
		notify.setLocationRelativeTo(dialog);
		notify.setModal(true);
		notify.setVisible(true);
		
	}
}
