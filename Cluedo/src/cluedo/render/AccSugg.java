package cluedo.render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.plaf.ActionMapUIResource;

import cluedo.character.Card;
import cluedo.character.CardName;
import cluedo.character.Chars;
import cluedo.character.CharsName;
import cluedo.load.LoadImage;
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

			@SuppressWarnings("static-access")
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
				ArrayList<CardName> cards = new ArrayList<CardName>();
				cards.add(chars);
				cards.add(weap);
				cards.add(room);
				checkResult(cards, true);
			}
			
		});
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				acc.dispose();
			}
			
		});
	}
	
	private void checkResult(ArrayList<CardName> cards, boolean isSug){
		ArrayList<Chars> players = data.getPlayChars();
		CardName chars = cards.get(0);
		CardName weap = cards.get(1);
		CardName room = cards.get(2);
		
		CardName isCard = CardName.BALL_ROOM;
		boolean foundRoom = false;
		boolean foundWeap = false;
		boolean foundChar = false;
		boolean found = false;
		int i = data.getCurrentPlayerPos();
		
		loop: while(i<players.size()){
			ArrayList<Card> crds = players.get(i).getCards(); 
			for(Card crd: crds){
				if(crd.getName() == chars){				
					found = true;
					isCard = chars;	
					foundChar = true;
					if(isSug){
						break loop;
					}
				}if(crd.getName() ==weap){
					found = true;
					isCard = weap;
					foundWeap = true;
					if(isSug){
						break loop;
					}
				}if(crd.getName() ==room){
					found = true;
					isCard = room;
					foundRoom = true;
					if(isSug){
						break loop;
					}
				}
			}				
			
			if(i >= players.size()){
				System.out.println(i);
				i = 0;
				continue;
			}else if (i+1 == data.getCurrentPlayerPos()){
				break;
			}	
			i++;
		}
		
		if(isSug){
			if(found){
				System.out.println("FoundCard: "+ isCard);
				foundDialog(isCard, true);
			}
		}else{
			if(foundRoom && foundChar && foundWeap){
				
			}else{
				
			}
		}
		
		
		
	}
	
	private void foundDialog(CardName isCard, boolean isFound){
		JDialog d = new JDialog(data.getFrame());
		JPanel p = new JPanel(null);
		if(isFound){
			p.setPreferredSize(new Dimension(140,265));		
			p.setBackground(Color.black);
			//d.setPreferredSize(new Dimension(210,400));
			JPanel p1 = new JPanel(null);
			p1.setSize(new Dimension(140,265));
			p1.setBackground(Color.white);
			d.setLayout(null);
			d.setContentPane(p);
			
			JLabel j = new JLabel();
			j.setSize(new Dimension(140,230) );
			j.setLocation(5, 2);
			ImageIcon print = new ImageIcon(new LoadImage().load_image(isCard));
			Image printS = print.getImage();
			Image scale = printS.getScaledInstance(261/2, 468/2, java.awt.Image.SCALE_SMOOTH);
			print = new ImageIcon(scale);
			j.setIcon(print);
			JButton b = new JButton("OK!");
			b.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
										
				}
			});
			
			b.setSize(p1.getWidth(),30);
			b.setLocation(0, p1.getHeight()-30);
			p1.add(j);
			p1.add(b);
			p.add(p1);
			d.setTitle("Disproven");
			d.setResizable(false);
			d.pack();
			d.setLocationRelativeTo(null);
			d.setVisible(true);
		}else{
			
		}
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
	
	public static void main(String[] ee){
		JFrame frame = new JFrame();
		Data data = new Data();
		data.setFrame(frame);
		AccSugg aug = new AccSugg(data);
		aug.foundDialog(CardName.BALL_ROOM,true);
		
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
