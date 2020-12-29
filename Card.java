public class Card {
    private String label;
    private int value;
    private char suit;

    public Card() {
        label = "";
        value = -1;
        suit = '\000';
    }
    public Card(String label, int value, char suit) {
        this.label = label;
        this.value = value;
        this.suit = suit;
    }
    public String getLabel() {
        return label;
    }
    public int getValue() {
        return value;
    }
    public char getSuit() {
        return suit;
    }
    public String getStringValue() {
        switch(value) {
            case 11: 
                return "J";
            case 12: 
                return "Q";
            case 13: 
                return "K";
            default:
                return Integer.toString(value);
        }
    }
    public static char suitToChar(String suit) {
        switch(suit) {
            case "Club":
                return 'C';
            case "Spade":
                return 'S';
            case "Diamond":
                return 'D';
            case "Heart":
                return 'H';
            default:
                return '\0';
        }
    }
    public static String charToSuit(char suit ) {
        switch(suit) {
            case 'C':
                return "Club";
            case 'S':
                return "Spade";
            case 'D':
                return "Diamond";
            case 'H':
                return "Heart";
            default:
                return "";
        }
    }
    public void setSuit(char suit) {
        this.suit = suit;
        String[] splitLabel = this.label.split(" ");
        this.label = splitLabel[0] + " of " + charToSuit(suit);
    }
    public static int cardEquivalent(String cardVal) {
        switch(cardVal) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            default:
                return Integer.parseInt(cardVal);
        }
    }
}
