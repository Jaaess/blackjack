package blackjack.model;

import javafx.scene.image.Image;

/*
 * Card object. Attributes include rank, suit, color, and image
 * Setters and getters for each attribute and a constructor
 */

public class Card {
	private String rank;
	private String suit;
	private String color;
	private Image image;
	
	
	public Card(String rnk, String st, String col, Image img) {
	    rank = rnk;
		suit = st;
		color = col;
		image = img;
		
	}
	

	public Image getImage() {
		return image;
	}



	public void setImage(Image image) {
		this.image = image;
	}



	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		return rank + " " + suit + " " + color;
	}

}
