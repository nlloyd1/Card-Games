import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class Deck {
    static String[] suits = {"Heart", "Spade", "Club", "Diamond"};
    static String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    static Random rng = new Random (420);

    public static ArrayList<Card> deck = new ArrayList<Card>();
    public static Deque<Card> discard = new LinkedList<Card>();
    public static ArrayList<ArrayList<Card>> melds = new ArrayList<ArrayList<Card>>();
    public static ArrayList<String> meldType = new ArrayList<String>();
    
    // Sort deck based off of card values: A is low, K is high
    // Comparator of two values
    public static Comparator<Card> dblDigitSort = new Comparator<Card>() {
        public int compare(Card c1, Card c2) {
            Integer val1;
            Integer val2;
            val1 = c1.getValue();
            val2 = c2.getValue();
            return val1.compareTo(val2);
        }
    };

    // Initialize the deck of 52 cards
    public static void makeDeck(boolean removeQueen) {
        deck.clear();
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (!(values[j] == "Q" && suits[i] == "Heart") || !removeQueen) {
                    String label= values[j] + " of " + suits[i];
                    Card temp = new Card(label, Card.cardEquivalent(values[j]), Card.suitToChar(suits[i]));
                    deck.add(temp);
                }
            }
        }
        Collections.shuffle(deck);
    }
    
    // Deals cards to each player (evenly) until the deck is empty
    // Returns the contents of each hand
    public static ArrayList<ArrayList<Card>> dealCards(int numPlayers, boolean INITIALIZEDISCARD) {
        ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
        for (int i=0; i < numPlayers; i++) {
            hands.add(new ArrayList<Card>());
        }
        int player = 0;
        int handSize = 2;
        
        outer:
        while (!deck.isEmpty()) {
            if (hands.get(player).size() < handSize) {
                hands.get(player).add(deck.get(0));
                deck.remove(0);
            }
            else {
                int playersDone = 0;
                for (ArrayList<Card> temp: hands) {
                    if (temp.size() >= handSize) {
                        Collections.sort(temp, dblDigitSort);
                        playersDone++;
                    }
                }
                if (playersDone >= numPlayers) {
                    break outer;
                }
            }
            player++;
            if (player > numPlayers) {
                player = 0;
            }
        }
        if (INITIALIZEDISCARD) {
            discard.addFirst(deck.remove(0));
        }
        System.out.println();
        return hands;
    }
    // Draw a card from the deck or discard pile
    // Reuturn drawn card
    public static Card drawCard() {
        Card returnCard;
        returnCard = deck.remove(0);
        System.out.printf("%s was draw.", returnCard);
        return returnCard;
    }
    public static Card drawCard(String location) {
        Card returnCard;
        if (location.equalsIgnoreCase("Discard")) {
            returnCard = discard.removeFirst();
        }
        else if (location.equalsIgnoreCase("deck")) {
            if (deck.size() <= 0) {
                deck = new ArrayList<Card>(discard);
                discard = new LinkedList<Card>();
                discard.add(deck.remove(0));
            }
            returnCard = deck.remove(0);
        }
        else {
            System.out.println("Invalid draw location");
            returnCard = new Card();
        }
        return returnCard;
    }
    // Prints contents of hand
    public static void displayCards(ArrayList<Card> hand) {
        Collections.sort(hand, dblDigitSort);
        for (int i=0; i < hand.size(); i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(hand.get(i).getLabel());
        }
        System.out.println();
    }
    // Prints matching pairs (melds)
    public static void displayMelds() {
        for (int i = 0; i < melds.size(); i++) {
            System.out.printf("%s " + melds.get(i) + "%n", meldType.get(i));
        }
        System.out.println();
    }
    // Return the score of a player's hand
    public static int calculateScore(ArrayList<Card> hand) {
        int score = 0;
        for (Card card: hand) {
            score += cardScore(card);
        }
        return score;
    }
    public static int cardScore(Card card) {
        int num = card.getValue();
        if (num > 10) {
            num = 10;
        }
        if (num == 8) {
            num = 50;
        }
        return num;
    }
}
