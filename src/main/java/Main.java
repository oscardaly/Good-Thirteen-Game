import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        //initialise?
        printMenu();

        DeckOfCards deckOfCards = DeckOfCards.createDeckOfCards();
        deckOfCards.shuffle();

        DeckOfCards faceUpCards = new DeckOfCards();
        dealCardsFromDeck(deckOfCards, faceUpCards);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String userOption = getUserOrDemoMode(reader);
        DeckOfCards hint = availableMove(faceUpCards);

        while (gameStatus(faceUpCards, hint).equals("in-progress")) {
            if (userOption.equals("user mode")) {
                System.out.println("--User mode--");
                System.out.println("--" + deckOfCards.getSize() + "cards left in deck--");
                System.out.println(faceUpCards);
                System.out.println("Enter a number (1) to choose your first card or 'h' to see a hint: ");
                int firstChosenCardIndex = Integer.parseInt(reader.readLine()) - 1;
                Card firstChosenCard = faceUpCards.getCardOfIndex(firstChosenCardIndex).card;

                if (firstChosenCard.isKing()) {
                    faceUpCards.removeCardOfIndex(firstChosenCardIndex);

                    if (deckOfCards.getSize() >= 1) {
                        faceUpCards.add(deckOfCards.takeCardFromDeck());
                    }

                    hint = availableMove(faceUpCards);
                }

                else {
                    System.out.println("Enter a number (1) to choose your second card: ");
                    int secondChosenCardIndex = Integer.parseInt(reader.readLine()) - 1;
                    Card secondChosenCard = faceUpCards.getCardOfIndex(secondChosenCardIndex).card;

                    if (cardsAddTo13(firstChosenCard, secondChosenCard)) {
                        faceUpCards.removeCard(firstChosenCard);
                        faceUpCards.removeCard(secondChosenCard);

                        if (deckOfCards.getSize() >= 2) {
                            faceUpCards.add(deckOfCards.takeCardFromDeck());
                            faceUpCards.add(deckOfCards.takeCardFromDeck());
                        }

                        else if (deckOfCards.getSize() == 1) {
                            faceUpCards.add(deckOfCards.takeCardFromDeck());
                        }

                        hint = availableMove(faceUpCards);
                    }

                    else {
                        System.out.println("Selected card values do not add to 13... please try again.");
                    }
                }
            }


            //When game Is finished allow the user to replay the game using arrow keys move by move

            else if (userOption.equals("demonstration mode")) {
                System.out.println("Demonstration mode.");

                //demonstration mode
                //press any key and the computer will play the game for them
                //shuffle cards
                //deal 10 cards face up
            } else {
                System.out.println("Incorrect option selected.");
            }
        }
    }

    private static DeckOfCards availableMove(DeckOfCards faceUpCards) {
        DeckOfCards availableMove = new DeckOfCards();

        for (int i = 0; i > faceUpCards.getSize(); i++) {
            for (int j = 0; j > faceUpCards.getSize(); i ++) {
                if (faceUpCards.getCardOfIndex(i).card.isKing()) {
                    availableMove.add(faceUpCards.getCardOfIndex(i).card);
                    return availableMove;
                }

                else if (faceUpCards.getCardOfIndex(j).card.isKing()) {
                    availableMove.add(faceUpCards.getCardOfIndex(j).card);
                    return availableMove;
                }

                else if (cardsAddTo13(faceUpCards.getCardOfIndex(i).card, faceUpCards.getCardOfIndex(j).card)) {
                    availableMove.add(faceUpCards.getCardOfIndex(i).card);
                    availableMove.add(faceUpCards.getCardOfIndex(j).card);
                    return availableMove;
                }
            }
        }

        return availableMove;
    }

    private static void dealCardsFromDeck(DeckOfCards deckOfCards, DeckOfCards faceUpCards) {
        if (deckOfCards.getSize() >= 10) {
            for (int i = 0; i < 10; i++) {
                faceUpCards.add(deckOfCards.takeCardFromDeck());
            }
        }

        else {
            System.out.println("Not enough cards in deck to start game");
        }
    }

    private static boolean cardsAddTo13(Card firstCard, Card secondCard) {
        return firstCard.getRank().ordinal() + secondCard.getRank().ordinal() == 11;

    }

    public static String getUserOrDemoMode(BufferedReader reader) throws IOException {
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

    private static String gameStatus(DeckOfCards faceUpCards, DeckOfCards hint) {
        if (faceUpCards.getSize() > 0) {
            if (hint.getSize() > 0) {
                return "in-progress";
            }

            else {
                System.out.println("Game Lost!");
                return "lose";
            }
        }

        else {
            System.out.println("Game Won!");
            return "win";
        }
        //Check that there is more than 0 cards face up
            //Check that at least 1 move is available - predicate?
            //Store this move as a hint

            //else If there is no move available then confirm this to the user
            //the game is ended and lost
        //else there are no cards left then the user has won and the game is ended
    }
}

//what is best practice with making methods public for testing