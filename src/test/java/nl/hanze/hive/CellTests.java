package nl.hanze.hive;

import com.sun.source.tree.NewArrayTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static nl.hanze.hive.Hive.Tile.*;
import static org.junit.jupiter.api.Assertions.*;

public class CellTests {

    // (4b)
    @Test
    public void checkIfCellHasNoTiles() {

        // Maak een nieuw bord
        Board board = new Board();

        // Voeg een cell toe aan het bord
        board.getCells().add(new Cell(1, 1));

        // Haal het cell object op
        Cell cell = board.getCell(1,1);

        // Assert
        assertTrue(cell.isEmpty());
    }

    // (4b)
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

        // Haal de cell op met pos 0,0 van de grashopper
        Cell cell = main.getBoard().getCell(0,0);

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

        // We halen de arrayList op met alle neighbours zonder tiles
        ArrayList<Cell> neighboursWithNoTiles = main.getBoard().GetNeighboursFromCellWithNoTiles(cell);

        // Assert we vergelijking hiermee de hardcoded waarden van de array actualNeighboursWithNoTiles
        // En de waarden van de array die de functie ons teruggeeft, deze moeten gelijk zijn

        assertEquals(actualNeighboursWithNoTiles.get(0), neighboursWithNoTiles.get(0));
        assertEquals(actualNeighboursWithNoTiles.get(1), neighboursWithNoTiles.get(1));
        assertEquals(actualNeighboursWithNoTiles.get(2), neighboursWithNoTiles.get(2));
        assertEquals(actualNeighboursWithNoTiles.get(3), neighboursWithNoTiles.get(3));
        assertEquals(actualNeighboursWithNoTiles.get(4), neighboursWithNoTiles.get(4));

    }

    // (4c)
    @Test
    void givenNonEmptyBoardWhenTileNotPlayedNextToOtherTileThenIllegalMove() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(GRASSHOPPER, 0, 0);
        main.play(QUEEN_BEE, 2, 1);
        // (assert) When the board is not empty, and WHITE tries to play a tile not adjacent to another WHITE tile, throw IllegalMove.
        assertThrows(Hive.IllegalMove.class, () -> main.play(SOLDIER_ANT, -3, -5));
    }

    // (4d)
    @Test
    void givenNonEmptyBoardWhenPlayerTilePlayedNextToOpponentTileThenIllegalMove() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(GRASSHOPPER, 0, 0);
        // (assert) When BLACK tries to place tile next to WHITE's tile, throw IllegalMove. (needs refactor)
        assertThrows(Hive.IllegalMove.class, () -> main.play(SOLDIER_ANT, 0, 1));
    }

    // (4e)
    @Test
    void whenPlayerHasPlayedThreeTilesThenPlayerMustPlayQueenBee() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(GRASSHOPPER, 0, 0);
        main.play(SOLDIER_ANT, 1, 5);
        main.play(GRASSHOPPER, 0, 1);
        main.play(BEETLE, 2, 5);
        main.play(SOLDIER_ANT, 0, 2);
        main.play(QUEEN_BEE, 3, 5);
        // (assert) When WHITE tries to play a tile other than QUEEN_BEE after three turns, throw IllegalMove.
        assertThrows(Hive.IllegalMove.class, () -> main.play(GRASSHOPPER, 1,0));
    }

    // (5a)
    @Test
    void whenTilesPlayedThenOnlyMoveOwnPlayerTiles() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(GRASSHOPPER, 0, 0);
        main.play(QUEEN_BEE, 2, 1);
        // (assert) When WHITE tries to move BLACK's tile, throw IllegalMove.
        assertThrows(Hive.IllegalMove.class, () -> main.move(2, 1, 4, 5));
    }

    // (5b)
    @Test
    void whenQueenBeePlayedOnlyThenMoveTiles() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(GRASSHOPPER, 0, 9);
        main.play(QUEEN_BEE, 3, -15);
        // (assert) When WHITE has not yet played QUEEN_BEE, but tries to move a tile, throw IllegalMove.
        assertThrows(Hive.IllegalMove.class, () -> main.move(0, 9, 5, 3));
    }

    // (5b)
    @Test
    void whenQueenBeePlayedDoNotThrowIllegalMoveUponMovingTile() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(QUEEN_BEE, 0, 0);
        main.play(GRASSHOPPER, 2, 4);
        // (assert) When WHITE has played QUEEN_BEE and tries to move a tile, do NOT throw IllegalMove.
        assertDoesNotThrow(() -> main.move(0, 0, 1, 0));
    }

    // (5c)
    @Test
    void whenTileMovedThenTileInContactWithAtLeastOneOtherTile() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(QUEEN_BEE, 1, 3);
        main.play(GRASSHOPPER, 2, 5);
        main.play(SOLDIER_ANT, 1, 4);
        main.play(SPIDER, 2, 6);
        assertThrows(Hive.IllegalMove.class, () -> main.move(1, 4, 7, -29));
    }

    // (5d)
    @Test
    void whenTitleIsMovedAndChainIsBrokenThrowIlligalMoveException() throws Hive.IllegalMove {
        Main main = new Main();
        //white
        main.play(QUEEN_BEE, -2,0);
        //black
        main.play(QUEEN_BEE, +1,0);
        //white
        main.play(GRASSHOPPER, -1,0);
        //black
        main.play(GRASSHOPPER, +2,0);
        //white
        main.move(-2, 0,-3,0);

        assertThrows(Hive.IllegalMove.class, () -> main.move(-2, 0, -3, 0));
    }
}
