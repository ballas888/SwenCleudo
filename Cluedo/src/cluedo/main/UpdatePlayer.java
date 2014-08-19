package cluedo.main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import cluedo.character.Chars;
import cluedo.main.Game.Room;
import cluedo.main.Tile.Direction;
import cluedo.search.Search;
/**
 * This handles players moves either with the mouse or keys. *
 */
public class UpdatePlayer {
	public enum mFunc {
		MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, MOVE_MOUSE
	}
	
	/**
	 * This gets the Mouse input position and the calls Astar search to find a path 
	 * for the player
	 * @param mFunc
	 * @param Data object that holds it all
	 * @param The point of the mouse.
	 * @return returns the path for the player
	 */
	public Stack<Point> updatePlayerMove(mFunc func, Data data,Point point){
		Stack<Point> points = new Stack<Point>();

		Search search = new Search();
		Tile[][] tiles = data.getTiles();
		//System.out.println("CurPlayer: "+data.getCurrentPlayer().get_name());
		//System.out.println(data.getCurrentPlayer().getPosition().x+" "+data.getCurrentPlayer().getPosition().y);
		if(func == mFunc.MOVE_MOUSE){
			Tile goal = tiles[point.y][point.x];
			int x = data.getCurrentPlayer().getPosition().x;
			int y = data.getCurrentPlayer().getPosition().y;
			Tile from = tiles[y][x];
			points = search.search(from, goal, data);
		}
		return points;
	}
	/**
	 * Updates the player move based on the key direction pressed
	 * @param mFunc
	 * @param Data object that holds it all
	 * @return Returns true if a move was done successfully
	 */
	public boolean updatePlayerMove(mFunc func, Data data) {
		boolean updated = false;
		Tile currTile = null;
		ArrayList<Chars> chars = data.getPlayChars();
		Tile[][] tiles = data.getTiles();
//		for (Chars c : chars) {
//			if(c.isPlaying()){
		Chars c = data.getCurrentPlayer();
			int oldX = c.getPosition().x;
			int oldY = c.getPosition().y;
			Tile tileOld = tiles[oldY][oldX];
			if (func == mFunc.MOVE_UP) {
				if (tileOld.can_move_up()) {
					Tile tileNew = tileOld.get_neigh(Direction.UP);
					if (tileNew == null) {
						System.out.println("TileNew is null  25 UpdatePlayer");
					} else if(!tileNew.HasChar()){
						c.setPosition(tileNew.getArrayPos());
						tileOld.setHasChar(false);
						tileNew.setHasChar(true);
						updated = true;
						currTile = tileNew;
					}
				}
			} else if (func == mFunc.MOVE_DOWN) {
				if (tileOld.can_move_down()) {
					Tile tileNew = tileOld.get_neigh(Direction.DOWN);
					if (tileNew == null) {
						System.out.println("TileNew is null  25 UpdatePlayer");
					} else if(!tileNew.HasChar()){
						c.setPosition(tileNew.getArrayPos());
						tileOld.setHasChar(false);
						tileNew.setHasChar(true);
						updated = true;
						currTile = tileNew;
					}
				}
			} else if (func == mFunc.MOVE_RIGHT) {
				if (tileOld.can_move_right()) {
					Tile tileNew = tileOld.get_neigh(Direction.RIGHT);
					if (tileNew == null) {
						System.out.println("TileNew is null  25 UpdatePlayer");
					} else if(!tileNew.HasChar()){
						c.setPosition(tileNew.getArrayPos());
						tileOld.setHasChar(false);
						tileNew.setHasChar(true);
						updated = true;
						currTile = tileNew;
					}
				}
			} else if (func == mFunc.MOVE_LEFT) {
				if (tileOld.can_move_left()) {
					Tile tileNew = tileOld.get_neigh(Direction.LEFT);
					if (tileNew == null) {
						System.out.println("Direction Blocked");
						
					} else if(!tileNew.HasChar()){
						c.setPosition(tileNew.getArrayPos());
						tileOld.setHasChar(false);
						tileNew.setHasChar(true);
						updated = true;
						currTile = tileNew;
					}
				}
			}
		//}
		//}
			return updated;
	}

}
