package blackjack.controller;

import java.util.ArrayList;
import java.util.List;

import blackjack.model.Card;
import blackjack.model.Deal;
import blackjack.model.Deck;
import blackjack.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlackJackController {

	@FXML
	Button hit;
	@FXML
	Button deal;
	@FXML
	Button stay;
	@FXML
	ImageView card1;
	@FXML
	ImageView card2;
	@FXML
	ImageView card3;
	@FXML
	ImageView card4;
	@FXML
	ImageView dCard1;
	@FXML
	ImageView dCard2;
	@FXML
	ImageView dCard3;
	@FXML
	ImageView dCard4;
	@FXML
	TextField playerName;
	@FXML
	TextField playerScore;
	@FXML
	TextField dealerName;
	@FXML
	TextField dealerScore;
	@FXML
	TextField result;
	@FXML
	TextField totalChips;
	@FXML
	TextField bet;

	private Player dealer;
	private Player player;
	private Deck deck = new Deck();
	private List<Card> cards = deck.setDeck();
	private List<Card> hand1 = new ArrayList<Card>();
	private List<Card> hand2 = new ArrayList<Card>();
	Deal dealing = new Deal();

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		player = new Player(hand1, "karl");
		dealer = new Player(hand2, "dealer");
		playerName.setText(player.getPlayerName());
		dealerName.setText(dealer.getPlayerName());
		player.setChipAmount(500);
		int chips = player.getChipAmount();
		totalChips.setText(Integer.toString(chips));
		hit.setDisable(true);
		stay.setDisable(true);
		dealerName.setEditable(false);
		playerName.setEditable(false);
		dealerScore.setEditable(false);
		playerScore.setEditable(false);
		totalChips.setEditable(false);
		result.setEditable(false);

	}

	public void dealCards(ActionEvent e) {
		Button b = (Button) e.getSource();

		String betAmount = String.valueOf(bet.getText());
		int amount = Integer.parseInt(betAmount);


		if (b == deal) {
			if(hand1.size() > 1 || hand2.size() > 1) {
				resetHands();
			}
			System.out.println(hand1.size());
			System.out.println(hand2.size());
			if (amount < player.getChipAmount()) {
				deck.shuffleDeck(cards);
				deal.setDisable(true);
				hit.setDisable(false);
				stay.setDisable(false);
				// player = new Player(hand1, "karl");
				// dealer = new Player(hand2, "dealer");

				dealer.getHand();
				hand2.add(cards.get(0));
				cards.remove(cards.get(0));
				hand2.add(cards.get(0));
				cards.remove(cards.get(0));
				dCard1.setImage(hand2.get(0).getImage());
				dCard2.setImage(hand2.get(1).getImage());

				player.getHand();
				hand1.add(cards.get(0));
				cards.remove(cards.get(0));
				hand1.add(cards.get(0));
				cards.remove(cards.get(0));
				card1.setImage(hand1.get(0).getImage());
				card2.setImage(hand1.get(1).getImage());
				System.out.println(hand1.size());
				System.out.println(hand2.size());

				int playerTotal = dealing.getPlayerTotal(hand1);
				int dealerTotal = dealing.getPlayerTotal(hand2);

				playerScore.setText(Integer.toString(playerTotal));
				dealerScore.setText(Integer.toString(dealerTotal));
			}

		}

	}

	public void hitPlayer(ActionEvent e) {
		Button b = (Button) e.getSource();
		int playerTotal = 0, dealerTotal = 0;
		int stayFlag = 0;
		String betAmount = String.valueOf(bet.getText());
		int amount = 0;
		int chips = player.getChipAmount();
		String temp;

		dealerTotal = dealing.getPlayerTotal(hand2);

		if (b == hit) {
			System.out.println(hand1.size());
			System.out.println(hand2.size());
			if (dealing.checkForEmptyDeck(cards) == true) {
				dealing.getNextCard(cards);
				cards.remove(0);
			}

			hand1.add(cards.get(0));
			cards.remove(0);
			playerTotal = dealing.getPlayerTotal(hand1);
			playerScore.setText(Integer.toString(playerTotal));

			//System.out.println("karl" + player);
			//System.out.println("dealer" + dealer);

			if (hand1.size() == 3) {
				card3.setImage(hand1.get(2).getImage());
			}

			if (hand1.size() == 4) {
				card4.setImage(hand1.get(3).getImage());
			}

			boolean hitDealer = hitDealer(playerTotal, dealerTotal);

			if (hitDealer == true) {
				hand2.add(cards.get(0));
				cards.remove(0);
				dealerTotal = dealing.getPlayerTotal(hand2);
				dealerScore.setText(Integer.toString(dealerTotal));

				if (hand2.size() == 3) {
					dCard3.setImage(hand2.get(2).getImage());
				}

				if (hand2.size() == 4) {
					dCard4.setImage(hand2.get(3).getImage());
				}
			}

			if (playerTotal > 21 || (dealerTotal == 21 && playerTotal > 21)) {
				result.setText("Lose");
				betAmount = String.valueOf(bet.getText());
				amount = Integer.parseInt(betAmount);
				temp = totalChips.getText();
				chips = Integer.parseInt(temp);
				chips = chips - amount;
				totalChips.setText(Integer.toString(chips));
				hit.setDisable(true);
				stay.setDisable(true);
				deal.setDisable(false);
			} else if (hitDealer == false && playerTotal == dealerTotal) {
				result.setText("Draw");
				betAmount = String.valueOf(bet.getText());
				amount = Integer.parseInt(betAmount);
				temp = totalChips.getText();
				chips = Integer.parseInt(temp);
				totalChips.setText(Integer.toString(chips));
				hit.setDisable(true);
				stay.setDisable(true);
				deal.setDisable(false);
			} else if ((playerTotal == 21 && dealerTotal != 21) || (playerTotal > dealerTotal && hitDealer == false) || (dealerTotal > 21)) {
				result.setText("Win");
				betAmount = String.valueOf(bet.getText());
				amount = Integer.parseInt(betAmount);
				temp = totalChips.getText();
				chips = Integer.parseInt(temp);
				chips = (amount * 2) + chips;
				totalChips.setText(Integer.toString(chips));
				hit.setDisable(true);
				stay.setDisable(true);
				deal.setDisable(false);

			}

		} else if (b == stay) {
			playerTotal = dealing.getPlayerTotal(hand1);
			dealerTotal = dealing.getPlayerTotal(hand2);
			hit.setDisable(true);
			stay.setDisable(true);
			stayFlag = 1;

		}

		while (stayFlag == 1) {
			boolean flag = hitDealer(playerTotal, dealerTotal);
			if (flag == true && dealerTotal < playerTotal) {
				hand2.add(cards.get(0));
				cards.remove(0);
				dealerTotal = dealing.getPlayerTotal(hand2);
				dealerScore.setText(Integer.toString(dealerTotal));
				stayFlag = 1;

			} else {
				stayFlag = 2;

			}

			if (hand2.size() == 3) {
				dCard3.setImage(hand2.get(2).getImage());
			}

			if (hand2.size() == 4) {
				dCard4.setImage(hand2.get(3).getImage());
			}

			if (dealerTotal > playerTotal && dealerTotal <= 21) {
				result.setText("Lose");
				betAmount = String.valueOf(bet.getText());
				amount = Integer.parseInt(betAmount);
				temp = totalChips.getText();
				chips = Integer.parseInt(temp);
				chips = chips - amount;
				//System.out.println("amountlose " + amount);
				//System.out.println("chips " + chips);
				totalChips.setText(Integer.toString(chips));
				hit.setDisable(true);
				stay.setDisable(true);
				deal.setDisable(false);
				break;

			} else if ((dealerTotal < playerTotal && (dealerTotal > 17 && dealerTotal < 21)) || (dealerTotal > 21 || 
					stayFlag == 2)) {
				result.setText("Win");
				betAmount = String.valueOf(bet.getText());
				amount = Integer.parseInt(betAmount);
				temp = totalChips.getText();
				chips = Integer.parseInt(temp);
				chips = (amount * 2) + chips;
				//System.out.println("amount wstay " + amount);
				//System.out.println("chips " + chips);
				totalChips.setText(Integer.toString(chips));
				hit.setDisable(true);
				stay.setDisable(true);
				deal.setDisable(false);
				break;

			} else if (flag == false && playerTotal == dealerTotal) {
				result.setText("Draw");
				betAmount = String.valueOf(bet.getText());
				amount = Integer.parseInt(betAmount);
				temp = totalChips.getText();
				chips = Integer.parseInt(temp);
				//System.out.println("amount " + amount);
				//System.out.println("chips " + chips);
				totalChips.setText(Integer.toString(chips));
				hit.setDisable(true);
				stay.setDisable(true);
				deal.setDisable(false);
				break;

			}

		}

	}

	private boolean hitDealer(int playerTotal, int dealerTotal) {
		if (dealerTotal < 17 && playerTotal <= 21) {
			return true;
		} else {
			return false;
		}
	}

	private void resetHands() {
		Image image = null;
		player.getHand().removeAll(hand1);
		dealer.getHand().removeAll(hand2);
		card1.setImage(image);
		card2.setImage(image);
		card3.setImage(image);
		card4.setImage(image);
		dCard1.setImage(image);
		dCard2.setImage(image);
		dCard3.setImage(image);
		dCard4.setImage(image);
		if(cards.size() == 0) {
			deck.setDeck();
			deck.shuffleDeck(cards);
			
		}
		result.clear();

	}

}
