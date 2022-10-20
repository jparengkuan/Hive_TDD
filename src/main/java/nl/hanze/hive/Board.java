package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import nl.hanze.hive.Hive.Tile;

public class Board {

    private HashMap<Cell, Stack<Tile>> cells;

    public Board(int size){
        this.cells = new HashMap<>();
        createBoard(size);
    }

    public void createBoard(int size){
        for(int i = -size; i < size; i++){
            for(int j = -size; j < size; j++){
                cells.put(new Cell(i, j), new Stack<>());
            }
        }
    }

    public HashMap<Cell, Stack<Tile>> getCells(){
        return cells;
    }

    public Stack<Tile> getCell(int q, int r){
        for(Cell cell : cells.keySet()){
            if(cell.q == q && cell.r == r){
                return cells.get(cell);
            }
        }
        return null;
    }


}
