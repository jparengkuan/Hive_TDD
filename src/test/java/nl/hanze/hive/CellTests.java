package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static nl.hanze.hive.Hive.Tile.GRASSHOPPER;
import static nl.hanze.hive.Hive.Tile.QUEEN_BEE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        //
        ArrayList<Cell> neighbours = new ArrayList<Cell>();
        Cell cell = main.getBoard().getCell(0,0);

        // Assert
        assertEquals(neighbours, main.getBoard().GetNeighboursFromCell(cell));

    }

}
