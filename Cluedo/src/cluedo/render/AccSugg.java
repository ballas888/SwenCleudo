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

import cluedo.character.CardName;
import cluedo.character.Chars;
import cluedo.character.CharsName;
import cluedo.main.Data;
import cluedo.main.Game.Room;
import cluedo.main.Tile;

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
	
	private final JRadioButtonMenuItem MS = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem CM = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem MW = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem RG = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem MP = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem PP = new JRadioButtonMenuItem();
	
	private final JRadioButtonMenuItem Dagger = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem Candlestick =  new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem LeadPipe = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem Revolver = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem Rope = new JRadioButtonMenuItem();
	private final JRadioButtonMenuItem Spanner = new JRadioButtonMenuItem();
	
	private JPanel title1 = new JPanel();
	private JPanel title2 = new JPanel();
	
	private JLabel car = new JLabel("Characters");
	private JLabel wep = new JLabel("Weapons");

	public AccSugg(Data data){
		acc = new JDialog(data.getFrame());
		acc.setSize(new Dimension(width, height));
		acc.setLocationRelativeTo(null);
		
		this.data = data;
		
		title1.add(car);
		
		title2.add(wep);
		setUpButtons();
		setUpWeapons();
		setUpChars();
		
		pan1.setSize(width,20);
		pan2.setPreferredSize(new Dimension(width,150));
		pan3.setSize(width, 20);
		
		pan1.add(title1);
		pan1.add(title2);
		pan2.add(weapons);
		pan2.add(chars);
		pan3.add(cancel);
		pan3.add(suggest);
		selection.add(pan1);
		selection.add(pan2);
		selection.add(pan3);
		acc.setContentPane(selection);
		acc.setVisible(true);
	}


	private void setUpButtons() {
		suggest.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CardName chars = CardName.MISS_SCARLET_C;
				CardName weap = CardName.CANDLESTICK;
				
				if(MS.isSelected()){
					chars = CardName.MISS_SCARLET_C;
				}else if(CM.isSelected()){
					chars = CardName.COLONEL_MUSTARD_C;
				}else if(MW.isSelected()){
					chars = CardName.MRS_WHITE_C;
				}else if(RG.isSelected()){
					chars = CardName.REVEREND_GREEN_C;
				}else if(MP.isSelected()){
					chars = CardName.MRS_PEACOCK_C;
				}else if(PP.isSelected()){
					chars = CardName.PROFESSOR_PLUM_C;
				}
				
				if(Dagger.isSelected()){
					weap = CardName.DAGGER;
				}else if(Candlestick.isSelected()){
					weap = CardName.CANDLESTICK;
				}else if(LeadPipe.isSelected()){
					weap = CardName.LEAD_PIPE;
				}else if(Revolver.isSelected()){
					weap = CardName.REVOLVER;
				}else if(Rope.isSelected()){
					weap = CardName.ROPE;
				}else if(Spanner.isSelected()){
					weap = CardName.SPANNER;
				}
				Chars c = data.getCurrentPlayer();
				Tile[][] tiles = data.getTiles();
				Tile tile = tiles[c.getPosition().y][c.getPosition().x];
				Room rm = tile.get_room();
				CardName room = CardName.BALL_ROOM;
				room = room.valueOf(rm.toString());
				System.out.println(room + " " + chars + " " + weap);
			}
			
		});
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				acc.dispose();
			}
			
		});
	}


	private void setUpChars() {
		MS.setText("Miss Scarlet");
		CM.setText("Colonel Mustard");
		MW.setText("Mrs. White");
		RG.setText("The Reverend Green");
		MP.setText("Mrs. Peacock");
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
		Dagger.setText("Dagger");
		Candlestick.setText("Candlestick");
		LeadPipe.setText("LeadPipe");
		Revolver.setText("Revolver");
		Rope.setText("Rope");
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
