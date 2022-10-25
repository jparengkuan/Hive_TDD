package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static nl.hanze.hive.Hive.Tile.GRASSHOPPER;
import static nl.hanze.hive.Hive.Tile.QUEEN_BEE;
import static org.junit.jupiter.api.Assertions.*;

public class CellTests {

    @Test
    public void checkIfCellHasNoTiles() {

        // Maak een nieuw bord
        Board board = new Board();

        // Voeg een cell toe aan het bord
        board.getCells().add(new Cell(1, 1));

        // Haal het cell object op
        Cell cell = board.getCell(1,1);

        // Assert
        assertEquals(true, cell.isEmpty());
    }

    @Test void whenTileAlreadyPlacedOnCellPlayerCannotMakeAPlay() throws Hive.IllegalMove {

        // Maak een nieuw spel aan
        Main main = new Main();

        // Speel de de grashopper naar pos 1,2
        main.play(GRASSHOPPER, 1, 2);

        // Speel in de volgende ronde de queen bee naar dezelfde pos
        // Dit mag niet omdat de cell al een tile bevat namelijk de grashopper

        assertThrows(Hive.IllegalMove.class, () -> main.play(QUEEN_BEE, 1, 2));

    }

    @Test void whenTileIsPlayedGetNeighbours() throws Hive.IllegalMove {
        // Maak een nieuw spel aan
        Main main = new Main();

        // Speel de de grashopper naar pos 0,0
        main.play(GRASSHOPPER, 0, 0);

        // Maak een nieuwe arraylist aan met aangrenzende velden (neighbours)
        ArrayList<Cell> actualNeighbours = new ArrayList<Cell>();

        // Haal de cell op met pos 0,0
        Cell cell = main.getBoard().getCell(0,0);

        // NORTH_WEST
        actualNeighbours.add(new Cell(0, -1));
        // WEST
        actualNeighbours.add(new Cell(-1, 0));
        // NORTH_EAST
        actualNeighbours.add(new Cell(+1, -1));
        // SOUTH_WEST
        actualNeighbours.add(new Cell(-1, +1));
        // SOUTH_EAST
        actualNeighbours.add(new Cell(0, +1));
        // EAST
        actualNeighbours.add(new Cell(+1, -0));

        // We halen de arrayList op met de berekende neighbours
        ArrayList<Cell> calculatedNeighbours = main.getBoard().GetNeighboursFromCell(cell);

        // Assert
        assertEquals(actualNeighbours.get(0), calculatedNeighbours.get(0));
        assertEquals(actualNeighbours.get(1), calculatedNeighbours.get(1));
        assertEquals(actualNeighbours.get(2), calculatedNeighbours.get(2));
        assertEquals(actualNeighbours.get(3), calculatedNeighbours.get(3));
        assertEquals(actualNeighbours.get(4), calculatedNeighbours.get(4));
        assertEquals(actualNeighbours.get(5), calculatedNeighbours.get(5));
    }

    @Test void whenTileIsPlayedGetNeighboursWithNoTiles() throws Hive.IllegalMove {

        // Maak een nieuw spel aan
        Main main = new Main();

        // Speel de grashopper tile van player white naar pos 0,0
        main.play(GRASSHOPPER, 0, 0);

        // Speel de queen bee tile van player black naar pos 1,0 (NORTH_WEST) TOV grashopper
        main.play(QUEEN_BEE, 0, -1);

        // Maak een nieuwe arraylist aan met aangrenzende velden (neighbours) met geen tiles
        ArrayList<Cell> actualNeighboursWithNoTiles = new ArrayList<Cell>();

        // WEST
        actualNeighboursWithNoTiles.add(new Cell(-1, 0));
        // NORTH_EAST
        actualNeighboursWithNoTiles.add(new Cell(+1, -1));
        // SOUTH_WEST
        actualNeighboursWithNoTiles.add(new Cell(-1, +1));
        // SOUTH_EAST
        actualNeighboursWithNoTiles.add(new Cell(0, +1));
        // EAST
        actualNeighboursWithNoTiles.add(new Cell(+1, -0));

        // We halen de arrayList op met de berekende neighbours
        ArrayList<Cell> neighboursWithNoTiles = main.getBoard().GetNeighboursFromCellWithNoTiles(cell);

        // Assert
        assertEquals(actualNeighboursWithNoTiles.get(0), neighboursWithNoTiles.get(0));
        assertEquals(actualNeighboursWithNoTiles.get(1), neighboursWithNoTiles.get(1));
        assertEquals(actualNeighboursWithNoTiles.get(2), neighboursWithNoTiles.get(2));
        assertEquals(actualNeighboursWithNoTiles.get(3), neighboursWithNoTiles.get(3));
        assertEquals(actualNeighboursWithNoTiles.get(4), neighboursWithNoTiles.get(4));
        assertEquals(actualNeighboursWithNoTiles.get(5), neighboursWithNoTiles.get(5));

    }

}
