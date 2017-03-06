package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private List<Card> hand = new ArrayList<>();
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
