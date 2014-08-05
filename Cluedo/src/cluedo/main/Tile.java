package cluedo.main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import cluedo.main.Game.Room;

public class Tile {
	private int x;
	private int y;
	private boolean can_up = true;
	private boolean can_down = true;
	private boolean can_left = true;
	private boolean can_right = true;
	private List<Tile> neigh =new ArrayList<Tile>();
	private Room in_room;	
	
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void add_neigh(Tile tile){
		neigh.add(tile);
	}
	
	public List<Tile> get_neighs(){
		return this.neigh;
	}
	
	public void set_room(Room room){
		this.in_room = room;
	}
	
	public Room get_room(){
		return this.in_room;
	}
	
	public Point get_pos(){
		return new Point(x,y);
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
