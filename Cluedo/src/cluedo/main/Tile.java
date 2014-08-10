package cluedo.main;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import cluedo.main.Game.Room;

public class Tile {
	private Point2D pos;
	private boolean can_up = true;
	private boolean can_down = true;
	private boolean can_left = true;
	private boolean can_right = true;
	private Tile left_neigh;
	private Tile right_neigh;
	private Tile up_neigh;
	private Tile down_neigh;
	private Room in_room;
	private boolean is_trap = false;
	private Point trap_location;
	private double ratio;
	
	public void setRatio(double ratio){
		this.ratio = ratio;
	}
	
	public double getRatio(){
		return this.ratio;
	}

	public void setPosition(Point2D p){
		this.pos = p;
	}
	
	public void set_trap_location(Point p){
		this.trap_location = p;
	}
	
	public Point get_trap_loc(){
		return this.trap_location;
	}
	
	public void set_trap(boolean bool){
		this.is_trap = bool;
	}
	
	public void add_neigh(Tile neigh,String dir){
		if(dir.equals("left")){
			left_neigh = neigh;
		}else if(dir.equals("right")){
			right_neigh = neigh;
		}else if(dir.equals("up")){
			up_neigh = neigh;
		}else if(dir.equals("down")){
			down_neigh = neigh;
		}
	}
	
	public Tile get_neigh(String dir){
		if(dir.equals("left")){
			return left_neigh;
		}else if(dir.equals("right")){
			return right_neigh;
		}else if(dir.equals("up")){
			return up_neigh;
		}else{
			return down_neigh;
		}
	}
	
	public boolean is_trap(){
		return this.is_trap;
	}
		
	public void set_room(Room room){
		this.in_room = room;
	}
	
	public Room get_room(){
		return this.in_room;
	}
	
	public Point2D getPos(){
		return pos;
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
