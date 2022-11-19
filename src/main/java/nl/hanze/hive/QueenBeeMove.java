package nl.hanze.hive;

import java.util.ArrayList;

public class QueenBeeMove implements MoveBehavior {

    private Board board;

    public QueenBeeMove(Board board) {
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
            throw new Hive.IllegalMove("QueenBee tile can only move 1 pos");
        }

        Cell cellToMoveTo = board.getCell(toQ, toR);

        if (cellToMoveTo != null || !cellToMoveTo.isEmpty()){
            throw new Hive.IllegalMove("QueenBee tile can only be moved to an empty cell");
        }


    }
}
