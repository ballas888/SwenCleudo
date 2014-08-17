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
	
	public void updateHUDButtons(boolean die, boolean sug, boolean acc,boolean end){
		roll.setEnabled(die);
		sugg.setEnabled(sug);
		accu.setEnabled(acc);
		endTurn.setEnabled(end);
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
	
	private void setUpRoll(){
		roll.setPreferredSize(new Dimension(this.width-2,60));
		roll.setContentAreaFilled(false);
		roll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				data.getCurrentPlayer().rollDice();
				roll.setEnabled(false);
				hudData.updateInfo();
				screen.requestFocus();
			}
		});
		this.add(roll);
	}

	private void setUpSugg(){
		sugg.setEnabled(false);
		sugg.setPreferredSize(new Dimension(this.width-2,60));
		sugg.setContentAreaFilled(false);
		sugg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				AccSugg as = new AccSugg(data);
				screen.requestFocus();

			}
		});
		this.add(sugg);
	}

	private void setUpAccu(){
		accu.setEnabled(false);
		accu.setPreferredSize(new Dimension(this.width-2,60));
		accu.setContentAreaFilled(false);
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

		  endTurn.setPreferredSize(new Dimension(this.width-2,60));
		endTurn.setContentAreaFilled(false);

		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				data.nextPlayer();	
				roll.setEnabled(true);
				data.getCurrentPlayer().setDieRolled(0, 1);
				data.getCurrentPlayer().setDieRolled(0, 2);
				hudData.updateCards();
				hudData.updateInfo();
				hudData.updateHUD();
				//otherwise screen loses focus
				screen.requestFocus();

			}
		});

		this.add(endTurn);
	}

}
