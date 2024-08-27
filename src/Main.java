import java.util.*;

public class Main {

    public static void main(String[] args) {

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, "A");
        Arrays.fill(cardArray, aceOfHearts); // actually fills an array
        Card.printDeck(Arrays.asList(cardArray), "Ace of Hearts", 1);

        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts); // replaces existing elements
        Card.printDeck(cards, "Collections.fill Ace of Hearts", 1);

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts); // instantiates new list
        Card.printDeck(acesOfHearts, "Collections.nCopies() Aces of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, "K");
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "Collections.nCopies() Kings of Clubs", 1);

        Collections.addAll(cards, cardArray); // Collections.addAll can take an array, List addAll not
        cards.addAll(List.of(cardArray));
        Card.printDeck(cards, "Collections.addAll cardArray", 2);

        Collections.copy(cards, kingsOfClubs); // replaces existing entries
        Card.printDeck(cards, "Collections.copy kingsOfClubs", 2);

        cards = List.copyOf(kingsOfClubs); // returns a new immutable list
        Card.printDeck(cards, "List.copyOf(kingsOfClubs)", 1);

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck, "Deck", 4);

        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled Deck", 4);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Deck", 4);

        var sortingAlgorithm = Comparator.comparing(Card::getRank).thenComparing(Card::getSuit);
        Collections.sort(deck, sortingAlgorithm); // calls List sort
        Card.printDeck(deck, "Sorted Deck by rank, suit", 13);

        Collections.reverse(deck);
        Card.printDeck(deck, "Sorted by rank, suit reversed", 13);

        List<Card> kings = new ArrayList<>(deck.subList(4,8));
        Card.printDeck(kings, "Kings in deck", 1);

        List<Card> tens = new ArrayList<>(deck.subList(16,20));
        Card.printDeck(tens, "Tens in deck", 1);

        Collections.shuffle(deck);
        int subListIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("subListIndex for tens = " + subListIndex);
        System.out.println("Contains = " + deck.containsAll(tens));

        boolean disjoint = Collections.disjoint(deck, tens);
        System.out.println("disjoint= " + disjoint);

        boolean disjoint2 = Collections.disjoint(kings, tens);
        System.out.println("disjoint= " + disjoint2);

        Card.printDeck(deck, "Current Deck", 13);

        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, "10");
        deck.sort(sortingAlgorithm);
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
        // needs to be sorted with this sorting algorithm, good for large amount of entries
        System.out.println("foundIndex = " + foundIndex); // if unsorted or contains duplicates
        System.out.println("indexOf = " + deck.indexOf(tenOfHearts));
        System.out.println("Found card = " + deck.get(foundIndex));

        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB, "10");
        System.out.println("Found: " + Collections.replaceAll(deck, tenOfHearts, tenOfClubs));
        Card.printDeck(deck.subList(32, 36), "tensOfHearts replaced by tenOfClubs: ", 1);


        Collections.replaceAll(deck, tenOfClubs, tenOfHearts);
        Card.printDeck(deck.subList(32, 36), "reverse, tensOfHearts replaced by tenOfClubs: ", 1);

        System.out.println("Frequency: " + Collections.frequency(deck,tenOfClubs));
        System.out.println("Frequency: " + Collections.frequency(deck,tenOfHearts));

//        Card.printDeck(deck, "current deck: ", 16);

        Collections.shuffle(deck);
        System.out.println("Max: " + Collections.max(deck,sortingAlgorithm));
        System.out.println("Min: " + Collections.min(deck,sortingAlgorithm));

        var sortBySuit = Comparator.comparing(Card::getSuit).thenComparing(Card::getRank);
        deck.sort(sortBySuit);
        List<Card> copied = new ArrayList<>(deck.subList(0, 13));
        Card.printDeck(copied, "Copied", 1);
        Collections.rotate(copied, 2);
        Card.printDeck(copied, "Copied rotate 2", 1);


        copied = new ArrayList<>(deck.subList(0, 13));
        Card.printDeck(copied, "Copied", 1);
        Collections.rotate(copied, -2);
        Card.printDeck(copied, "Copied rotate -2", 1);

        copied = new ArrayList<>(deck.subList(0, 13));
        Card.printDeck(copied, "Copied", 1);
        for (int i = 0; i < copied.size() / 2; i++) {
            Collections.swap(copied, i, copied.size() -1 - i);
        }
        Card.printDeck(copied, "Manually Reversed with swap: ", 1);

        copied = new ArrayList<>(deck.subList(0, 13));
        Collections.reverse(copied);
        Card.printDeck(copied, "Reversed with reverse: ", 1);
    }

}
