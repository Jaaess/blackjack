# blackjack

Disclaimer: This is a personal project, not meant to be used in production and/or any other professional manner

This is a JavaFX project that also utilizes elasticsearch, kibana, and jackson. JavaFX Scene starts up when running as a java application.

Player starts with $500 dollars worth of chips. Player can place a bet against the dealer as long as the bet is < the pot of chips. Blackjack regular rules apply. Splitting and insurance do not however. Also, the dealer's cards are visible from the first round. The round is saved to elasticsearch at the end of the round and can perform data analysis on kibana on wins/losses




