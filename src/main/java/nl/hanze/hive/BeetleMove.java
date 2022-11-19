package nl.hanze.hive;

public class BeetleMove implements MoveBehavior {

    private Board board;

    public BeetleMove(Board board) {
        this.board = board;
    }

    @Override
    public boolean isLegalMove(int fromQ, int fromR, int toQ, int toR) {

        return false;
    }
}
