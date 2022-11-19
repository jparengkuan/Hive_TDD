package nl.hanze.hive;

public class MoveBehaviorFactory {

    private Board board;

    public MoveBehaviorFactory(Board board)
    {
        this.board = board;
    }

    public MoveBehavior getMoveBehavior(Hive.Tile gametile){
        if (gametile == Hive.Tile.BEETLE){
            return new BeetleMove(board);
        }
        return null;
    }
}
