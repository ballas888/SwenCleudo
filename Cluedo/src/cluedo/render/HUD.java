package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import cluedo.character.CharsName;
import cluedo.main.Data;
import cluedo.main.Game;

public class HUD extends JPanel{

	private final JButton endTurn = new JButton("End Turn");
	private final JButton sugg = new JButton("Make Suggestion");
	private final JButton accu = new JButton("Make Accusation");
	private final JButton roll = new JButton("Roll Dice");


	private Data data;
	private Screen screen;
	private int width;
	
	private HUDData hudData;


	public HUD(int width, int height, Data data, Screen screen){
		this.data = data;
		this.screen = screen;
		this.width = width;
		Dimension size = new Dimension(width,height);
		setSize(size);
		this.setLayout(new GridLayout(4,1));
		setFocusable(true);
		//setBackground(new Color(200,200,200));
		setBackground((Color.black));

		setUpRoll();
		setUpSugg();
		setUpAccu();
		setUpEndTurn();
		this.validate();
		this.setVisible(true);
	}
	
	public void updateHUD(){
		changeColor();
	}
	
	public void updateHUDButtons(boolean die, boolean sug, boolean acc){
		roll.setEnabled(die);
		sugg.setEnabled(sug);
		accu.setEnabled(acc);
	}
	
	private void changeColor() {
		int alpha = 255;
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
	
	private void setUpRoll(){
		roll.setPreferredSize(new Dimension(this.width-2,60));
		//roll.setContentAreaFilled(false);
		roll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				screen.requestFocus();

			}
		});
		this.add(roll);
	}

	private void setUpSugg(){
		sugg.setEnabled(false);
		sugg.setPreferredSize(new Dimension(this.width-2,60));
		//sugg.setContentAreaFilled(false);
		sugg.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.gray, 1),
				BorderFactory.createEmptyBorder()));
		sugg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				screen.requestFocus();

			}
		});
		this.add(sugg);
	}

	private void setUpAccu(){
		accu.setEnabled(false);
		accu.setPreferredSize(new Dimension(this.width-2,60));
		//accu.setContentAreaFilled(false);
		accu.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.gray, 1),
				BorderFactory.createEmptyBorder()));
		accu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				screen.requestFocus();

			}
		});
		this.add(accu);
	}

	public void setHudData(HUDData hudData){
		this.hudData = hudData;
	}
	
	private void setUpEndTurn() {

//		endTurn.setForeground(Color.BLACK);
//		  endTurn.setBackground(Color.WHITE);
//		  Border line = new LineBorder(Color.BLACK);
//		  Border margin = new EmptyBorder(5, 15, 5, 15);
//		  Border compound = new CompoundBorder(line, margin);
//		  endTurn.setBorder(compound);

		  endTurn.setPreferredSize(new Dimension(this.width-2,60));
			endTurn.setContentAreaFilled(false);

		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				data.nextPlayer();
				hudData.updateCards();
				hudData.updateInfo();
				//otherwise screen loses focus
				screen.requestFocus();

			}
		});

		this.add(endTurn);
	}

}
