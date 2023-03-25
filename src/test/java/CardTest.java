import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testCardToString() {
        Card card = new Card(Card.Rank.Four, Card.Suit.Spades);
        assertEquals("Four of Spades", card.toString());
    }

    @Test
    public void testCreateDeckOfCards() {
        Card[] deckOfCards = Card.createDeckOfCards();
        Predicate<Card> containsAllSuits = card ->
                card.getSuit() == Card.Suit.Hearts ||
                card.getSuit() == Card.Suit.Clovers ||
                card.getSuit() == Card.Suit.Spades ||
                card.getSuit() == Card.Suit.Diamonds;

        assertNotNull(deckOfCards);
        assertEquals(52, deckOfCards.length);
        assertTrue(Arrays.stream(deckOfCards).anyMatch(containsAllSuits));
    }

    @Test
    public void testShuffleCards() {
        Card[] cards = Card.createDeckOfCards();
        Card[] shuffledCards = Card.createDeckOfCards();

        Card.shuffle(shuffledCards);

        assertEquals(52, shuffledCards.length);
        assertNotEquals(cards, shuffledCards);
    }
}