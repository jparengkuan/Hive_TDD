package nl.hanze.hive;

import java.util.ArrayList;

public class BeetleMove implements MoveBehavior {

    private Board board;

    public BeetleMove(Board board) {
        this.board = board;
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws Hive.IllegalMove {

        // Haal de neighbors op van de from coordinaten
        ArrayList<Cell> neighboursFrom = board.GetNeighboursFromCell(new Cell(fromQ, fromR));

        // Haal de neighbors op van de to coordinaten
        ArrayList<Cell> neighboursTo = board.GetNeighboursFromCell(new Cell(toQ, toQ));

        neighboursFrom.retainAll(neighboursTo);

        if (neighboursFrom.isEmpty())
        {
            throw new Hive.IllegalMove("Beetle tile can only move 1 pos");
        }

        if(movementHelperMethods.lostContactDuringMove(board, fromQ, fromR, toQ, toR))
        {
            throw new Hive.IllegalMove("Beetle tile lost contact during move");
        }

    }
}
