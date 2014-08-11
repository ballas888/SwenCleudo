package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import cluedo.main.Data;

public class InfoHud extends JPanel{
	
	private Data data;
	private Screen screen;
	private int width;
	
	public InfoHud(int width, int height, Data data, Screen screen){
		this.data = data;
		this.screen = screen;
		this.width = width;
		Dimension size = new Dimension(width,height);	
		setSize(size);		
		setFocusable(true);
		//setBackground(new Color(200,200,200));
		setBackground((Color.red));
		
		this.validate();
		this.setVisible(true);
	}
}