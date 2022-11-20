package nl.hanze.hive;

import nl.hanze.hive.*;

import java.util.ArrayList;

public class GrashopperMove implements MoveBehavior {

    private Board board;

    public GrashopperMove(Board board) {
        this.board = board;
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws Hive.IllegalMove {
        // Grashopper mag zich niet verplaatsen naar zijn begin positie
        if (fromQ == toQ && fromR == toR)
        {
            throw new Hive.IllegalMove("Grashopper tile cannot move to his start pos");
        }

    }
}
