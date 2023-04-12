import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testCardToString() {
        Card card = new Card(Card.Rank.Four, Card.Suit.Spades);
        assertEquals("Four of Spades", card.toString());
    }
}