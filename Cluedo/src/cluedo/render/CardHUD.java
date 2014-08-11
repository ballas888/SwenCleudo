package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import cluedo.main.Data;

public class CardHUD extends JPanel{
	
	private Data data;
	private Screen screen;
	private int width;
	
	public CardHUD(int width, int height, Data data, Screen screen){
		this.data = data;
		this.screen = screen;
		this.width = width;
		Dimension size = new Dimension(width,height);	
		setSize(size);		
		setFocusable(true);
		//setBackground(new Color(200,200,200));
		setBackground((Color.green));
		
		this.validate();
		this.setVisible(true);
	}
}
