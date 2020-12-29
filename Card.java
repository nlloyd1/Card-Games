public class Card {
    private string label;
    private int value;
    private char suit;

    public Card() {
        label = "";
        value = 1;
        suit = "A";
    }
    public Card(string label, int value, char suit) {
        this.label = label;
        this.value = value;
        this.suit = suit;
    }
    public string getLabel() {
        return label;
    }
    public int getValue() {
        return value;
    }
    public char getSuit() {
        return suit;
    }
    public string setLabel() {

    }
    public int setValue() {
        switch(value) {
            case 11: 
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 1:
                return "A";
            default:
                return Integer.toString(value);
        }
    }
    public char setSuit() {

    }
    public suitToChar(string Suit) {
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
    public static charToSuit(char suit ) {
        switch(suit) {
            case 'C':
                return "Club";
            case "S":
                return "Spade";
            case "D":
                return "Diamond";
            case "H":
                return "Heart";
            default:
                return '\0';
        }
    }
}
