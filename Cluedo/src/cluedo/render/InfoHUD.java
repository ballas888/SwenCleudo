package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cluedo.character.CharsName;
import cluedo.main.Data;

public class InfoHUD extends JPanel{
	
	private Data data;
	private Screen screen;
	private int width;
	private JPanel title = new JPanel();
	private JLabel current = new JLabel("INITIAL");

	
	public InfoHUD(int width, int height, Data data, Screen screen){
		this.data = data;
		this.screen = screen;
		this.width = width;
		this.setLayout(null);
		Dimension size = new Dimension(width,height);	
		setSize(size);		
		setFocusable(true);
		title.setSize(new Dimension(width, 60));
		title.add(current);
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
		
		this.repaint();
		this.setVisible(true);
	}
	
	private void updateName(){
		current.setVisible(false);
		//current.removeAll();
        StringBuilder sb = new StringBuilder(64);
        sb.append("<html><div style=\"text-align: center;\">" + data.getCurrentPlayer().getPlayName()).append("<br>Playing As:<br>").append(data.getCurrentPlayer().get_name()+"</html>");
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