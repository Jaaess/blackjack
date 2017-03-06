package blackjack.model;

import java.util.List;

/*
 * Class to be used as pojo to convert to JSON object and then send to Elasticsearch
 */

public class Round {
	
	private List<Card> playerHand;
	private List<Card> dealerHand;
	private String playerName;
	private String dealerName;
	private String roundOutcome;
	
	public List<Card> getPlayerHand() {
		return playerHand;
	}
	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}
	public List<Card> getDealerHand() {
		return dealerHand;
	}
	public void setDealerHand(List<Card> dealerHand) {
		this.dealerHand = dealerHand;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getRoundOutcome() {
		return roundOutcome;
	}
	public void setRoundOutcome(String roundOutcome) {
		this.roundOutcome = roundOutcome;
	}
	@Override
	public String toString() {
		return "Round [playerHand=" + playerHand + ", dealerHand=" + dealerHand + ", playerName=" + playerName
				+ ", dealerName=" + dealerName + ", roundOutcome=" + roundOutcome + "]";
	}
	
	
	
}
