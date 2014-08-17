package cluedo.render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import cluedo.character.CharsName;
import cluedo.main.Data;

public class AccSugg{
	
	private Data data;
	private int width = 350;
	private int height = 300;
	private final JButton cancel = new JButton("Cancel");
	private final JButton suggest = new JButton("Suggest");
	
	//pop up window
	private JDialog acc;
	
	//holds the selection panels
	private JPanel selection = new JPanel(new FlowLayout());
	private JPanel pan1 = new JPanel();
	private JPanel pan2 = new JPanel();
	private JPanel pan3 = new JPanel();

	
	//button groups to hold selections
	private ButtonGroup charsGroup = new ButtonGroup();
	private	ButtonGroup weaponsGroup = new ButtonGroup();
	
	//panels to hold radio buttons
	private JPanel chars = new JPanel(new GridLayout(0,1,1,1));	
	private JPanel weapons = new JPanel(new GridLayout(0,1,1,1));
	
	private JPanel title1 = new JPanel();
	private JPanel title2 = new JPanel();
	
	private JPanel buttonPanel = new JPanel();
	private JLabel car = new JLabel("Characters");
	private JLabel wep = new JLabel("Weapons");

	public AccSugg(Data data){
		acc = new JDialog(data.getFrame());
		acc.setSize(new Dimension(width, height));
		acc.setLocationRelativeTo(null);
		
		JPanel tempPanel = new JPanel();
		
		//car.setEditable(false);
		//wep.setEditable(false);
		
		//buttonPanel.setBounds(0, 250, 249, 38);
//		buttonPanel.setLocation(0, 250);
//		buttonPanel.setBackground(Color.white);
//		buttonPanel.setBorder(BorderFactory.createCompoundBorder(
//				BorderFactory.createLineBorder(Color.gray, 1),
//				BorderFactory.createEmptyBorder()));
		
		//title1.setBounds(0, 0, 124, 30);
		title1.add(car);
		//title1.setBackground(Color.white);
		//car.setBackground(Color.white);
		
		//title2.setBounds(125,0,124,30);
		title2.add(wep);
		//title2.setBackground(Color.white);
		//wep.setBackground(Color.white);
		//wep.setBounds(175, 0, 200, 200);
		//weapons.setBounds(175,32,200,200);
		//car.setBounds(0, 0, 200, 200);
		//chars.setBounds(0, 32, 200, 200);

		 
		setUpWeapons();
		setUpChars();
		
		pan1.setSize(width,20);
		pan2.setPreferredSize(new Dimension(width,150));
		pan3.setSize(width, 20);
		
		pan1.add(title1);
		pan1.add(title2);
		pan2.add(weapons);
		pan2.add(chars);
		//selection.add(buttonPanel);
		pan3.add(cancel);
		pan3.add(suggest);
		selection.add(pan1);
		selection.add(pan2);
		selection.add(pan3);
		acc.setContentPane(selection);
		acc.setVisible(true);
	}


	private void setUpChars() {
		final JRadioButtonMenuItem MS = new JRadioButtonMenuItem();
		MS.setText("Miss Scarlet");
		final JRadioButtonMenuItem CM = new JRadioButtonMenuItem();
		CM.setText("Colonel Mustard");
		final JRadioButtonMenuItem MW = new JRadioButtonMenuItem();
		MW.setText("Mrs. White");
		final JRadioButtonMenuItem RG = new JRadioButtonMenuItem();
		RG.setText("The Reverend Green");
		final JRadioButtonMenuItem MP = new JRadioButtonMenuItem();
		MP.setText("Mrs. Peacock");
		final JRadioButtonMenuItem PP = new JRadioButtonMenuItem();
		PP.setText("Professor Plum");
		
		charsGroup.add(MS);
		charsGroup.add(CM);
		charsGroup.add(MW);
		charsGroup.add(RG);
		charsGroup.add(MP);
		charsGroup.add(PP);
		
		chars.add(MS);
		chars.add(CM);
		chars.add(MW);
		chars.add(RG);
		chars.add(MP);
		chars.add(PP);
		chars.setSize(new Dimension(width/2,200));
		chars.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.gray, 1),
				BorderFactory.createEmptyBorder()));
				
		
	}


	private void setUpWeapons() {
		final JRadioButtonMenuItem Dagger = new JRadioButtonMenuItem();
		Dagger.setText("Dagger");
		final JRadioButtonMenuItem Candlestick =  new JRadioButtonMenuItem();
		Candlestick.setText("Candlestick");
		final JRadioButtonMenuItem LeadPipe = new JRadioButtonMenuItem();
		LeadPipe.setText("LeadPipe");
		final JRadioButtonMenuItem Revolver = new JRadioButtonMenuItem();
		Revolver.setText("Revolver");
		final JRadioButtonMenuItem Rope = new JRadioButtonMenuItem();
		Rope.setText("Rope");
		final JRadioButtonMenuItem Spanner = new JRadioButtonMenuItem();
		Spanner.setText("Spanner");
		
		weaponsGroup.add(Dagger);
		weaponsGroup.add(Candlestick);
		weaponsGroup.add(LeadPipe);
		weaponsGroup.add(Revolver);
		weaponsGroup.add(Rope);
		weaponsGroup.add(Spanner);
		
		weapons.add(Dagger);
		weapons.add(Candlestick);
		weapons.add(LeadPipe);
		weapons.add(Revolver);
		weapons.add(Rope);
		weapons.add(Spanner);
		weapons.setSize(new Dimension(width/2,200));

		weapons.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.gray, 1),
				BorderFactory.createEmptyBorder()));
		
	}
	
	
//	private void changeColor() {
//		if(data.getCurrentPlayer().get_name() == CharsName.MISS_SCARLET){
//			this.setBackground(new Color(246, 135, 135));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.COLONEL_MUSTARD){
//			this.setBackground(new Color(244, 212, 137));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.MRS_WHITE){
//			this.setBackground(new Color(255,255,255));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.REVEREND_GREEN){
//			this.setBackground(new Color(135, 191, 165));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.MRS_PEACOCK){
//			this.setBackground(new Color(185, 205, 228));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.PROFESSOR_PLUM){
//			this.setBackground(new Color(202, 188, 208));
//		}
//	}
}
