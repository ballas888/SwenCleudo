package cluedo.main;

import cluedo.main.Game.Room;

public class Tile {
	private int x;
	private int y;
	private boolean can_up = true;
	private boolean can_down = true;
	private boolean can_left = true;
	private boolean can_right = true;
	private Room in_room;
	
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void set_can_up(boolean bool){
		this.can_up = bool;
	}
	
	public void set_can_down(boolean bool){
		this.can_down = bool;
	}
	
	public void set_can_left(boolean bool){
		this.can_left = bool;
	}
	
	public void set_can_right(boolean bool){
		this.can_right = bool;
	}
	
	public boolean can_move_up(){
		return this.can_up;
	}
	
	public boolean can_move_down(){
		return this.can_down;
	}
	
	public boolean can_move_right(){
		return this.can_right;
	}
	
	public boolean can_move_left(){
		return this.can_left;
	}
}
