package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import nl.hanze.hive.Hive.Tile;

public class Board {

    // A HashMap with the board cells as keys and a stack of tiles as values.
    private ArrayList<Cell> cells;

    /**
     * Initialize the game board with cells.
     */
    public Board(){
        this.cells = new ArrayList<>();
    }

    /**
     * Returns the cells on a game board.
     *
     * @return the HashMap with cells and tiles.
     */
    public ArrayList<Cell> getCells(){
        return cells;
    }

    /**
     * Returns a cell with the given coordinates.
     *
     * @param q the q coordinate.
     * @param r the r coordinate.
     * @return the cell if a cell with given coordinates exists, null if the cell does not exist.
     */
    public Cell getCell(int q, int r){
        for(Cell cell : cells){
            if(cell.q == q && cell.r == r){
                return cell;
            }
        }
        return null;
    }

    public boolean cellExists(int q, int r){
        for(Cell cell : cells){
            if(cell.q == q && cell.r == r){
                return true;
            }
        }
        return false;
    }

    public void addCell(int q, int r){
        cells.add(new Cell(q, r));
    }

    /**
     * Returns a boolean if the board is empty
     *
     * @return boolean True/False
     */
    public boolean isEmpty(){
        return getCells().size() == 0;
    }

    /**
     * Get all the surrounding neighbour cells for a given cell
     *
     * @param Cell cell The target cell
     * @return ArrayList<Cell> Arraylist with all the six neighbour cells
     */
    ArrayList<Cell> GetNeighboursFromCell(Cell cell){

        // Maak arraylist aan hier slaan we alle neighbours in op
        ArrayList<Cell> neighbours = new ArrayList<Cell>();

        int[][] directions = new int[][] {Main.NORTH_WEST, Main.WEST, Main.NORTH_EAST, Main.SOUTH_WEST, Main.SOUTH_EAST, Main.EAST};

        // Loop door elke alle zes aangrenzende velden
        for(int[] direction : directions){

            // Maak een nieuwe neighbourcell aan
           Cell neighboursCell = new Cell(cell.q + direction[0], cell.r + direction[1]);

           // Voeg de cell toe aan neighbours arraylist
           neighbours.add(neighboursCell);
        }

        // return the arraylist filled with neighbour cells
        return neighbours;
    }
}
