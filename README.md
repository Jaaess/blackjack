# blackjack

This is a JavaFX project that also utilizes elasticsearch, kibana, and jackson.

JavaFX Scene starts up when running as a java application.

Player starts with $500 dollars worth of chips. Player can place a bet against the dealer as long as the bet is < the pot of chips.

Blackjack regular rules apply. Splitting and insurance do not however. Also, the dealer's cards are visible from the first round.

A POJO object (round)'s attributes are set for each round (player name, dealer name, player cards, dealer cards, and result of round)

The POJO is converted into a JSON string using the Jackson mapper and then sent to an elasticsearch index.

Not included in this project is the elasticsearch server setup (potential pdf on how to set one up), and the kibana instance that will be used to visualize all this data.
