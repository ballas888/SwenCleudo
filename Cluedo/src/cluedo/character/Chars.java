package cluedo.character;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Chars {
	private Point position;
	private CharsName name;
	private String playName;
	private Boolean playable = false;
	private Boolean playing = false;
	private BufferedImage sprite;
	
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
}
