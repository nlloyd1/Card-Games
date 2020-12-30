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
    public static ArrayList<ArrayList<Card>> dealCards(int numPlayers, boolean INITIALIZEDISCARD) {
        ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();

        for (int i=0; i < numPlayers; i++) {
            hands.add(new ArrayList<Card>());
        }
        int player = 0;
        int handSize = 2;

        while (!deck.isEmpty()) {
            if (hands.get(player).size() < handSize) {
                hands.get(player).add(deck.get(0));
                deck.remove(0);
            }
        }
    }
}
