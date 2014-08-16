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
		this.setLayout(new GridLayout(9,2,10,10));
		this.validate();
		this.setVisible(true);
	}
	
	public void drawCards(){
		this.setVisible(false);
		this.removeAll();
		ArrayList<Card> cards = data.getCurrentPlayer().getCards();
		for(int i = 0; i < cards.size(); i++){
			ImageIcon print = new ImageIcon(cards.get(i).getImage());
			Image printS = print.getImage();
			Image scale = printS.getScaledInstance(261/4, 468/4, java.awt.Image.SCALE_SMOOTH);
			print = new ImageIcon(scale);
			JLabel card = new JLabel(print);
			this.add(card);
		}
		this.repaint();
		this.setVisible(true);
		//slkadjflkasjdf
	}
}
