package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
