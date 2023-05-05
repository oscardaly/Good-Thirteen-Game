public class Moves {
    private MoveNode firstMove;
    private MoveNode lastMove;
    private int numberOfMoves;

    public Moves() {
        numberOfMoves = 0;
        firstMove = null;
        lastMove = null;
    }

    public Moves(DeckOfCards... moves) {
        for (DeckOfCards cardsForMove : moves) {
            addMove(cardsForMove);
        }
    }

    public void addMove(final DeckOfCards cardsForMove) {
        MoveNode moveNode = new MoveNode(cardsForMove);

        if (isEmpty()) {
            firstMove = moveNode;
        } else {
            lastMove.setNext(moveNode);
        }
        lastMove = moveNode;
        numberOfMoves++;
    }

    public void removeMove() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty queue");
        }

        firstMove = firstMove.getNext();
        numberOfMoves--;

        if (isEmpty()) {
            lastMove = null;
        }
    }

    public MoveNode getFirstMove() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty queue");
        }

        return firstMove;
    }

    public boolean isEmpty() {
        return numberOfMoves == 0;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public MoveNode getMoveAtIndex(int index) {
        if (index < 0 || index >= getNumberOfMoves()) {
            throw new IndexOutOfBoundsException();
        }

        MoveNode current = firstMove;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current;
    }
}

class MoveNode {
    private Card firstCard;
    private Card secondCard;
    private MoveNode next;

    public MoveNode(DeckOfCards cardsForMove) {
        setMove(cardsForMove);
        setNext(null);
    }

    public DeckOfCards getMove() {
        DeckOfCards move = new DeckOfCards();
        move.add(firstCard);

        if (secondCard != null) {
            move.add(secondCard);
        }

        return move;
    }

    public void setMove(DeckOfCards cardsForMove) {
        firstCard = cardsForMove.getHead().card;

        if (cardsForMove.getSize() == 2) {
            secondCard = cardsForMove.getHead().next.card;
        }
    }

    public MoveNode getNext() {
        return next;
    }

    public void setNext(MoveNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder moveAsString = new StringBuilder();
        moveAsString.append(firstCard);

        if (secondCard != null) {
            moveAsString.append(", ").append(secondCard);
        }

        moveAsString.append("\n");

        return moveAsString.toString();
    }
}