package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cluedo.character.CharsName;
import cluedo.character.Dice;
import cluedo.main.Data;

public class InfoHUD extends JPanel{
	
	private Data data;
	private Screen screen;
	private int width;
	private JPanel title = new JPanel();
	private JLabel current = new JLabel("INITIAL");
	private Dice dice1 = new Dice();
	private Dice dice2 = new Dice();
	private JLabel d1;
	private JLabel d2;	
	
	public InfoHUD(int width, int height, Data data, Screen screen){
		
		this.data = data;
		this.screen = screen;
		this.width = width;
		this.setLayout(null);
		Dimension size = new Dimension(width,height);	
		setSize(size);		
		setFocusable(true);
		d1 = new JLabel();
		d2 = new JLabel();
		
		d1.setSize(this.getWidth()/2-2,this.getWidth()/2-2);
		d1.setLocation(0, (this.getHeight()/2)-((this.width/2)-2));
		d2.setSize(this.getWidth()/2-2,this.getWidth()/2-2);
		d2.setLocation((this.getWidth()/2)+2, (this.getHeight()/2)-((this.width/2)-2));
				
		title.setSize(new Dimension(width, 60));
		title.add(current);
		
		this.add(d1);
		this.add(d2);
		this.add(title);
		//title.setBackground((Color.black));
		//this.add(current);
		this.validate();
		this.setVisible(true);
	}
	
	public void drawInfo(){
		this.setVisible(false);
		//this.removeAll();
		
		changeColor();
		updateName();
		drawDice();
		
		this.repaint();
		this.setVisible(true);
	}
	
	private void drawDice(){
		this.setVisible(false);
		int d1Value = data.getCurrentPlayer().getDieRolled(1);
		int d2Value = data.getCurrentPlayer().getDieRolled(2);
		ImageIcon d1Icon = new ImageIcon(dice1.getDie(d1Value));
		ImageIcon d2Icon = new ImageIcon(dice2.getDie(d2Value));
		Image d1It = d1Icon.getImage();
		Image scale1 = d1It.getScaledInstance(this.getWidth()/2-2, this.getWidth()/2-2, java.awt.Image.SCALE_SMOOTH);
		d1Icon = new ImageIcon(scale1);
		
		Image d2It = d2Icon.getImage();
		Image scale2 = d2It.getScaledInstance(this.getWidth()/2-2, this.getWidth()/2-2, java.awt.Image.SCALE_SMOOTH);
		d2Icon = new ImageIcon(scale2);
		
		d1.setIcon(d1Icon);
		d2.setIcon(d2Icon);		
	}
	
	private void updateName(){
		current.setVisible(false);
		//current.removeAll();
        StringBuilder sb = new StringBuilder(64);
        sb.append("<html><div style=\"text-align: center;\">" + data.getCurrentPlayer().getPlayName()).append("<br>Playing As:<br>").append(data.getCurrentPlayer().get_name()+"</html>");
		//current.setText("<html" + data.getCurrentPlayer().getPlayName() + " Playing As: " + data.getCurrentPlayer().get_name()+"</html>");
		current.setText(sb.toString());
        current.setVisible(true);
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