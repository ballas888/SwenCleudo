package cluedo.main;

import java.awt.Point;
import java.util.ArrayList;

import cluedo.character.Chars;
import cluedo.main.Tile.Direction;

public class UpdatePlayer {
	public enum mFunc {
		MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, MOVE_MOUSE
	}
	
	public void updatePlayerMove(mFunc func, Data data,Point point){
		Tile[][] tiles = data.getTiles();
		if(func == mFunc.MOVE_MOUSE){
			Tile tile = tiles[point.y][point.x];			
		}
	}

	public void updatePlayerMove(mFunc func, Data data) {
		ArrayList<Chars> chars = data.getAllChars();
		Tile[][] tiles = data.getTiles();
		for (Chars c : chars) {
			int oldX = c.getPosition().x;
			int oldY = c.getPosition().y;
			Tile tileOld = tiles[oldY][oldX];
			if (func == mFunc.MOVE_UP) {
				if (tileOld.can_move_up()) {
					Tile tileNew = tileOld.get_neigh(Direction.UP);
					if (tileNew == null) {
						System.out.println("TileNew is null  25 UpdatePlayer");
					} else {
						c.setPosition(tileNew.getArrayPos());
					}
				}
			} else if (func == mFunc.MOVE_DOWN) {
				if (tileOld.can_move_down()) {
					Tile tileNew = tileOld.get_neigh(Direction.DOWN);
					if (tileNew == null) {
						System.out.println("TileNew is null  25 UpdatePlayer");
					} else {
						c.setPosition(tileNew.getArrayPos());
					}
				}
			} else if (func == mFunc.MOVE_RIGHT) {
				if (tileOld.can_move_right()) {
					Tile tileNew = tileOld.get_neigh(Direction.RIGHT);
					if (tileNew == null) {
						System.out.println("TileNew is null  25 UpdatePlayer");
					} else {
						c.setPosition(tileNew.getArrayPos());
					}
				}
			} else if (func == mFunc.MOVE_LEFT) {
				if (tileOld.can_move_left()) {
					Tile tileNew = tileOld.get_neigh(Direction.LEFT);
					if (tileNew == null) {
						System.out.println("TileNew is null  25 UpdatePlayer");
					} else {
						c.setPosition(tileNew.getArrayPos());
					}
				}
			}
		}
	}

}
