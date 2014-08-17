package cluedo.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cluedo.character.CharsName;
import cluedo.main.Data;

public class AccSugg{
	
	private Data data;
	private int width = 100;
	private int height = 100;
	private final JButton cancel = new JButton("Cancel");
	private final JButton suggest = new JButton("Suggest");


	public AccSugg(Data data){
		JDialog acc = new JDialog();
		acc.setLocationRelativeTo(null);
		acc.setPreferredSize(new Dimension(width, height));
	}
	
	
//	private void changeColor() {
//		if(data.getCurrentPlayer().get_name() == CharsName.MISS_SCARLET){
//			this.setBackground(new Color(246, 135, 135));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.COLONEL_MUSTARD){
//			this.setBackground(new Color(244, 212, 137));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.MRS_WHITE){
//			this.setBackground(new Color(255,255,255));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.REVEREND_GREEN){
//			this.setBackground(new Color(135, 191, 165));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.MRS_PEACOCK){
//			this.setBackground(new Color(185, 205, 228));
//		}else if(data.getCurrentPlayer().get_name() == CharsName.PROFESSOR_PLUM){
//			this.setBackground(new Color(202, 188, 208));
//		}
//	}
}
