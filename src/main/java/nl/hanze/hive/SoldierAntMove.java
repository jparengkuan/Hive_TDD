package nl.hanze.hive;

import java.util.ArrayList;
import java.util.LinkedList;

public class SoldierAntMove implements MoveBehavior {

    private Board board;


    public SoldierAntMove(Board board) {
        this.board = board;
    }


    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws Hive.IllegalMove {

        LinkedList<Cell> queue = new LinkedList<>();
        LinkedList<Cell> visited = new LinkedList<>();
        queue.add(new Cell(fromQ, fromR));

        while (!queue.isEmpty()) {

            Cell currenFirst = queue.removeFirst();

            // If cell does not exist yet, add cell to board
            if (!board.cellExists(currenFirst.q, currenFirst.r)) {
                board.getCells().add(new Cell(currenFirst.q, currenFirst.r));
            }

            if (visited.contains(new Cell(toQ, toR))) {
                System.out.println("Found");
                break;
            }

            visited.add(currenFirst);

            System.out.println(currenFirst.q + " " + currenFirst.r );

            ArrayList<Cell> allNeighbors = board.GetNeighboursFromCell(currenFirst);

            if (allNeighbors == null)
                continue;

            int neighborSize = allNeighbors.size();
            int countEmptyNeighborTiles = 0;

            for (Cell neighbor : allNeighbors) {

                // If cell does not exist yet, add cell to board
                if (!board.cellExists(neighbor.q, neighbor.r)) {
                    board.getCells().add(new Cell(neighbor.q, neighbor.r));
                }

                if(board.getCell(neighbor.q, neighbor.r).isEmpty()){
                    countEmptyNeighborTiles += 1;
                }

                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }

            if (countEmptyNeighborTiles <= neighborSize){
                throw new Hive.IllegalMove("SoldierAnt can only be moved over empty tiles");
            }
            System.out.println();


        }

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
