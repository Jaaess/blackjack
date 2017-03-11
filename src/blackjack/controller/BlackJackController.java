package blackjack.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import com.fasterxml.jackson.core.JsonGenerationException;

import blackjack.model.Card;
import blackjack.model.Deal;
import blackjack.model.Deck;
import blackjack.model.Player;
import blackjack.model.Round;
import blackjack.utils.EsConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlackJackController {

	/*
	 * All of the FXML buttons, FXML ImageViews, and FXML TextFields used in the view
	 */
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

	
	/*
	 * Method to initialize the game board after it loads. Will set player name and give player $500 chips
	 */
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
	
	/*
	 * Method to deal cards to dealer and player
	 * Uses ActionEvent object to trigger boolean expressions based on which button id user clicks
	 * If user clicks deal ---> reset hand if user's hand is > 0,
	 *  deal 2 cards each for dealer and player, 
	 *  set images for those cards,
	 *  and set player total after dealing first hand
	 * 
	 */

	public void dealCards(ActionEvent e) {
		Button b = (Button) e.getSource();

		String betAmount = String.valueOf(bet.getText());
		int amount = Integer.parseInt(betAmount);
		
		if(cards.size() == 0) {
			System.out.println("out of cards");
			deck.setDeck();
			deck.shuffleDeck(cards);
			
		}
		

		if (b == deal) {
			if(hand1.size() > 0 || hand2.size() > 0) {
				resetHands();
			}
			//System.out.println(hand1.size());
			//System.out.println(hand2.size());
			if (amount < player.getChipAmount()) {
				
				if(cards.size() == 0) {
					System.out.println("out of cards");
					deck.setDeck();
					deck.shuffleDeck(cards);	
				}
				
				deck.shuffleDeck(cards);
				deal.setDisable(true);
				hit.setDisable(false);
				stay.setDisable(false);
				// player = new Player(hand1, "karl");
				// dealer = new Player(hand2, "dealer");

				dealer.getHand();
				
				
				if(cards.size() != 0 && cards.size() != 1) {
					hand2.add(cards.get(0));
					cards.remove(cards.get(0));
					dCard1.setImage(hand2.get(0).getImage());
					hand2.add(cards.get(1));
					cards.remove(cards.get(1));
					dCard2.setImage(hand2.get(1).getImage());
				}


				player.getHand();
				if(cards.size() != 0 && cards.size() != 1) {
					hand1.add(cards.get(0));
					cards.remove(cards.get(0));
					card1.setImage(hand1.get(0).getImage());
	
					hand1.add(cards.get(0));
					cards.remove(cards.get(0));
					card2.setImage(hand1.get(1).getImage());
				}
				
				//System.out.println(hand1.size());
				//System.out.println(hand2.size());

				int playerTotal = dealing.getPlayerTotal(hand1);
				int dealerTotal = dealing.getPlayerTotal(hand2);

				playerScore.setText(Integer.toString(playerTotal));
				dealerScore.setText(Integer.toString(dealerTotal));
			}

		}

	}
	
	
	/*
	 * Method to hit player and dealer
	 * Uses ActionEvent object to trigger boolean expressions based on which button id user clicks
	 * If user clicks hit ---> deals card for player, 
	 * 	deals card for dealer depending on dealer's hand 
	 *  set images for cards dealt
	 *  and set player total after hitting
	 *  finally, checks user score after each hit if user can call hit again. 
	 *  if user loses, then ends round
	 *  
	 *  If user clicks stay ---> stops player from hitting or staying again
	 *   if dealer needs to hit, dealer will hit
	 *   finally, checks user score after dealer is done hitting if dealer needs to hit 
	 *   if user wins/loses, then ends round
	 * 
	 */

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
			//System.out.println(hand1.size());
			//System.out.println(hand2.size());
			if (dealing.checkForEmptyDeck(cards) == true) {
				dealing.getNextCard(cards);
			}
			
			if(cards.size() == 0) {
				System.out.println("out of cards");
				deck.setDeck();
				deck.shuffleDeck(cards);
				
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
	
	/*
	 * Boolean method to check if should hit dealer or not
	 * 
	 */

	private boolean hitDealer(int playerTotal, int dealerTotal) {
		if (dealerTotal < 17 && playerTotal <= 21 && hit.isDisabled() == true) {
			return true;
		} else {
			return false;
		}
	}
	

	/*
	 * Method to reset the hand, removes all cards from both dealer and player hands, and resets images
	 * 
	 */

	private void resetHands() {
		
		String resultOfGame = result.getText();
		
		//Round round = createRound(dealer, player, resultOfGame);
		//String json = jsonString(round);
		
		//IndexResponse response = sendToElastic(json);
		
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
	/*
	 * Method to create round object which is all data from round 
	 * (player and dealer names, player and dealer cards, and roud outcome
	 */
	
	private static Round createRound(Player dealer, Player player, String result) {
		
		Round round = new Round();
		round.setDealerName(dealer.getPlayerName());
		round.setPlayerName(player.getPlayerName());
		round.setDealerHand(dealer.getHand());
		round.setPlayerHand(player.getHand());
		round.setRoundOutcome(result);
	
		return round;
		
	}
	
	/*
	 * Method to convert a round object to a JSON string
	 */
	
	private String jsonString(Round round) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {

			//Convert object to JSON string
			String jsonString = mapper.writeValueAsString(round);
			//System.out.println(jsonInString);

			//Convert object to JSON string and pretty print
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(round);
			//System.out.println(jsonString);
			
			return jsonString;


		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	
		return null;
	}
	
	/*
	 * Method to send round object to elastic, called every time round ends in resetHands() method
	 * 
	 */
	
	private IndexResponse sendToElastic(String jsonString) {
	
		
		Client client = EsConnector.connectToClient();
		
		IndexResponse response = client.prepareIndex("test", "output").setSource(jsonString)
				.execute()
				.actionGet();
				
		return response;
		
	}

}
