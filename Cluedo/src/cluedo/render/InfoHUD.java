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
		int alpha = 120;
		if(data.getCurrentPlayer().get_name() == CharsName.MISS_SCARLET){
			this.setBackground(new Color(238, 0, 0,alpha));
		}else if(data.getCurrentPlayer().get_name() == CharsName.COLONEL_MUSTARD){
			this.setBackground(new Color(233, 165, 6,alpha));
		}else if(data.getCurrentPlayer().get_name() == CharsName.MRS_WHITE){
			this.setBackground(new Color(255,255,255,alpha));
		}else if(data.getCurrentPlayer().get_name() == CharsName.REVEREND_GREEN){
			this.setBackground(new Color(1, 120, 64,alpha));
		}else if(data.getCurrentPlayer().get_name() == CharsName.MRS_PEACOCK){
			this.setBackground(new Color(107, 150, 200,alpha));
		}else if(data.getCurrentPlayer().get_name() == CharsName.PROFESSOR_PLUM){
			this.setBackground(new Color(145, 115, 157,alpha));
		}
	}
}