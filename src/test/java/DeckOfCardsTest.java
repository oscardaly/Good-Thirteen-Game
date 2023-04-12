import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckOfCardsTest {
    @Test
    public void testCreateDeckOfCards() {
        DeckOfCards deckOfCards = DeckOfCards.createDeckOfCards();

        assertNotNull(deckOfCards);
        assertEquals(52, deckOfCards.getSize());

        int heartsCount = 0;
        int clubsCount = 0;
        int spadesCount = 0;
        int diamondsCount = 0;

        CardNode current = deckOfCards.getHead();
        while (current != null) {
            Card card = current.card;
            switch (card.getSuit()) {
                case Hearts: heartsCount++; break;
                case Clovers: clubsCount++; break;
                case Spades: spadesCount++; break;
                case Diamonds: diamondsCount++; break;
            }
            current = current.next;
        }

        assertEquals(13, heartsCount);
        assertEquals(13, clubsCount);
        assertEquals(13, spadesCount);
        assertEquals(13, diamondsCount);
    }

    @Test
    public void testShuffleCards() {
        DeckOfCards originalDeckOfCards = DeckOfCards.createDeckOfCards();
        DeckOfCards shuffledDeckOfCards = DeckOfCards.createDeckOfCards();

        shuffledDeckOfCards.shuffle();

        assertEquals(52, shuffledDeckOfCards.getSize());
        assertNotEquals(originalDeckOfCards, shuffledDeckOfCards);
    }

    @Test
    public void testAddCardToDeck() {
        DeckOfCards deckOfCards = new DeckOfCards();
        assertEquals(0, deckOfCards.getSize());

        Card card = new Card(Card.Rank.Jack, Card.Suit.Hearts);
        deckOfCards.add(card);

        assertEquals(1, deckOfCards.getSize());
    }

    @Test
    public void testRemoveCardFromDeck() {
        DeckOfCards deckOfCards = DeckOfCards.createDeckOfCards();
        assertEquals(52, deckOfCards.getSize());

        deckOfCards.takeCardFromDeck();
        assertEquals(51, deckOfCards.getSize());
    }

    @Test
    public void testGetInvalidIndexOfCard() {
        DeckOfCards deckOfCards = new DeckOfCards();
        assertEquals(0, deckOfCards.getSize());

        assertThrows(IndexOutOfBoundsException.class, () -> deckOfCards.getCardOfIndex(0));
    }

    @Test
    public void testGetValidIndexOfCard() {
        DeckOfCards deckOfCards = new DeckOfCards();
        assertEquals(0, deckOfCards.getSize());

        Card card = new Card(Card.Rank.Jack, Card.Suit.Diamonds);
        deckOfCards.add(card);

        assertEquals(deckOfCards.getCardOfIndex(0).card, card);
    }
}
