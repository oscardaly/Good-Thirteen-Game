public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isKing() {
        return rank == Rank.King;
    }

    @Override
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }

    public boolean isEqualTo(Card card) {
        return (card.getRank() == this.rank) &&
                (card.getSuit() == this.suit);
    }

    public enum Rank {
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
    public enum Suit {
        Hearts,
        Spades,
        Diamonds,
        Clovers
    }
}
