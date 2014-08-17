package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cluedo.character.Card;
import cluedo.character.CharsName;
import cluedo.main.Data;

public class CardHUD extends JPanel{
	
	//private Data data;
	private Screen screen;
	private int width;
	private Data data;
	
	public CardHUD(int width, int height, Data data, Screen screen){
		//this.data = data;
		this.screen = screen;
		this.width = width;
		this.data = data;
		Dimension size = new Dimension(width,height);	
		setSize(size);		
		setFocusable(true);
		//setBackground(new Color(200,200,200));
		this.setBackground(Color.white);
		this.setLayout(new GridLayout(0,1,5,5));
		this.validate();
		this.setVisible(true);
	}
	
	public void updateCards(){
		drawCards();
		changeColor();
	}
	
	private void changeColor() {
		if(data.getCurrentPlayer().get_name() == CharsName.MISS_SCARLET){
			this.setBackground(new Color(246, 135, 135));
		}else if(data.getCurrentPlayer().get_name() == CharsName.COLONEL_MUSTARD){
			this.setBackground(new Color(244, 212, 137));
		}else if(data.getCurrentPlayer().get_name() == CharsName.MRS_WHITE){
			this.setBackground(new Color(255,255,255));
		}else if(data.getCurrentPlayer().get_name() == CharsName.REVEREND_GREEN){
			this.setBackground(new Color(135, 191, 165));
		}else if(data.getCurrentPlayer().get_name() == CharsName.MRS_PEACOCK){
			this.setBackground(new Color(185, 205, 228));
		}else if(data.getCurrentPlayer().get_name() == CharsName.PROFESSOR_PLUM){
			this.setBackground(new Color(202, 188, 208));
		}
	}
	
	private void drawCards(){
		this.setVisible(false);
		this.removeAll();
		ArrayList<Card> cards = data.getCurrentPlayer().getCards();
		for(int i = 0; i < cards.size(); i++){
			ImageIcon print = new ImageIcon(cards.get(i).getImage());
			Image printS = print.getImage();
			Image scale = printS.getScaledInstance(261/2, 468/2, java.awt.Image.SCALE_SMOOTH);
			print = new ImageIcon(scale);
			JLabel card = new JLabel(print);
			this.add(card);
		}
		this.repaint();
		this.setVisible(true);
		//slkadjflkasjdf
	}
}
