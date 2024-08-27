import java.util.ArrayList;
import java.util.List;

public class Card {

    private Suit suit;
    private String face;
    private int rank;

    private Card(Suit suit, String face, int rank) {
        this.suit = suit;
        this.face = face;
        this.rank = rank;
    }

    public static Card getNumericCard(Suit suit, String number) {
        int rank;
        try {
            rank = Integer.parseInt(number) - 2;
        } catch (NumberFormatException e) {
            System.out.println( "Could not parse card number: " + e.getMessage());
            return null;
        }
        return new Card(suit, number, rank);
    }

    public static Card getFaceCard(Suit suit, String face) {
        int rank;
        switch (face.trim().toUpperCase().charAt(0)) {
            case 'J' -> {face = "Jack"; rank = 9;}
            case 'Q' -> {face = "Queen"; rank = 10;}
            case 'K' -> {face = "King"; rank = 11;}
            case 'A' -> {face = "Ace"; rank = 12;}
            default -> {
                return null;
            }
        }
        return new Card(suit, face, rank);
    }

    public static List<Card> getStandardDeck() {
        List<Card> deck = new ArrayList<>();
        for (var suit : Suit.values()) {
            for (int i = 2; i <= 10; i++) {
                deck.add(getNumericCard(suit, "" + i));
            }
            for (var face : List.of("J", "Q", "K", "A"))
                deck.add(getFaceCard(suit, face));
        }
        return deck;
    }

    @Override
    public String toString() {
        return switch (face) {
            case "Jack" -> "J";
            case "Queen" -> "Q";
            case "King" -> "K";
            case "Ace" -> "A";
            default -> face;
        } + suit +"(" + rank + ")";
    }

    public static void printDeck(List<Card> deck) {
        printDeck(deck, "Current Deck",4);
    }

    public static void printDeck(List<Card> deck, String description, int rowCount) {
        int columnCount = (int)Math.ceil((double)deck.size()/rowCount);

        System.out.println(description);
        for (int i = 0; i < deck.size(); i++) {
            if(i % columnCount == 0 && i != 0)
                System.out.println();
            System.out.print(deck.get(i) + "\t");
        }
        System.out.println();
        System.out.println("---------------------------------------");
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getFace() {
        return face;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card card) {
            return suit.equals(card.suit) && rank == card.rank;
        }
        return false;
    }

    public enum Suit {
//        CLUB(9827), DIAMOND(9830), HEART(9829), SPADE(9824);
        CLUB("♣"), DIAMOND("♦"), HEART("❤"), SPADE("♠");

        private String unicode;

        Suit(String unicode) {
            this.unicode = unicode;
        }

        @Override
        public String toString() {
            return unicode;
        }
    }

}
