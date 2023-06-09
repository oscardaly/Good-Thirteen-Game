import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            printMenu();

            Moves gameMoves = new Moves();
            DeckOfCards deckOfCards = DeckOfCards.createDeckOfCards();
            deckOfCards.shuffle();

            DeckOfCards faceUpCards = new DeckOfCards();
            dealCardsFromDeck(deckOfCards, faceUpCards);

            final String userOption = getUserOrDemoMode(reader);
            System.out.println("--" + userOption + "--");
            DeckOfCards availableMove = findAvailableMove(faceUpCards);

            while (gameStatus(faceUpCards, availableMove).equals("in-progress")) {

                if (userOption.equals("User Mode")) {
                    System.out.println("--" + deckOfCards.getSize() + " cards left in deck--");
                    System.out.println(faceUpCards);
                    Card firstChosenCard = getCardChoiceFromUser(faceUpCards, availableMove, reader);

                    if (firstChosenCard.isKing()) {
                        faceUpCards.removeCard(firstChosenCard);

                        if (deckOfCards.getSize() >= 1) {
                            faceUpCards.add(deckOfCards.takeCardFromDeck());
                        }

                        availableMove = findAvailableMove(faceUpCards);
                        gameMoves.addMove(new DeckOfCards(firstChosenCard));
                    } else if (firstChosenCard.getRank() == null && firstChosenCard.getSuit() == null) {
                        continue;
                    } else {
                        Card secondChosenCard = getCardChoiceFromUser(faceUpCards, availableMove, reader);

                        if (secondChosenCard.getRank() == null && secondChosenCard.getSuit() == null) {
                            continue;
                        } else if (cardsAddTo13(firstChosenCard, secondChosenCard)) {
                            faceUpCards.removeCard(firstChosenCard);
                            faceUpCards.removeCard(secondChosenCard);

                            if (deckOfCards.getSize() >= 2) {
                                faceUpCards.add(deckOfCards.takeCardFromDeck());
                                faceUpCards.add(deckOfCards.takeCardFromDeck());
                            } else if (deckOfCards.getSize() == 1) {
                                faceUpCards.add(deckOfCards.takeCardFromDeck());
                            }

                            availableMove = findAvailableMove(faceUpCards);
                            gameMoves.addMove(new DeckOfCards(firstChosenCard, secondChosenCard));
                        } else {
                            System.out.println("Selected card values do not add to 13... please try again.");
                        }
                    }
                } else if (userOption.equals("Demonstration Mode")) {
                    System.out.println("--" + deckOfCards.getSize() + " cards left in deck--");
                    System.out.println(faceUpCards);
                    System.out.println("Press ENTER to play next move...");
                    System.in.read();
                    System.out.println("Chosen move:");
                    System.out.println(availableMove);

                    if (availableMove.getSize() >= 1) {
                        if (availableMove.getSize() == 2) {
                            faceUpCards.removeCard(availableMove.getHead().next.card);

                            if (deckOfCards.getSize() >= 2) {
                                faceUpCards.add(deckOfCards.takeCardFromDeck());
                            }
                        }

                        faceUpCards.removeCard(availableMove.getHead().card);

                        if (deckOfCards.getSize() >= 1) {
                            faceUpCards.add(deckOfCards.takeCardFromDeck());
                        }
                    }

                    gameMoves.addMove(availableMove);
                    availableMove = findAvailableMove(faceUpCards);
                } else {
                    System.out.println("Incorrect option selected. Exiting the game...");
                    System.exit(0);
                }
            }

            askUserToReplayMoves(gameMoves, reader);
            System.out.println("\nThank you for playing the Good Thirteen CLI game!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void askUserToReplayMoves(Moves gameMoves, BufferedReader reader) throws IOException {
        System.out.println("Would you like to replay your game move by move? (y/n)");
        String replayOption = reader.readLine();
        System.out.println("\n");

        if (replayOption.equals("y")) {
            replayGameMoves(gameMoves);
        }
    }

    private static void replayGameMoves(Moves gameMoves) throws IOException {
        for (int i = 0; i < gameMoves.getNumberOfMoves(); i++) {
            if (i > 0) {
                System.out.println("Press ENTER to see next move...");
                System.in.read();
            }
            System.out.println("Move " + (i + 1) + ": " + gameMoves.getMoveAtIndex(i).toString());
        }
    }

    public static Card getCardChoiceFromUser(DeckOfCards faceUpCards, DeckOfCards availableMove, BufferedReader reader) throws IOException {
        System.out.println("Enter a number (1) to choose your a card or 'h' to see a hint: ");
        String userInput = reader.readLine();

        try {
            int firstChosenCardIndex = Integer.parseInt(userInput) - 1;
            return faceUpCards.getCardOfIndex(firstChosenCardIndex).card;
        } catch (NumberFormatException e) {
            if (userInput.equals("h")) {
                System.out.println("Hint:");

                if (availableMove.getSize() > 0) {
                    System.out.println(availableMove);
                } else if (availableMove.getSize() == 0) {
                    System.out.println("No moves available.");
                }

            } else {
                System.out.println("Incorrect option selected.");
            }

            return new Card(null, null);
        }
    }

    private static DeckOfCards findAvailableMove(DeckOfCards faceUpCards) {
        DeckOfCards availableMove = new DeckOfCards();

        for (int i = 0; i < faceUpCards.getSize(); i++) {
            for (int j = 0; j < faceUpCards.getSize(); j++) {
                Card firstCard = faceUpCards.getCardOfIndex(i).card;
                Card secondCard = faceUpCards.getCardOfIndex(j).card;

                if (firstCard.isKing()) {
                    availableMove.add(firstCard);
                    return availableMove;
                } else if (secondCard.isKing()) {
                    availableMove.add(secondCard);
                    return availableMove;
                } else if (cardsAddTo13(firstCard, secondCard)) {
                    availableMove.add(firstCard);
                    availableMove.add(secondCard);
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
        } else {
            System.out.println("Not enough cards in deck to start game");
        }
    }

    private static boolean cardsAddTo13(Card firstCard, Card secondCard) {
        return firstCard.getRank().ordinal() + secondCard.getRank().ordinal() == 11;
    }

    public static String getUserOrDemoMode(BufferedReader reader) throws IOException {
        System.out.println("Please enter 1 or 2 to select play mode: ");
        String userOption = reader.readLine();

        if (userOption.equals("1")) return "User Mode";
        else if (userOption.equals("2")) return "Demonstration Mode";
        else return "Incorrect option selected";
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
            } else {
                System.out.println("Game Lost!");
                return "lose";
            }
        } else {
            System.out.println("Game Won!");
            return "win";
        }
    }
}