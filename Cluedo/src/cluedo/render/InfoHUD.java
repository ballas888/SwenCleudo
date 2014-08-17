package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

import cluedo.character.CharsName;
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
		//setBackground((Color.red));
		//this.add(current);
		this.validate();
		this.setVisible(true);
	}
	
	public void drawInfo(){
		this.setVisible(false);
		this.removeAll();
		
		changeColor();
		
		this.repaint();
		this.setVisible(true);
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
}