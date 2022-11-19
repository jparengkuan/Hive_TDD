package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {

    // A HashMap with the board cells as keys and a stack of tiles as values.
    private ArrayList<Cell> cells;

    /**
     * Initialize the game board with cells.
     */
    public Board() {
        this.cells = new ArrayList<>();
    }

    /**
     * Returns the cells on a game board.
     *
     * @return the HashMap with cells and tiles.
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * Returns a cell with the given coordinates.
     *
     * @param q the q coordinate.
     * @param r the r coordinate.
     * @return the cell if a cell with given coordinates exists, null if the cell does not exist.
     */
    public Cell getCell(int q, int r) {
        for (Cell cell : cells) {
            if (cell.q == q && cell.r == r) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Check if cell exists on the board.
     * @param q the q coordinate.
     * @param r the r coordinate.
     * @return true if cell exists, false if cell does not exist.
     */
    public boolean cellExists(int q, int r) {
        for (Cell cell : cells) {
            if (cell.q == q && cell.r == r) {
                return true;
            }
        }
        return false;
    }

    public void addCell(int q, int r) {
        cells.add(new Cell(q, r));
    }

    /**
     * Returns a boolean if the board is empty
     *
     * @return boolean True/False
     */
    public boolean isEmpty() {
        return getCells().size() == 0;
    }

    /**
     * Get all the surrounding neighbour cells for a given cell
     *
     * @param cell The target cell
     * @return ArrayList<Cell> Arraylist with all the six neighbour cells
     */
    public ArrayList<Cell> GetNeighboursFromCell(Cell cell) {

        // Maak arraylist aan hier slaan we alle neighbours in op
        ArrayList<Cell> neighbours = new ArrayList<>();

        int[][] directions = new int[][]{Game.NORTH_WEST, Game.WEST, Game.NORTH_EAST, Game.SOUTH_WEST, Game.SOUTH_EAST, Game.EAST};

        // Loop door elke alle zes aangrenzende velden
        for (int[] direction : directions) {

            // Maak een nieuwe neighbourcell
            Cell neighboursCell = new Cell(cell.q + direction[0], cell.r + direction[1]);

            // Voeg de cell toe aan neighbours arraylist
            neighbours.add(neighboursCell);
        }

        // return the arraylist filled with neighbour cells
        return neighbours;
    }

    /**
     * Get all the surrounding neighbour cells for a given cell that tiles
     *
     * @param cell The target cell
     * @return ArrayList<Cell> Arraylist with all cells with tiles
     */
    ArrayList<Cell> GetNeighboursFromCellWithTiles(Cell cell) {

        // Maak arraylist aan hier slaan we alle neighbours in op voor de gegeven cell
        ArrayList<Cell> neighbours = GetNeighboursFromCell(cell);

        // Maak een arraylist waar we alle cellen opslaan die geen tile bevatten
        ArrayList<Cell> neighboursWithTiles = new ArrayList<>();

        // Loop door elke mogelijke neighbour van de cell
        for (Cell neighbour : neighbours) {

            // Check of de cell bestaat op het bord
            if (cellExists(neighbour.q, neighbour.r)) {
                // Haal het cell object op voor met de coördinaten
                Cell existingCell = getCell(neighbour.q, neighbour.r);

                // Als de cell bestaat op het bord en niet leeg is dan voegen we de cell toe
                // aan de array neighboursWitTiles
                if (!existingCell.isEmpty()) {
                    neighboursWithTiles.add(neighbour);
                }
            }
        }

        // Geef de arraylist terug met de cellen die tiles bevatten
        return neighboursWithTiles;

    }

    /**
     * Get all the surrounding neighbour cells for a given cell that has no tiles
     *
     * @param cell The target cell
     * @return ArrayList<Cell> Arraylist with all cells that have no tiles
     */
    ArrayList<Cell> GetNeighboursFromCellWithNoTiles(Cell cell) {

        // Maak arraylist aan hier slaan we alle neighbours in op voor de gegeven cell
        ArrayList<Cell> neighbours = GetNeighboursFromCell(cell);

        // Maak een arraylist waar we alle cellen opslaan die geen tile bevatten
        ArrayList<Cell> neighboursWithNoTiles = new ArrayList<>();

        // Loop door elke mogelijke neighbour van de cell
        for (Cell neighbour : neighbours) {

            // Check of de cell bestaat op het bord
            if (cellExists(neighbour.q, neighbour.r)) {
                // Haal het cell object op voor met de coördinaten
                Cell existingCell = getCell(neighbour.q, neighbour.r);

                // Als de cell bestaat op het bord en niet leeg is dan voegen we de cell toe
                // aan de array neighboursWithNoTiles
                if (existingCell.isEmpty()) {
                    neighboursWithNoTiles.add(neighbour);
                }
            } else {
                // De cell bestaat niet op het bord, omdat er nog niet op is gespeeld
                // We kunnen er daarom vanuit gaan dat de cell geen tiles bevat en slaan
                // hem daarop in de neighboursWithNoTiles arraylist
                neighboursWithNoTiles.add(neighbour);
            }

        }

        // Geef de arraylist terug met de cellen die geen tiles bevatten
        return neighboursWithNoTiles;

    }

    /**
     * Check if the chain will be broken if a player makes a certain move
     *
     * @param cellsArray for backup purposes
     * @param amountOfChainsBeforeMove
     * @return Boolean true or false
     */
    public boolean checkIfChainWillBeBroken(ArrayList<Cell> cellsArray, int amountOfChainsBeforeMove) {

        // Aantal chains op het bord voordat een player een move doet
        int amountOfChainsAfterMove = countTotalTileChains();

        // Check of er een chain is gebroken
        if (amountOfChainsAfterMove < amountOfChainsBeforeMove)
        {
            // Zet backup terug
            this.setCells(cellsArray);

            return true;
        }
        return false;
    }

    /**
     * Check if board has tiles from both players.
     * @return true if the board has tiles from both players, false if board has tiles from one or no players.
     */
    public boolean hasTilesFromBothPlayers(){
        int black_count = 0;
        int white_count = 0;
        for(Cell cell : cells){
            for(Gametile tile : cell.getTiles()){
                if(tile.getOwner() == Hive.Player.BLACK){
                    black_count++;
                }
                if(tile.getOwner() == Hive.Player.WHITE){
                    white_count++;
                }
            }
        }
        return black_count > 0 && white_count > 0;
    }

    /**
     * Count the total of chains on the board
     *
     * @return int number of chains
     */
    public int countTotalTileChains() {

        // Bijhouden hoeveel chains er zijn
        int tileChainCounter = 0;

        // Bijhouden welke cellen we al hebben gehad
        ArrayList<Cell> cellVisistedList = new ArrayList<Cell>();

        // Loop door elke cell in het bord
        for (Cell cell : this.cells) {

            // We zijn alleen geïnteresseerd in de cellen met tiles en de cellen die we nog niet hebben gehad
            if (!cell.isEmpty() && !cellVisistedList.contains(cell)) {

                // Haal de buren van de cell op
                ArrayList<Cell> neighboursCellsWithTiles = GetNeighboursFromCellWithTiles(cell);

                // Check of de cell buren heeft
                if (neighboursCellsWithTiles.size() > 0) {

                    // Increment de tileChainCounter
                    tileChainCounter += 1;

                    // Loop door alle buren van de cell heen
                    for (Cell neighbourCell : neighboursCellsWithTiles) {
                        // Voeg de cell toe aan de lijst
                        cellVisistedList.add(neighbourCell);
                    }
                }

            }

        }

        return tileChainCounter;
    }

    /**
     * Setter to set all the cells
     *
     * @param cellsArray the array of cells
     * @return Boolean true or false
     */
    private void setCells(ArrayList<Cell> cellsArray) {
        this.cells = cellsArray;
    }


    String getMoveDirections(int fromQ, int fromR, int toQ, int toR)
    {

        Cell startPos = new Cell(fromQ, fromR);
        Cell endPos = new Cell(toQ, toR);

        String direction;

        HashMap<String, Cell> startPosCoordinatesMap = startPos.getCoordinatesHashmap();

        for (Map.Entry<String, Cell> set : startPosCoordinatesMap.entrySet()) {

            if (set.getValue().equals(endPos))
            {
                direction = set.getKey();
                return direction;
            }
        }
        return "Unknown";
    }


}
