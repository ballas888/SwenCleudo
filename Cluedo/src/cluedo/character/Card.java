package cluedo.character;
//Richard
import java.awt.image.BufferedImage;

public class Card {
	private BufferedImage cardImg;
	private final CardName name;
	private final CardType type;
	
	public Card(CardName name, CardType type){
		this.name = name;
		this.type = type;
	}
	
	public CardName getName(){
		return this.name;
	}
	
	public CardType getType(){
		return this.type;
	}
	
	public void setImage(BufferedImage img){
		this.cardImg = img;
	}
	public BufferedImage getImage(){
		return this.cardImg;
	}

}
