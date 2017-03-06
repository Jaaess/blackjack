package blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Deal {
	
	private Deck deck = new Deck();
	private List<Card> cards = deck.setDeck();
	


	public List<Card> getNextCard(List<Card> cards) {
		
		List<Card> hand = new ArrayList<Card>();
	
		hand.add(cards.get(0));
		
		return hand;
		
	}
	
	public boolean checkForEmptyDeck(List<Card> cards) {
		if(cards.size() != 0) {
			return true;
		}
		else {
			return false;
		}
		
	}

	
	public String convertCard(String card) {
		if(card.equals("J") || card.equals("Q") ||  card.equals("K")) {
			card = "10";
			
		}
		/*else if(card.equals("A")) {
			card = "1";
		}*/
		else if(card.equals("10")){
			card = card.substring(0, 2);
		}
		else {
			card = card.substring(0, 1);
		}
		
		return card;
		
	}
	
	public int getPlayerTotal(List<Card> cards) {
		String temp, temp2;
		int tempValue = 0, tempValue2 = 0, total = 0;
		
		for(int i = 0; i < cards.size(); i++) {
			if(!cards.get(i).getRank().equals("A")) {
				temp = convertCard(cards.get(i).getRank());
				tempValue = Integer.parseInt(temp);
				System.out.println(tempValue);
				
			}
			
			if(cards.get(i).getRank().equals("A")) {
				if(cards.size() == 2) {
					temp2 = "11";
					tempValue = Integer.parseInt(temp2);
					System.out.println(tempValue);
					
				}
				else {
					temp2 = "1";
					tempValue = Integer.parseInt(temp2);
					System.out.println(tempValue);
					
				}
				
				
			}
			
	
			//temp = convertCard(cards.get(i).getRank());
			//tempValue = Integer.parseInt(temp);
			total += tempValue;
			
		}
		//System.out.println(total);
		return total;
		
		
	}


}
