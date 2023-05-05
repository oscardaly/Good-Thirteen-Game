import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class MenuTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Test
    public void testUserMode() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1");
        assertEquals("User Mode", Main.getUserOrDemoMode(bufferedReader));
    }

    @Test
    public void testDemoMode() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("2");
        assertEquals("Demonstration Mode", Main.getUserOrDemoMode(bufferedReader));
    }

    @Test
    public void testInvalidOption() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("3");
        assertEquals("Incorrect option selected", Main.getUserOrDemoMode(bufferedReader));
    }

    @Test
    public void testUserEntersIncorrectCardOption() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        DeckOfCards deckOfCards = new DeckOfCards();
        when(bufferedReader.readLine()).thenReturn("@");

        System.setOut(new PrintStream(outputStreamCaptor));
        Main.getCardChoiceFromUser(deckOfCards, deckOfCards, bufferedReader);
        System.setOut(standardOut);

        assertTrue(outputStreamCaptor.toString().contains("Incorrect option selected."));
    }

    @Test
    public void testUserRequestsHintWhenNoMovesAvailable() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        DeckOfCards deckOfCards = new DeckOfCards();
        when(bufferedReader.readLine()).thenReturn("h");

        System.setOut(new PrintStream(outputStreamCaptor));
        Main.getCardChoiceFromUser(deckOfCards, deckOfCards, bufferedReader);
        System.setOut(standardOut);

        assertTrue(outputStreamCaptor.toString().contains("No moves available."));
    }

    @Test
    public void testUserRequestsHintWhenMoveAvailable() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        Card kingOfHearts = new Card(Card.Rank.King, Card.Suit.Hearts);
        DeckOfCards deckOfCards = new DeckOfCards(kingOfHearts);
        when(bufferedReader.readLine()).thenReturn("h");

        System.setOut(new PrintStream(outputStreamCaptor));
        Main.getCardChoiceFromUser(deckOfCards, deckOfCards, bufferedReader);
        System.setOut(standardOut);

        assertTrue(outputStreamCaptor.toString().contains("Hint:"));
        assertTrue(outputStreamCaptor.toString().contains(kingOfHearts.toString()));
    }

    @Test
    public void testUserRequestsReplayMoves() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        Card kingOfHearts = new Card(Card.Rank.King, Card.Suit.Hearts);
        DeckOfCards deckOfCards = new DeckOfCards(kingOfHearts);
        Moves moves = new Moves(deckOfCards);
        when(bufferedReader.readLine()).thenReturn("y");

        System.setOut(new PrintStream(outputStreamCaptor));
        Main.askUserToReplayMoves(moves, bufferedReader);
        System.setOut(standardOut);

        assertTrue(outputStreamCaptor.toString().contains(kingOfHearts.toString()));
    }

    @Test
    public void testUserRefusesReplayMoves() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        Moves moves = new Moves();
        when(bufferedReader.readLine()).thenReturn("n");

        System.setOut(new PrintStream(outputStreamCaptor));
        Main.askUserToReplayMoves(moves, bufferedReader);
        System.setOut(standardOut);

        assertEquals("Would you like to replay your game move by move? (y/n)\n\n\n", outputStreamCaptor.toString());
    }
}