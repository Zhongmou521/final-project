import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TexasHoldem {
    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private List<String> deck;
    private List<String> communityCards;
    private List<String> playerHand;
    private List<String> opponentHand;
    private int playerChips;
    private int opponentChips;
    private int pot;

    public TexasHoldem() {
        createDeck();
        playerChips = 100; // Starting chips for the player
        opponentChips = 100; // Starting chips for the opponent
        communityCards = new ArrayList<>();
    }

    private void createDeck() {
        deck = new ArrayList<>();
        for (String suit : SUITS) {
            for (String value : VALUES) {
                deck.add(value + " of " + suit);
            }
        }
        shuffleDeck();
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private List<String> dealCards(int numCards) {
        List<String> cards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            cards.add(deck.remove(deck.size() - 1));
        }
        return cards;
    }

    private void evaluateHands() {
        System.out.println("Your hand: " + playerHand);
        System.out.println("Opponent's hand: " + opponentHand);
        System.out.println("Community cards: " + communityCards);

    }

    public void play() {
        System.out.println("Welcome to Texas Hold'em Poker!");

        // Initial deal
        playerHand = dealCards(2);
        opponentHand = dealCards(2);
        System.out.println("Your hand: " + playerHand); // Show player's hand

        // Betting round
        pot = 0;
        int playerBet = playerBetting();
        int opponentBet = (int) (Math.random() * opponentChips); // Random opponent bet
        pot += playerBet + opponentBet;

        // Deal the flop (3 community cards)
        communityCards.addAll(dealCards(3));
        System.out.println("Community cards after the flop: " + communityCards);

        // Second betting round
        playerBet = playerBetting();
        opponentBet = (int) (Math.random() * opponentChips); // Random opponent bet
        pot += playerBet + opponentBet;

        // Deal the turn (1 community card)
        communityCards.addAll(dealCards(1));
        System.out.println("Community cards after the turn: " + communityCards);

        // Third betting round
        playerBet = playerBetting();
        opponentBet = (int) (Math.random() * opponentChips); // Random opponent bet
        pot += playerBet + opponentBet;

        // Deal the river (1 community card)
        communityCards.addAll(dealCards(1));
        System.out.println("Community cards after the river: " + communityCards);

        // Final betting round
        playerBet = playerBetting();
        opponentBet = (int) (Math.random() * opponentChips); // Random opponent bet
        pot += playerBet + opponentBet;

        // Evaluate hands
        evaluateHands();

        // for determining the winner
        System.out.println("The pot is: " + pot);
        System.out.println("You win the pot!");
    }

    private int playerBetting() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have " + playerChips + " chips. Enter your bet (or 0 to fold): ");
        int bet = scanner.nextInt();
        if (bet > playerChips) {
            System.out.println("You can't bet more than you have! Betting all in instead.");
            bet = playerChips;
        }
        playerChips -= bet;
        return bet;
    }

    public static void main(String[] args) {
        TexasHoldem game = new TexasHoldem();
        game.play();
    }
}