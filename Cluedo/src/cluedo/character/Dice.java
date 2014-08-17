package cluedo.character;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

import cluedo.load.LoadImage;

public class Dice {
	private BufferedImage die1;
	private BufferedImage die2;
	private BufferedImage die3;
	private BufferedImage die4;
	private BufferedImage die5;
	private BufferedImage die6;
	
	public Dice(){
		createDice();
	}
	
	private void createDice(){
		die1 = new LoadImage().load_image(DiceType.DIE_1);
		die2 = new LoadImage().load_image(DiceType.DIE_2);
		die3 = new LoadImage().load_image(DiceType.DIE_3);
		die4 = new LoadImage().load_image(DiceType.DIE_4);
		die5 = new LoadImage().load_image(DiceType.DIE_5);
		die6 = new LoadImage().load_image(DiceType.DIE_6);
	}
	
	public BufferedImage getDie(int num){
		assert(num >=1 && num <=6);
		Class<? extends Dice> c = this.getClass();
		try {
			Field f = c.getDeclaredField("die"+num);
			return (BufferedImage) f.get(this);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
