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
        else if (gametile == Hive.Tile.QUEEN_BEE){
            return new QueenBeeMove(board);
        }
        else if (gametile == Hive.Tile.SOLDIER_ANT){
            return new SoldierAntMove(board);
        }
        return null;
    }
}
