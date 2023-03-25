import java.util.Random;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card[] createDeckOfCards() {
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

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public static void shuffle(Card[] deckOfCards) {
        Random random = new Random();

        for (int i = 0; i < deckOfCards.length; i++) {
            int j = random.nextInt(deckOfCards.length - 1);
            Card temp = deckOfCards[i];
            deckOfCards[i] = deckOfCards[j];
            deckOfCards[j] = temp;
            System.out.println(deckOfCards[i]);
        }
    }

    @Override
    public String toString() {
        return rank.toString() + " of " + suit.toString();
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
