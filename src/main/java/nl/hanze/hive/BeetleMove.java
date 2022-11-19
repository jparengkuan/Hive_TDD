package nl.hanze.hive;

import java.util.ArrayList;

public class BeetleMove implements MoveBehavior {

    private Board board;

    public BeetleMove(Board board) {
        this.board = board;
    }

    @Override
    public boolean isLegalMove(int fromQ, int fromR, int toQ, int toR) {

        // Haal de neighbors op van de from coordinaten
        ArrayList<Cell> neighboursFrom = board.GetNeighboursFromCell(new Cell(fromQ, fromR));

        // Haal de neighbors op van de to coordinaten
        ArrayList<Cell> neighboursTo = board.GetNeighboursFromCell(new Cell(toQ, toQ));

        neighboursFrom.retainAll(neighboursTo);

        if (!neighboursFrom.isEmpty())
        {
            return true;
        }


        return false;
    }
}
