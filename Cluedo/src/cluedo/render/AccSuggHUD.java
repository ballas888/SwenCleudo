package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cluedo.character.CharsName;
import cluedo.main.Data;

public class AccSuggHUD extends JPanel{
	
	private Data data;
	private int width;
	private int height;
	private int buttonHeight = 40;
	private final JButton cancel = new JButton("Cancel");
	private final JButton suggest = new JButton("Suggest");


	public AccSuggHUD(int width, int height, Data data){
		this.width = width;
		this.data = data;
		Dimension size = new Dimension(width,height);	
		setSize(size);		
		setFocusable(true);
		this.setBackground(Color.black);
		this.setLocation(0, 0);
		setUpChoices();
		setUpSuggest();
		setUpCancel();
		this.setVisible(false);
	}
	
	private void setUpChoices() {
		JPanel choices = new JPanel();
		choices.setBackground(Color.blue);
		choices.setPreferredSize(new Dimension(width,height - (buttonHeight*2)));
		this.add(choices);
		choices.setVisible(true);
	}

	private void setUpCancel() {
		cancel.setLocation(0, height - buttonHeight);
		cancel.setPreferredSize(new Dimension(width,buttonHeight));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
			}
		});
		this.add(cancel);
	}

	private void setUpSuggest() {
		suggest.setLocation(0, height - (buttonHeight*2));
		suggest.setPreferredSize(new Dimension(width,buttonHeight));
		suggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
			}
		});
		this.add(suggest);
	}

	public void updateAccSugg(boolean reveal){
		//this.setVisible(false);
		changePos(reveal);
		//changeColor();
		this.repaint();
		//this.setVisible(true);
	}
	
	private void changePos(boolean reveal){
		this.setBackground(Color.black);
		this.setVisible(reveal);
		if(reveal){
			//this.setLocation(0,0);
			this.requestFocus();
		}else{
			//this.setLocation(0,0);
		}
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
