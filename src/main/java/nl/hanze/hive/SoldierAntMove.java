package nl.hanze.hive;

import java.util.ArrayList;

public class SoldierAntMove implements MoveBehavior {

    private Board board;

    public SoldierAntMove(Board board) {
        this.board = board;
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws Hive.IllegalMove {

        // Soldier ant mag zich niet verplaatsen naar zijn begin positie
        if (fromQ == toQ && fromR == toR)
        {
            throw new Hive.IllegalMove("SoldierAnt title cannot move to his start pos");
        }

    }
}
