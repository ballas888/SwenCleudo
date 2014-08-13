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

public class HUD extends JPanel{

	private final JButton endTurn = new JButton("End Turn");
	private final JButton sugg = new JButton("Make Suggestion");
	private final JButton accu = new JButton("Make Accusation");
	private final JButton vir = new JButton("UnkNown");


	private Data data;
	private Screen screen;
	private int width;


	public HUD(int width, int height, GridLayout layout, Data data, Screen screen){
		this.data = data;
		this.screen = screen;
		this.width = width;
		Dimension size = new Dimension(width,height);
		setSize(size);
		setFocusable(true);
		//setBackground(new Color(200,200,200));
		setBackground((Color.blue));

		setUpSugg();
		setUpAccu();
		setUpVir();
		setUpEndTurn();
		this.validate();
		this.setVisible(true);
	}

	private void setUpSugg(){
		sugg.setPreferredSize(new Dimension(this.width-2,50));
		this.add(sugg);
	}

	private void setUpAccu(){
		accu.setPreferredSize(new Dimension(this.width-2,50));
		this.add(accu);
	}

	private void setUpVir(){
		vir.setPreferredSize(new Dimension(this.width-2,50));
		this.add(vir);
	}

	private void setUpEndTurn() {

//		endTurn.setForeground(Color.BLACK);
//		  endTurn.setBackground(Color.WHITE);
//		  Border line = new LineBorder(Color.BLACK);
//		  Border margin = new EmptyBorder(5, 15, 5, 15);
//		  Border compound = new CompoundBorder(line, margin);
//		  endTurn.setBorder(compound);

		  endTurn.setPreferredSize(new Dimension(this.width-2,50));

		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				data.nextPlayer();
				//otherwise screen loses focus
				screen.requestFocus();

			}
		});

		this.add(endTurn);
	}

}
