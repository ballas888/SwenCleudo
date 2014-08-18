package cluedo.load;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import cluedo.character.Card;
import cluedo.character.CardName;
import cluedo.character.CardType;
import cluedo.character.Chars;
import cluedo.main.Data;

public class LoadCards {
	
	private final CardName[] names = {CardName.KITCHEN, CardName.BALL_ROOM, CardName.CONSERVATORY, CardName.DINING_ROOM, CardName.BILLARD_ROOM,
			CardName.LIBRARY, CardName.LOUNGE, CardName.HALL, CardName.STUDY, CardName.MRS_WHITE_C, CardName.MISS_SCARLET_C, CardName.COLONEL_MUSTARD_C, CardName.REVEREND_GREEN_C,
			CardName.MRS_PEACOCK_C, CardName.PROFESSOR_PLUM_C, CardName.CANDLESTICK, CardName.DAGGER, CardName.LEAD_PIPE, CardName.REVOLVER, CardName.ROPE, CardName.SPANNER};
	
	public void loadCard(Data data){
		ArrayList<Card> rooms = new ArrayList<Card>();
		ArrayList<Card> chars = new ArrayList<Card>();
		ArrayList<Card> weapons = new ArrayList<Card>();
		for(int i = 0; i<names.length; i++){
			if(i>=0 && i <9){
				Card card = new Card(names[i],CardType.ROOM_CARD);
				card.setImage(new LoadImage().load_image(card.getName()));
				rooms.add(card);
			}else if(i>=9 && i<15){
				Card card = new Card(names[i],CardType.CHAR_CARD);
				card.setImage(new LoadImage().load_image(card.getName()));
				chars.add(card);
			}else{
				Card card = new Card(names[i],CardType.WEAPON_CARD);
				card.setImage(new LoadImage().load_image(card.getName()));
				weapons.add(card);
			}
		}
		int roomRan = (int)(Math.random()*rooms.size());
		int charsRan = (int)(Math.random()*chars.size());
		int weaponsRan = (int)(Math.random()*weapons.size());
		Card middleRoomCard = rooms.remove(roomRan);
		Card middleCharCard = chars.remove(charsRan);
		Card middleWeaponCard = weapons.remove(weaponsRan);
		
		data.setMRoomCard(middleRoomCard);
		data.setMCharCard(middleCharCard);
		data.setMWeapCard(middleWeaponCard);
		
		System.out.println(middleRoomCard.getName());
		System.out.println(middleCharCard.getName());
		System.out.println(middleWeaponCard.getName());
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.addAll(rooms);
		cards.addAll(chars);
		cards.addAll(weapons);
		Collections.shuffle(cards, new Random(System.currentTimeMillis()));
		Collections.shuffle(cards, new Random(System.currentTimeMillis()));
		divideCards(data, cards);
		
		
	}
	
	private void divideCards(Data data, ArrayList<Card> cards){
		ArrayList<Chars> chars = data.getPlayChars();
		while(!cards.isEmpty()){
			for(int i = 0; i < chars.size();i++){
				if(!cards.isEmpty()){
					chars.get(i).getCards().add(cards.remove(cards.size()-1));
				}
			}
		}
		
		for(int i = 0; i < chars.size();i++){
			Chars c = chars.get(i);
			//System.out.println(c.get_name());
			ArrayList<Card> cs = c.getCards();
			for(int j = 0; j < cs.size();j++){
				Card crd = cs.get(j);
				//System.out.println("	"+crd.getName());
			}
		}
	}
	
	
}
