import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //initialise?
        printMenu();

        final String userOption = getUserOrDemoMode(reader);
        DeckOfCards deckOfCards = DeckOfCards.createDeckOfCards();
        deckOfCards.shuffle();

        DeckOfCards faceUpCards = new DeckOfCards();
        dealCardsFromDeck(deckOfCards, faceUpCards);

        //while loop?
        if (userOption.equals("user mode")) {
            System.out.println("User mode.");

            //play game

            //Allow user to choose 1/2 cards
            //Check that there is more than 0 cards face up
            //Check that at least 1 move is available
            //Store this move as a hint
            //Ask user to choose a card
            //if the card is a king then remove it
            //else Ask the user to choose another card to remove
            //if the 2 cards don't add to 13 ask the user to choose again
            //else if they do add to 13 then remove the 2 cards and deal 2 more cards
            //if there is only 1 card left then just deal that
            //if there are no cards left then alert the user
            //If there is no move available then confirm this to the user
            //the game is ended and lost
            //If there are no cards left then the user has won and the game is ended

            //When game Is finished allow the user to replay the game using arrow keys move by move
        }

        else if (userOption.equals("demonstration mode")) {
            System.out.println("Demonstration mode.");

            //demonstration mode
            //press any key and the computer will play the game for them
            //shuffle cards
            //deal 10 cards face up
        }

        else {
            System.out.println("Incorrect option selected.");
        }
    }

    private static void dealCardsFromDeck(DeckOfCards deckOfCards, DeckOfCards faceUpCards) {
        if (deckOfCards.getSize() >= 10) {
            for (int i = 0; i < 10; i++) {
                Card card = deckOfCards.takeCardFromDeck();
                //take this out into a method
                System.out.println(i+1 + ". " + card.toString());
                faceUpCards.add(card);
            }
        }

        else {
            System.out.println("Not enough cards in deck to start game");
        }
    }

    protected static String getUserOrDemoMode(BufferedReader reader) throws IOException {
        System.out.println("Please enter 1 or 2 to select play mode: ");
        String userOption = reader.readLine();

        if (userOption.equals("1")) return "user mode";
        else if (userOption.equals("2")) return "demonstration mode";
        else return "not an option";
    }

    private static void printMenu() {
        System.out.println("Welcome to the Good Thirteen CLI Game!");
        System.out.println("1. Play Game");
        System.out.println("2. Demonstration Mode");
    }
}