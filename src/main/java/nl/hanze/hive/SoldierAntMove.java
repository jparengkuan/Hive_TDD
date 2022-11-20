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
            throw new Hive.IllegalMove("SoldierAnt tile cannot move to his start pos");
        }

        // Soldier mag zich alleen verplaatsen naar een lege eind positie
        if (!board.getCell(toQ, toR).isEmpty()){
            throw new Hive.IllegalMove("SoldierAnt tile can only move to an empty end pos");
        }



    }
}
