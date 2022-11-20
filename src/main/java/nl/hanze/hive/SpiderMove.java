package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SpiderMove implements MoveBehavior {

    private Board board;

    public SpiderMove(Board board){
        this.board = board;
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws Hive.IllegalMove {
        // Cell to move from
        Cell moveFrom = board.getCell(fromQ, fromR);
        // Cell to move to
        Cell moveTo = board.getCell(toQ, toR);

        // If end point and current point are the same, throw IllegalMove.
        if(moveFrom.equals(moveTo)){
            throw new Hive.IllegalMove("spider can't move to its current place");
        }
        // If end cell is not empty, throw IllegalMove.
        if(!board.getCell(toQ, toR).getTiles().isEmpty()){
            throw new Hive.IllegalMove("spider can't move to non empty end place");
        }
        // Calculate best path
        calculatePath(moveFrom, moveTo);
    }

    public void calculatePath(Cell moveFrom, Cell moveTo) throws Hive.IllegalMove {
        int amountOfMoves = 3;
        boolean destFound = false;
        Cell current = moveFrom;
        ArrayList<Cell> visited = new ArrayList<>();
        while(!destFound){
            ArrayList<Cell> neighbors = board.GetNeighboursFromCell(current);
            HashMap<Cell, Double> cell_distances = new HashMap<>();
            for(Cell cell : neighbors){
                double distance = Math.sqrt(Math.pow(moveTo.q - cell.q, 2) + Math.pow(moveTo.r - cell.r, 2));
                if(!visited.contains(board.getCell(cell.q, cell.r))){
                    cell_distances.put(cell, distance);
                }
            }
            double shortest = Collections.min(cell_distances.values());
            for(Map.Entry<Cell, Double> entry : cell_distances.entrySet()){
                if(entry.getValue() == shortest){
                    visited.add(current);
                    current = entry.getKey();
                }
            }
            if(current.equals(moveTo)){
                destFound = true;
            }
        }
        for(Cell cell : visited){
            if(visited.indexOf(cell) > 0 && cell.getTiles().size() > 0){
                throw new Hive.IllegalMove("A spider has to move over an empty field.");
            }
        }
        if(visited.size() != amountOfMoves){
            throw new Hive.IllegalMove("A spider must move exactly three times.");
        }
    }
}
