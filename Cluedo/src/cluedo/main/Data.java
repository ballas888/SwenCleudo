package cluedo.main;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import cluedo.character.Chars;
import cluedo.load.LoadImage;
import cluedo.load.LoadMap;

public class Data {
	private BufferedImage map_image;
	private ArrayList<Chars> allChars = new ArrayList<Chars>();
	private ArrayList<Chars> playChars = new ArrayList<Chars>();
	public final LoadImage loadImage = new LoadImage();
	public final LoadMap loadMap = new LoadMap();
	private Tile[][] tiles;
	private int tileSize;
	private Chars currentPlayer;
	private ArrayList<Point> mousePath = new ArrayList<Point>();
	private boolean isSearching = false;
	
	public void setMousePath(ArrayList<Point> points){
		this.mousePath = points;
	}
	
	public ArrayList<Point> getMousePath(){
		return this.mousePath;
	}
	
	public void setCurrentPlayer(Chars ch){
		this.currentPlayer = ch;
	}
	
	public Chars getCurrentPlayer(){
		return this.currentPlayer;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	public Tile[][] getTiles() {
		return tiles;
	}
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	public ArrayList<Chars> getPlayChars() {
		return playChars;
	}
	public void setPlayChars(ArrayList<Chars> playChars) {
		this.playChars = playChars;
	}
	public ArrayList<Chars> getAllChars() {
		return allChars;
	}
	public void setAllChars(ArrayList<Chars> allChars) {
		this.allChars = allChars;
	}
	public BufferedImage getMap_image() {
		return map_image;
	}
	public void setMap_image(BufferedImage map_image) {
		this.map_image = map_image;
	}

	public boolean isSearching() {
		return isSearching;
	}

	public void setSearching(boolean isSearching) {
		this.isSearching = isSearching;
	}
	
}
