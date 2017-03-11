package blackjack.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Player object. Player's attributes are a player's hand (list of cards), player name, and chip amount
 * Getters and setters for each attribute
 * Constructor that omits chip amount since impractical to give dealer chips when instantiating
 * player object as a dealer
 */

public class Player {
	
	private List<Card> hand = new ArrayList<Card>();
	private String playerName;
	private int chipAmount;
	
	
	public Player(List<Card> hnd, String name) {
		playerName = name;
		hand = hnd;
	}

	public int getChipAmount() {
		return chipAmount;
	}

	public void setChipAmount(int chipAmount) {
		this.chipAmount = chipAmount;
	}

	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}


	@Override
	public String toString() {
		return "Player [hand=" + hand + "]";
	}
	
	

	
}
