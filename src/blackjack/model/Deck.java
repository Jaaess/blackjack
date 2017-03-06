package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

public class Deck {

	private List<Card> deck = new ArrayList<Card>();
	private String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
	private String[] suit = { "Diamond", "Heart", "Club", "Spade" };
	private String[] color = { "R", "R", "B", "B" };
	private Card card;
	private List<String> listOfPaths = new ArrayList<String>();
	private List<Image> imageList = new ArrayList<Image>();
	private List<Image> imageList2 = new ArrayList<Image>();
	private List<Image> imageList3 = new ArrayList<Image>();
	private List<Image> imageList4 = new ArrayList<Image>();

	public List<Card> getDeck() {
		return deck;
	}

	private List<Image> getImageListD() {

		String temp;
		Image image = null;

		for (int i = 0; i < 13; i++) {
			temp = getPaths().get(i);
			image = new Image(temp);
			imageList.add(image);
		}

		return imageList;
	}

	private List<Image> getImageListH() {

		String temp;
		Image image = null;

		for (int i = 13; i < 26; i++) {
			temp = getPaths().get(i);
			image = new Image(temp);
			imageList2.add(image);
		}

		return imageList2;
	}
	
	private List<Image> getImageListC() {

		String temp;
		Image image = null;

		for (int i = 26; i < 39; i++) {
			temp = getPaths().get(i);
			image = new Image(temp);
			imageList3.add(image);
		}

		return imageList3;
	}
	
	private List<Image> getImageListS() {

		String temp;
		Image image = null;

		for (int i = 39; i < 52; i++) {
			temp = getPaths().get(i);
			image = new Image(temp);
			imageList4.add(image);
		}

		return imageList4;
	}


	public List<Card> setDeck() {
		getImageListD();
		getImageListH();
		getImageListC();
		getImageListS();

		for (int i = 0; i < rank.length; i++) {
			
			card = new Card(rank[i], suit[0], color[0], imageList.get(i));

			deck.add(card);

		}

		for (int i = 0; i < rank.length; i++) {

			card = new Card(rank[i], suit[1], color[1], imageList2.get(i));

			deck.add(card);

		}

		for (int i = 0; i < rank.length; i++) {

			card = new Card(rank[i], suit[2], color[2], imageList3.get(i));

			deck.add(card);

		}

		for (int i = 0; i < rank.length; i++) {

			card = new Card(rank[i], suit[3], color[3], imageList4.get(i));

			deck.add(card);

		}

		return deck;
	}

	public void shuffleDeck(List<Card> cards) {
		Collections.shuffle(cards);
	}

	private List<String> getPaths() {

		listOfPaths.add("2_of_diamonds.png");
		listOfPaths.add("3_of_diamonds.png");
		listOfPaths.add("4_of_diamonds.png");
		listOfPaths.add("5_of_diamonds.png");
		listOfPaths.add("6_of_diamonds.png");
		listOfPaths.add("7_of_diamonds.png");
		listOfPaths.add("8_of_diamonds.png");
		listOfPaths.add("9_of_diamonds.png");
		listOfPaths.add("10_of_diamonds.png");
		listOfPaths.add("jack_of_diamonds.png");
		listOfPaths.add("queen_of_diamonds.png");
		listOfPaths.add("king_of_diamonds.png");
		listOfPaths.add("ace_of_diamonds.png");
		listOfPaths.add("2_of_hearts.png");
		listOfPaths.add("3_of_hearts.png");
		listOfPaths.add("4_of_hearts.png");
		listOfPaths.add("5_of_hearts.png");
		listOfPaths.add("6_of_hearts.png");
		listOfPaths.add("7_of_hearts.png");
		listOfPaths.add("8_of_hearts.png");
		listOfPaths.add("9_of_hearts.png");
		listOfPaths.add("10_of_hearts.png");
		listOfPaths.add("jack_of_hearts.png");
		listOfPaths.add("queen_of_hearts.png");
		listOfPaths.add("king_of_hearts.png");
		listOfPaths.add("ace_of_hearts.png");
		listOfPaths.add("2_of_clubs.png");
		listOfPaths.add("3_of_clubs.png");
		listOfPaths.add("4_of_clubs.png");
		listOfPaths.add("5_of_clubs.png");
		listOfPaths.add("6_of_clubs.png");
		listOfPaths.add("7_of_clubs.png");
		listOfPaths.add("8_of_clubs.png");
		listOfPaths.add("9_of_clubs.png");
		listOfPaths.add("10_of_clubs.png");
		listOfPaths.add("jack_of_clubs.png");
		listOfPaths.add("queen_of_clubs.png");
		listOfPaths.add("king_of_clubs.png");
		listOfPaths.add("ace_of_clubs.png");
		listOfPaths.add("2_of_spades.png");
		listOfPaths.add("3_of_spades.png");
		listOfPaths.add("4_of_spades.png");
		listOfPaths.add("5_of_spades.png");
		listOfPaths.add("6_of_spades.png");
		listOfPaths.add("7_of_spades.png");
		listOfPaths.add("8_of_spades.png");
		listOfPaths.add("9_of_spades.png");
		listOfPaths.add("10_of_spades.png");
		listOfPaths.add("jack_of_spades.png");
		listOfPaths.add("queen_of_spades.png");
		listOfPaths.add("king_of_spades.png");
		listOfPaths.add("ace_of_spades.png");

		return listOfPaths;
	}

}
