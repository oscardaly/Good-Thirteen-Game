import java.util.Random;

public class DeckOfCards {
    private CardNode head;
    private int size;

    public DeckOfCards() {
        this.head = null;
        this.size = 0;
    }

    public CardNode getHead() {
        return this.head;
    }

    public int getSize() {
        return this.size;
    }

    public void add(Card card) {
        CardNode newCardNode = new CardNode(card);

        if (head == null) {
            head = newCardNode;
        } else {
            CardNode currentCardNode = head;

            while (currentCardNode.next != null) {
                currentCardNode = currentCardNode.next;
            }

            currentCardNode.next = newCardNode;
        }

        this.size++;
    }

    public Card takeCardFromDeck() {
        Card lastCard = getCardOfIndex(size-1).card;
        size--;
        return lastCard;
    }

    public static DeckOfCards createDeckOfCards() {
        DeckOfCards deckOfCards = new DeckOfCards();
        int i = 0;

        for (Card.Rank rank : Card.Rank.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                deckOfCards.add(new Card(rank, suit));
                i++;
            }
        }

        return deckOfCards;
    }

    public void shuffle() {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int j = random.nextInt(i + 1);

            CardNode card1 = getCardOfIndex(i);
            CardNode card2 = getCardOfIndex(j);
            Card temp = card1.card;
            card1.card = card2.card;
            card2.card = temp;
        }
    }

    public CardNode getCardOfIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        CardNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    public CardNode removeCardOfIndex(int index) {
        CardNode previousCard = getCardOfIndex(index-1);
        previousCard.next = previousCard.next.next;
        size--;
        return previousCard.next;
    }

}

class CardNode {
    Card card;
    CardNode next;

    public CardNode(Card card) {
        this.card = card;
        this.next = null;
    }
}
