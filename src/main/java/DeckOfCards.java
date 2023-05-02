import java.util.Random;

public class DeckOfCards {
    private CardNode head;
    private int size;

    public DeckOfCards() {
        this.head = null;
        this.size = 0;
    }

    public DeckOfCards(Card... cards) {
        for (Card card : cards) {
            add(card);
        }
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

    public void removeCardOfIndex(int index) {
        if (index == 0) {
            head = getCardOfIndex(0).next;
        }

        else {
            CardNode previousCard = getCardOfIndex(index-1);
            previousCard.next = previousCard.next.next;
        }

        size--;
    }

    public Card removeCard(Card cardToRemove) {
        CardNode currentCardNode = head;

        for (int i = 0; i < size; i++) {
            if (currentCardNode.card.isEqualTo(cardToRemove)) {
                removeCardOfIndex(i);
                return cardToRemove;
            }
            currentCardNode = currentCardNode.next;
        }

        return currentCardNode.card;
    }

    public boolean contains(Card cardToFind) {
        CardNode currentCardNode = head;

        for (int i = 0; i < size; i++) {
            if (currentCardNode.card.isEqualTo(cardToFind)) {
                return true;
            }
            currentCardNode = currentCardNode.next;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder cardsAsString = new StringBuilder();
        CardNode currentCardNode = head;

        for (int i = 0; i < size; i++) {
            cardsAsString.append(i + 1)
                    .append(". ")
                    .append(currentCardNode.card.toString())
                    .append("\n");

            currentCardNode = currentCardNode.next;
        }

        return cardsAsString.toString();
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

