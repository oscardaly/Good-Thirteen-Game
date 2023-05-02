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

    @Test
    public void testRemovingCard() {
        DeckOfCards deckOfCards = new DeckOfCards();
        Card jackDiamonsCard = new Card(Card.Rank.Jack, Card.Suit.Diamonds);
        Card queenHeartsCard = new Card(Card.Rank.Queen, Card.Suit.Hearts);
        Card kingSpacesCard = new Card(Card.Rank.King, Card.Suit.Spades);

        deckOfCards.add(jackDiamonsCard);
        deckOfCards.add(queenHeartsCard);
        deckOfCards.add(kingSpacesCard);
        deckOfCards.removeCard(queenHeartsCard);

        assertEquals(2, deckOfCards.getSize());
        assertEquals(jackDiamonsCard, deckOfCards.getCardOfIndex(0).card);
        assertEquals(kingSpacesCard, deckOfCards.getCardOfIndex(1).card);
    }

    @Test
    public void testRemoveMultipleCards() {
        DeckOfCards deckOfCards = new DeckOfCards();
        Card jackDiamonsCard = new Card(Card.Rank.Jack, Card.Suit.Diamonds);
        Card queenHeartsCard = new Card(Card.Rank.Queen, Card.Suit.Hearts);
        Card kingSpacesCard = new Card(Card.Rank.King, Card.Suit.Spades);

        deckOfCards.add(jackDiamonsCard);
        deckOfCards.add(queenHeartsCard);
        deckOfCards.add(kingSpacesCard);
        deckOfCards.removeCard(queenHeartsCard);
        deckOfCards.removeCard(kingSpacesCard);

        assertEquals(1, deckOfCards.getSize());
        assertEquals(jackDiamonsCard, deckOfCards.getCardOfIndex(0).card);
        assertFalse(deckOfCards.contains(queenHeartsCard));
        assertFalse(deckOfCards.contains(kingSpacesCard));
    }

    @Test
    public void testToString() {
        DeckOfCards deckOfCards = new DeckOfCards();
        Card jackDiamonsCard = new Card(Card.Rank.Jack, Card.Suit.Diamonds);
        Card queenHeartsCard = new Card(Card.Rank.Queen, Card.Suit.Hearts);
        Card kingSpacesCard = new Card(Card.Rank.King, Card.Suit.Spades);

        deckOfCards.add(jackDiamonsCard);
        deckOfCards.add(queenHeartsCard);
        deckOfCards.add(kingSpacesCard);

        assertEquals("1. Jack of Diamonds\n2. Queen of Hearts\n3. King of Spades\n", deckOfCards.toStringAsList(ListType.NUMBERED));
    }
}
