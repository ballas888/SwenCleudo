package cluedo.character;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Chars {
	private Point position;
	private CharsName name;
	private String playName;
	private Boolean playable = false;
	private Boolean playing = false;
	private BufferedImage sprite;
	private ArrayList<Card> cards = new ArrayList<Card>();
	private int die1rolled = 6;
	private int die2rolled = 6;
	
	public void nowPlaying(){
		this.playing = true;
	}
	
	public void notPlaying(){
		this.playing = false;
	}
	
	public boolean isPlaying(){
		return this.playing;
	}
	
	public Chars(CharsName name,Point pos){
		this.name = name;
		this.position = pos;
	}
	public CharsName get_name(){
		return this.name;
	}
	public Point getPosition(){
		return this.position;
	}
	public void setPosition(Point p){
		this.position = p;
	}
	public void setPlayName(String name){
		this.playName = name;
	}
	
	public String getPlayName(){
		return this.playName;
	}
	
	public void setPlayable(boolean play){
		playable = play;
	}
	public boolean isPlayable(){
		return this.playable;
	}
	public void setSprite(BufferedImage img){
		this.sprite = img;
	}
	public BufferedImage getSprite(){
		return this.sprite;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public int getDieRolled(int die) {
		if(die == 1){
			return die1rolled;
		}else{
			return die2rolled;
		}
		
	}

	public void setDieRolled(int rolled, int die) {
		if(die == 1){
			this.die1rolled = rolled;
		}else{
			this.die2rolled = rolled;
		}
	}
}
