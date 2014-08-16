package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

import cluedo.main.Data;

public class InfoHUD extends JPanel{
	
	private Data data;
	private Screen screen;
	private int width;
	private JTextField current = new JTextField(50);

	
	public InfoHUD(int width, int height, Data data, Screen screen){
		this.data = data;
		this.screen = screen;
		this.width = width;
		Dimension size = new Dimension(width,height);	
		setSize(size);		
		setFocusable(true);
		//setBackground(new Color(200,200,200));
		setBackground((Color.red));
		//this.add(current);
		this.validate();
		this.setVisible(true);
	}
	
//	public void updatePlayer(){
//		current.setText(data.getCurrentPlayer().getPlayName());
//	}
}