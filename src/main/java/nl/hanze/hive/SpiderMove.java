package nl.hanze.hive;

public class SpiderMove implements MoveBehavior {

    private Board board;

    public SpiderMove(Board board){
        this.board = board;
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws Hive.IllegalMove {

    }
}
