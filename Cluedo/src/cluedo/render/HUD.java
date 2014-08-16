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
		setBackground((Color.white));

		setUpRoll();
		setUpSugg();
		setUpAccu();
		setUpEndTurn();
		this.validate();
		this.setVisible(true);
	}
	
	private void setUpRoll(){
		roll.setPreferredSize(new Dimension(this.width-2,60));
		roll.setContentAreaFilled(false);
		roll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				screen.requestFocus();

			}
		});
		this.add(roll);
	}

	private void setUpSugg(){
		sugg.setPreferredSize(new Dimension(this.width-2,60));
		sugg.setContentAreaFilled(false);
		sugg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				screen.requestFocus();

			}
		});
		this.add(sugg);
	}

	private void setUpAccu(){
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
