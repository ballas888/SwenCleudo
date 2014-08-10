package cluedo.character;

import java.awt.Point;

public class Chars {
	private Point start_point;
	private CharsName name;
	
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
}
