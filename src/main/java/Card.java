public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card[] createDeckOfCards() {
        Card[] deckOfCards = new Card[52];
        int i  = 0;

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deckOfCards[i] = new Card(rank, suit);
                i++;
            }
        }

        return deckOfCards;
    }

    @Override
    public String toString() {
        return rank.toString() + "of" + suit.toString();
    }

    private enum Rank {
        Ace,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King
    }
    private enum Suit {
        Hearts,
        Spades,
        Diamonds,
        Clovers

    }
}
