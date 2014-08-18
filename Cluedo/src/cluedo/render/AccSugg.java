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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.plaf.ActionMapUIResource;

import cluedo.character.Card;
import cluedo.character.CardName;
import cluedo.character.Chars;
import cluedo.character.CharsName;
import cluedo.load.LoadImage;
import cluedo.main.Data;
import cluedo.main.Game.Room;
import cluedo.main.Tile;
/*
 * Represents the panel for players to accuse or suggest
 * theories on other players
 */
public class AccSugg{

	private Data data;
	private int width = 400;
	private int height = 260;
	private boolean sugg;
	private final JButton cancel = new JButton("Cancel");
	private final JButton suggest = new JButton("Suggest");

	//pop up window
	private JDialog acc;
	private JDialog d;

	//holds the selection panels
	private JPanel selection = new JPanel(new FlowLayout());
	private JPanel pan1 = new JPanel(new GridLayout(1,0,1,1));
	private JPanel pan2 = new JPanel(new GridLayout(1,0,1,1));
	private JPanel pan3 = new JPanel(new GridLayout(1,0,1,1));


	//button groups to hold selections
	private ButtonGroup charsGroup = new ButtonGroup();
	private	ButtonGroup weaponsGroup = new ButtonGroup();
	private	ButtonGroup roomsGroup = new ButtonGroup();


	//panels to hold radio buttons
	private JPanel chars = new JPanel(new GridLayout(0,1,1,1));
	private JPanel weapons = new JPanel(new GridLayout(0,1,1,1));
	private JPanel rooms = new JPanel(new GridLayout(0,1,1,1));

	//player radio buttons
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

	private final JRadioButtonMenuItem Ball = new JRadioButtonMenuItem("Ball Room");
	private final JRadioButtonMenuItem Billard  =  new JRadioButtonMenuItem("Billard Room");
	private final JRadioButtonMenuItem Conservatory = new JRadioButtonMenuItem("Conservatory");
	private final JRadioButtonMenuItem Dining  = new JRadioButtonMenuItem("Dining Room");
	private final JRadioButtonMenuItem Hall = new JRadioButtonMenuItem("Hall");
	private final JRadioButtonMenuItem Kitchen = new JRadioButtonMenuItem("Kitchen");
	private final JRadioButtonMenuItem Library = new JRadioButtonMenuItem("Library");
	private final JRadioButtonMenuItem Lounge = new JRadioButtonMenuItem("Lounge");
	private final JRadioButtonMenuItem Study = new JRadioButtonMenuItem("Study");

	private JPanel title1 = new JPanel();
	private JPanel title2 = new JPanel();
	private JPanel title3 = new JPanel();

	private JLabel car = new JLabel("Characters",SwingConstants.LEFT);
	private JLabel wep = new JLabel("Weapons",SwingConstants.LEFT);
	private JLabel roo = new JLabel("Rooms",SwingConstants.LEFT);

	public AccSugg(Data data, boolean sugg){
		this.sugg = sugg;
		this.data = data;
		if(this.sugg){
			buildSugg();
		}else{
			buildAcc();
		}
	}

