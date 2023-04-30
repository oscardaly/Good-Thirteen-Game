public class Moves {
    private Card firstCard;
    private Card lastCard;
    private int numberOfMoves;

    public Moves() {

    }

    public Card addCard(final Card newCard) {
        return newCard;
    }

    public Card removeCard() {
        //remove first card
        //change first card to second card
        return firstCard;
    }
}

//I need to think of a way to make this class with a move which could be either 1 or 2 cards
//maybe make a move class which holds 2 cardNodes and check if they add to 13 but handle null
