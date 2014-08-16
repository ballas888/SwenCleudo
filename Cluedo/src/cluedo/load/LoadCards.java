package cluedo.load;

import java.util.ArrayList;

import cluedo.character.Card;
import cluedo.character.CardName;
import cluedo.character.CardType;

public class LoadCards {
	
	private final CardName[] names = {CardName.KITCHEN, CardName.BALL_ROOM, CardName.CONSERVATORY, CardName.DINING_ROOM, CardName.BILLARD_ROOM,
			CardName.LIBRARY, CardName.LOUNGE, CardName.HALL, CardName.STUDY, CardName.MRS_WHITE_C, CardName.MISS_SCARLET_C, CardName.COLONEL_MUSTARD_C, CardName.REVEREND_GREEN_C,
			CardName.MRS_PEACOCK_C, CardName.PROFESSOR_PLUM_C, CardName.CANDLESTICK, CardName.DAGGER, CardName.LEAD_PIPE, CardName.REVOLVER, CardName.ROPE, CardName.SPANNER};
	
	public ArrayList<Card> LoadCards(){
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i = 0; i<names.length; i++){
			if(i>=0 && i <9){
				Card card = new Card(names[i],CardType.ROOM_CARD);
				card.setImage(new LoadImage().load_image(card.getName()));
				cards.add(card);
			}else if(i>=9 && i<15){
				Card card = new Card(names[i],CardType.CHAR_CARD);
				card.setImage(new LoadImage().load_image(card.getName()));
				cards.add(card);
			}else{
				Card card = new Card(names[i],CardType.WEAPON_CARD);
				card.setImage(new LoadImage().load_image(card.getName()));
				cards.add(card);
			}
		}
		for(Card c : cards){
			System.out.println(c.getName() + " TYPE: " + c.getType());
		}
		return cards;
	}
}