	//user is accusing
	private void buildAcc() {
		int spacing = 100;
		acc = new JDialog(data.getFrame());
		acc.setSize(new Dimension(width+spacing, height));
		acc.setLocationRelativeTo(null);

		title1.add(car);
		title2.add(wep);
		title3.add(roo);
		suggest.setText("Accuse");
		setUpButtons();
		setUpWeapons();
		setUpChars();
		setUpRooms();

		pan1.setPreferredSize(new Dimension(width+spacing,20));
		pan2.setPreferredSize(new Dimension(width+spacing,160));
		pan3.setPreferredSize(new Dimension(width+spacing,30));

		chars.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(),
				BorderFactory.createEmptyBorder()));
		weapons.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(),
				BorderFactory.createEmptyBorder()));

		pan1.add(title1);
		pan1.add(title2);
		pan1.add(title3);
		pan2.add(weapons);
		pan2.add(chars);
		pan2.add(rooms);
		pan3.add(suggest);

		pan3.add(cancel);
		selection.add(pan1);
		selection.add(pan2);
		selection.add(pan3);
		acc.setContentPane(selection);
		acc.setTitle("Make An Accusation");
		acc.setModal(true);
		acc.setVisible(true);
	}

	//user is suggesting
	private void buildSugg() {
		acc = new JDialog(data.getFrame());
		acc.setSize(new Dimension(width, height));
		acc.setLocationRelativeTo(null);

		title1.add(car);
		title2.add(wep);
		setUpButtons();
		setUpWeapons();
		setUpChars();

		pan1.setPreferredSize(new Dimension(width,20));
		pan2.setPreferredSize(new Dimension(width,160));
		pan3.setPreferredSize(new Dimension(width,30));

		chars.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(),
				BorderFactory.createEmptyBorder()));
		weapons.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(),
				BorderFactory.createEmptyBorder()));

		pan1.add(title1);
		pan1.add(title2);
		pan2.add(weapons);
		pan2.add(chars);
		pan3.add(suggest);
		pan3.add(cancel);
		selection.add(pan1);
		selection.add(pan2);
		selection.add(pan3);
		acc.setContentPane(selection);
		acc.setTitle("Make A Suggestion");
		acc.setModal(true);
		acc.setVisible(true);
	}


	private void setUpButtons() {
		suggest.addActionListener(new ActionListener(){

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				CardName chars = CardName.MISS_SCARLET_C;
				CardName weap = CardName.CANDLESTICK;
				CardName room = CardName.BALL_ROOM;

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


				if(sugg){
					Chars c = data.getCurrentPlayer();
					Tile[][] tiles = data.getTiles();
					Tile tile = tiles[c.getPosition().y][c.getPosition().x];
					Room rm = tile.get_room();
					room = room.valueOf(rm.toString());
					System.out.println(room + " " + chars + " " + weap);
				}else{
					if(Ball.isSelected()){
						room = CardName.BALL_ROOM;
					}else if(Billard.isSelected()){
						room = CardName.BILLARD_ROOM;
					}else if(Conservatory.isSelected()){
						room = CardName.CONSERVATORY;
					}else if(Dining.isSelected()){
						room = CardName.DINING_ROOM;
					}else if(Hall.isSelected()){
						room = CardName.HALL;
					}else if(Kitchen.isSelected()){
						room = CardName.KITCHEN;
					}else if(Library.isSelected()){
						room = CardName.LIBRARY;
					}else if(Lounge.isSelected()){
						room = CardName.LOUNGE;
					}else if(Study.isSelected()){
						room = CardName.STUDY;
					}
				}
				ArrayList<CardName> cards = new ArrayList<CardName>();
				cards.add(chars);
				cards.add(weap);
				cards.add(room);
				if(sugg){
					checkResult(cards, true);
				}else{
					checkResult(cards, false);
				}
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
		//chars.setPreferredSize(new Dimension(width/2,30));
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

		weapons.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.gray, 1),
				BorderFactory.createEmptyBorder()));

	}

	private void setUpRooms(){
		roomsGroup.add(Ball);
		roomsGroup.add(Billard);
		roomsGroup.add(Conservatory);
		roomsGroup.add(Dining);
		roomsGroup.add(Hall);
		roomsGroup.add(Kitchen);
		roomsGroup.add(Library);
		roomsGroup.add(Lounge);
		roomsGroup.add(Study);

		rooms.add(Ball);
		rooms.add(Billard);
		rooms.add(Conservatory);
		rooms.add(Dining);
		rooms.add(Hall);
		rooms.add(Kitchen);
		rooms.add(Library);
		rooms.add(Lounge);
		rooms.add(Study);
	}
	
	//try and disprove the user
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
		if(isSug){
			loop: while(i<players.size()){
				ArrayList<Card> crds = players.get(i).getCards();
				for(Card crd: crds){
					if(crd.getName() == chars){
						found = true;
						isCard = chars;
						break loop;

					}else if(crd.getName() ==weap){
						found = true;
						isCard = weap;
						break loop;

					}else if(crd.getName() ==room){
						found = true;
						isCard = room;
						foundRoom = true;
						break loop;
						}
					}
				if(i >= players.size()){
					System.out.println(i);
					i = 0;
					continue loop;
				}else if (i+1 == data.getCurrentPlayerPos()){
					break;
				}
				i++;
			}
		}else{
			if(chars == data.getMCharCard().getName() && weap == data.getMWeapCard().getName() && room == data.getMRoomCard().getName()){
				//accusation is correct
				accPopUp(false);
			}else{
				//accusation is wrong
				accPopUp(true);
			}
		}
		if(isSug){
			if(found){
				//suggestion is proven wrong
				foundDialog(isCard, true);
			}else{
				//suggestion isnt proven wrong
				foundDialog(isCard, false);
			}
		}



	}

	//notify user if their accusation is right or wrong
	private void accPopUp(boolean isDead){
		d = new JDialog(acc);
		d.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		JPanel p = new JPanel(null);
		String text = "<html><div style=\"text-align: center;\">You're Accusation Is Wrong<br> You Have Been Eliminated</html>";
		if(!isDead){
			text = "You Win!";
		}
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setSize(new Dimension(140,70));
		label.setLocation(0, 2);
		label.setVisible(true);

		JPanel p1 = new JPanel(null);
		p.setPreferredSize(new Dimension(140,100));
		p1.setSize(140, 100);
		if(isDead){
			label.setSize(new Dimension(200,70));
			p.setPreferredSize(new Dimension(200,100));
			p1.setSize(200, 100);
		}


		p1.setBackground(Color.white);

		JButton b = new JButton("OK!");
		if(isDead){
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					data.eliminate();
					d.dispose();
					acc.dispose();
					data.getHud().updateHUDRoll(true);
					if(data.getPlayChars().size() == 1){
						accPopUp(false);
					}
				}
			});
		}else{
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					d.dispose();
					acc.dispose();
					System.exit(0);
				}
			});
		}
		b.setSize(p1.getWidth(),30);
		b.setLocation(0, p1.getHeight()-30);

		p1.add(label);
		p1.add(b);

		d.setLayout(null);
		d.setContentPane(p);
		p.add(p1);
		d.setResizable(false);
		d.pack();
		d.setLocationRelativeTo(acc);
		d.setModal(true);
		d.setVisible(true);

	}

	//notify user if their suggestion could be correct
	private void foundDialog(CardName isCard, boolean isFound){

		d = new JDialog(acc);
		JPanel p = new JPanel(null);
		String text = "Theory Disproven";
		if(!isFound){
			StringBuilder sb = new StringBuilder(64);
	        sb.append("<html><div style=\"text-align: center;\">" + "Theory Not Disproved:").append("<br>No card found<br>").append("to disprove Theory"+"</html>");
			text = sb.toString();
		}
		JLabel label = new JLabel(text,SwingConstants.CENTER);
		if(!isFound){
			label.setSize(new Dimension(140,70));
		}else{
			label.setSize(new Dimension(140,50));
		}

		label.setLocation(0, 2);
		label.setBackground(Color.black);
		label.setVisible(true);

		if(!isFound){
			p.setPreferredSize(new Dimension(140,100));
		}else{
			p.setPreferredSize(new Dimension(140,315));
		}

		JPanel p1 = new JPanel(null);
		if(!isFound){
			p1.setSize(new Dimension(140,100));
		}else{
			p1.setSize(new Dimension(140,315));
		}

		p1.setBackground(Color.white);

		if(isFound){
			JLabel j = new JLabel();
			j.setSize(new Dimension(140,230) );
			j.setLocation(5, 52);
			ImageIcon print = new ImageIcon(new LoadImage().load_image(isCard));
			Image printS = print.getImage();
			Image scale = printS.getScaledInstance(261/2, 468/2, java.awt.Image.SCALE_SMOOTH);
			print = new ImageIcon(scale);
			j.setIcon(print);
			p1.add(j);
		}
		JButton b = new JButton("OK!");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					d.dispose();
					acc.dispose();
					data.nextPlayer();
					data.getHud().updateCards();
					data.getHud().updateHUD();
					data.getHud().updateInfo();
			}
		});
		b.setSize(p1.getWidth(),30);
		b.setLocation(0, p1.getHeight()-30);

		p1.add(label);
		p1.add(b);

		d.setLayout(null);
		d.setContentPane(p);
		p.add(p1);
		d.setResizable(false);
		d.pack();
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}

	public static void main(String[] ee){
		JFrame frame = new JFrame();
		Data data = new Data();
		data.setFrame(frame);
		AccSugg aug = new AccSugg(data,false);
		aug.foundDialog(CardName.BALL_ROOM,false);

	}

}
