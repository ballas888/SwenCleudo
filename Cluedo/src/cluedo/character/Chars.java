package cluedo.character;

import java.awt.Point;

public class Chars {
	private Point start_point;
	private CharsName name;
	private String playName;
	private Boolean playable = false;
	
	public Chars(CharsName name,Point pos){
		this.name = name;
		this.start_point = pos;
	}
	public CharsName get_name(){
		return this.name;
	}
	public Point get_start_pos(){
		return this.start_point;
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
	public boolean getPlayable(){
		return this.playable;
	}
}
