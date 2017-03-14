# blackjack

Disclaimer: This is a personal project, not meant to be used in production and/or any other professional manner

This is a JavaFX project that also utilizes elasticsearch, kibana, and jackson. JavaFX Scene starts up when running as a java application.

Player starts with $500 dollars worth of chips. Player can place a bet against the dealer as long as the bet is < the pot of chips. Blackjack regular rules apply. Splitting and insurance do not however. Also, the dealer's cards are visible from the first round. The round is saved to elasticsearch at the end of the round and can perform data analysis on kibana on wins/losses

<img width="801" alt="screen shot 2017-03-13 at 10 10 20 pm" src="https://cloud.githubusercontent.com/assets/26029285/23882981/8ce3e02e-083a-11e7-8028-e7cea60ebfcd.png">


#Example of using Kibana chart:

In this example you can see that ~22% of the cards I was dealt when I won were aces, also that I only won ~45 % of the time :(


<img width="884" alt="screen shot 2017-03-13 at 10 19 13 pm" src="https://cloud.githubusercontent.com/assets/26029285/23883093/3a6b86ca-083b-11e7-8ebc-54306745534b.png">



