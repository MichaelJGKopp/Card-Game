import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class War {

    static Player player1;
    static Player cpu;
    static List<Card> deck;
    static List<Card> pool;
    static Scanner scanner;
    static int roundCount;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        newGame();

        while (
                deck.size() >= 2
                && player1.size() + deck.size() >= cpu.size()
                && cpu.size() + deck.size() >= player1.size()
        ) {
            System.out.println("""
                                Draw cards?\s""");
            scanner.nextLine();

            round();
        }

        result();

        scanner.close();;
    }

    public static void round() {
        // card init to start the loop
        Card cardP1 = Card.getNumericCard(Card.Suit.HEART, "10");
        Card cardCPU = cardP1;

        // new round
        roundCount++;
        while (cardCPU.getRank() == cardP1.getRank()) {
            // draw card each and add to pool
            cardP1 = deck.remove(0);
            cardCPU = deck.remove(0);
            pool.addAll(List.of(cardP1, cardCPU));

            // compare cards and give to winning player if equal draw another
            System.out.println("Round " + roundCount);
            System.out.println("---------------------");
            System.out.printf("%-12s %-4s %n", player1.getName(), cardP1);
            System.out.printf("%-12s %-4s %n", cpu.getName(), cardCPU);
            System.out.printf("%-12s %-20s %n", "Pool", pool);
            System.out.println("---------------------");

            if (cardP1.getRank() > cardCPU.getRank()) {
                System.out.println(player1.getName() + " won this round!");
                player1.addAll(pool);
                pool.clear();
            } else if (cardP1.getRank() < cardCPU.getRank()) {
                System.out.println(cpu.getName() + " won this round!");
                cpu.addAll(pool);
                pool.clear();
            } else {
                System.out.println("Cards are equal in rank. Draw again?");
                scanner.nextLine();
            }
            System.out.println();
        }
        System.out.println("End of round " + roundCount + ":");
        System.out.println(player1.getName() + ": " + player1);
        System.out.println(cpu.getName() + ": " + cpu);
        System.out.println();
    }

    public static void result() {
        System.out.println("***********************");
        System.out.println(
                (player1.size() > cpu.size()) ? player1.getName() + " has won the game!" :
                (player1.size() < cpu.size()) ? player1.getName() + " has lost the game!" :
                        "The game came to a draw!");
        System.out.println("***********************");
        System.out.printf("%-12s Cards: %-4s %n", player1.getName(), player1.size());
        System.out.printf("%-12s Cards: %-4s %n", cpu.getName(), cpu.size());
        System.out.println("***********************");
        System.out.println("Quit?");
        scanner.nextLine();
    }

    public static void newGame() {

        System.out.println("""
                ***********************
                WELCOME TO WAR!
                ***********************
                """);

        player1 = new Player("Player 1");
        cpu = new Player("Player 2");

        deck = Card.getStandardDeck();
        Collections.shuffle(deck);

        pool = new ArrayList<>();
    }

}
