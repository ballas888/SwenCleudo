package cluedo.main;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import cluedo.character.Card;
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
	private double tileSize;
	private int currentPlayer = 0;
	private ArrayList<Point> mousePath = new ArrayList<Point>();
	private boolean isSearching = false;
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Card middleRoomCard;
	private Card middleCharCard;
	private Card middleWeapCard;
	
	public void setCards(ArrayList<Card> cards){
		this.cards = cards;
	}
	
	public ArrayList<Card> getCards(){
		return this.cards;
	}

	public void setMousePath(ArrayList<Point> points) {
		this.mousePath = points;
	}

	public void populateChoosen(Data data){
		Tile[][] tiles = data.getTiles();
		for(int i = 0;i < allChars.size(); i++){
			if(allChars.get(i).isPlayable()){
				Chars c = allChars.get(i);
				tiles[c.getPosition().y][c.getPosition().x].setHasChar(true);
				playChars.add(allChars.get(i));
			}
		}
		playChars.get(0).nowPlaying();
	}

	public ArrayList<Point> getMousePath() {
		return this.mousePath;
	}

	public void nextPlayer() {
		//set current player to not playing
		playChars.get(currentPlayer).notPlaying();

		//move to next player
		if(this.currentPlayer + 1 >= playChars.size()){
			this.currentPlayer = 0;
		}else{
			this.currentPlayer++;
		}
		//set current player to playing
		playChars.get(currentPlayer).nowPlaying();
	}

	public Chars getCurrentPlayer() {
		return playChars.get(currentPlayer);
	}

	public double getTileSize() {
		return tileSize;
	}

	public void setTileSize(double tileSize) {
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

	public Card getMRoomCard() {
		return middleRoomCard;
	}

	public void setMRoomCard(Card middleRoomCard) {
		this.middleRoomCard = middleRoomCard;
	}

	public Card getMCharCard() {
		return middleCharCard;
	}

	public void setMCharCard(Card middleCharCard) {
		this.middleCharCard = middleCharCard;
	}

	public Card getMWeapCard() {
		return middleWeapCard;
	}

	public void setMWeapCard(Card middleWeapCard) {
		this.middleWeapCard = middleWeapCard;
	}

}
