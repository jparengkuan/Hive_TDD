package nl.hanze.hive;
import org.junit.jupiter.api.Test;

import static nl.hanze.hive.Hive.Tile.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovementRulesTests {
    @Test
    void pushToAdjacentTile() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = game.getBoard();
        // White
        game.play(BEETLE, 0, 0);
        // move tile to adjacent tile
        game.move(0, 0, 1, 0);
        // check if move has been executed correctly
        assertTrue(board.getCell(0, 0).getTiles().isEmpty() && !board.getCell(1, 0).getTiles().isEmpty());
    }

    @Test
    void whenAnyGivenTileIsMovedThrowIlligalMoveWhenADuringMoveThereIsNoContact() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = game.getBoard();
        // White
        game.play(QUEEN_BEE, +1, +0);
        // Black
        game.play(QUEEN_BEE, 0, -3);
        // White
        game.play(GRASSHOPPER, +1, -1);
        // Black
        game.play(GRASSHOPPER, +1, -3);
        // White
        game.play(BEETLE, 0, -1);
        // Black
        game.play(GRASSHOPPER, +2, -3);
        // White
        game.play(BEETLE, -1, 0);
        // Black
        game.play(GRASSHOPPER, +3, -3);
        // White
        game.play(GRASSHOPPER, -1, +1);
        // black
        game.play(SPIDER, +3, -4);
        // White
       assertThrows(Hive.IllegalMove.class, () -> game.move(-1, +1, 0, +1));
    }

    @Test
    void WhenBeetleTileIsMovedMoreThatOnceThrowIllegalMove() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = game.getBoard();
        // White
        game.play(QUEEN_BEE, -2, -1);
        // Black
        game.play(QUEEN_BEE, +1, -1);
        // White
        game.play(BEETLE, -1, -1);
        // Black
        game.play(GRASSHOPPER, +2, -1);
        // White turn
        game.move(-1, -1, +1, -3);
        assertThrows(Hive.IllegalMove.class, () -> game.move(-1, -1, +1, -3));
    }

    @Test
    void WhenQueenBeeTileIsMovedMoreThatOnceThrowIllegalMove() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = game.getBoard();
        // White
        game.play(QUEEN_BEE, -2, -1);
        // Black
        game.play(QUEEN_BEE, +1, -1);
        // White
        game.play(BEETLE, -1, -1);
        // Black
        game.play(GRASSHOPPER, +2, -1);
        // White turn
        assertThrows(Hive.IllegalMove.class, () -> game.move(-2, -1, +1, -3));
    }

    @Test
    void WhenQueenBeeTileIsMovedToANonEmptyCellThrowIllegalMove() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = game.getBoard();
        // White
        game.play(QUEEN_BEE, -2, -1);
        // Black
        game.play(QUEEN_BEE, +1, -1);
        // White
        game.play(BEETLE, -1, -1);
        // Black
        game.play(GRASSHOPPER, +2, -1);
        // White turn
        assertThrows(Hive.IllegalMove.class, () -> game.move(-2, -1, -1, -1));

    }
}
