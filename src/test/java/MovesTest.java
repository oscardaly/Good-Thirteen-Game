import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovesTest {
    @Test
    public void test1CardMoveToString() {
        Card kingOfHearts = new Card(Card.Rank.King, Card.Suit.Hearts);
        MoveNode moveNode = new MoveNode(new DeckOfCards(kingOfHearts));
        assertEquals(kingOfHearts + "\n", moveNode.toString());
    }

    @Test
    public void test2CardMoveToString() {
        Card kingOfHearts = new Card(Card.Rank.King, Card.Suit.Hearts);
        Card queenOfHearts = new Card(Card.Rank.Queen, Card.Suit.Hearts);
        MoveNode moveNode = new MoveNode(new DeckOfCards(kingOfHearts, queenOfHearts));
        assertEquals(kingOfHearts + ", " + queenOfHearts + "\n", moveNode.toString());
    }

    @Test
    public void testGetMoveByIndex() {
        Card kingOfHearts = new Card(Card.Rank.King, Card.Suit.Hearts);
        Card queenOfHearts = new Card(Card.Rank.Queen, Card.Suit.Hearts);
        Card aceOfHearts = new Card(Card.Rank.Ace, Card.Suit.Hearts);
        DeckOfCards firstCardsForMove = new DeckOfCards(queenOfHearts, aceOfHearts);
        DeckOfCards secondCardsForMove = new DeckOfCards(kingOfHearts);

        Moves moves = new Moves(firstCardsForMove, secondCardsForMove);

        assert(moves.getMoveAtIndex(0).getMove().equals(firstCardsForMove));
        assert(moves.getMoveAtIndex(1).getMove().equals(secondCardsForMove));
    }

    @Test
    public void testGetMoveByIndexWithIncorrectIndex() {
        Moves moves = new Moves();

        assertThrows(IndexOutOfBoundsException.class, () -> moves.getMoveAtIndex(0).getMove());
    }
}
