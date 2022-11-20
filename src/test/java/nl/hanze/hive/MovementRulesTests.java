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
        game.play(QUEEN_BEE, 0, 0);
        game.play(QUEEN_BEE, 0, 1);
        // move tile to adjacent tile
        game.move(0, 0, 1, 0);
        // check if move has been executed correctly
        assertTrue(board.getCell(0, 0).getTiles().isEmpty() && !board.getCell(1, 0).getTiles().isEmpty());
    }

    // (6c)
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

    // (7a)
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
        // game.move(-1, -1, +1, -3);
        assertThrows(Hive.IllegalMove.class, () -> game.move(-1, -1, +1, -3));
    }

    // (8a)
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

    // (8b)
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

    // (10a)
    @Test
    void WhenSpiderIsMovedThreeSpotsThenDontThrowIllegalMove() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = new Board();
        // White
        game.play(QUEEN_BEE, -1, -2);
        // Black
        game.play(QUEEN_BEE, +1, +2);
        // White
        game.play(GRASSHOPPER, -1, -1);
        // Black
        game.play(SOLDIER_ANT, +1, +1);
        // White
        game.play(SPIDER, -1, +0);
        // Black
        game.play(BEETLE, +2, +0);
        // White
        game.play(SPIDER, +0, -2);
        // Black
        game.play(GRASSHOPPER, +2, +1);
        // White
        assertDoesNotThrow(() -> game.move(+0, -2, -1, +1));
    }

    // (10a)
    @Test
    void WhenSpiderIsMovedLessThanThreeTimesThenThrowIllegalMove() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = new Board();
        // White
        game.play(QUEEN_BEE, -1, -2);
        // Black
        game.play(QUEEN_BEE, +1, +2);
        // White
        game.play(GRASSHOPPER, -1, -1);
        // Black
        game.play(SOLDIER_ANT, +1, +1);
        // White
        game.play(SPIDER, +0, -2);
        // Black
        game.play(GRASSHOPPER, +2, +1);
        // White
        assertThrows(Hive.IllegalMove.class, () -> game.move(+0, -2, -1, +0));
    }

    // (10a)
    @Test
    void WhenSpiderIsMovedMoreThanThreeTimesThenThrowIllegalMove() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = new Board();
        // White
        game.play(QUEEN_BEE, -1, -2);
        // Black
        game.play(QUEEN_BEE, +1, +2);
        // White
        game.play(GRASSHOPPER, -1, -1);
        // Black
        game.play(SOLDIER_ANT, +1, +1);
        // White
        game.play(SPIDER, -1, +0);
        // Black
        game.play(BEETLE, +2, +0);
        // White
        game.play(SPIDER, +0, -2);
        // Black
        game.play(GRASSHOPPER, +2, +1);
        // White
        assertThrows(Hive.IllegalMove.class, () -> game.move(+0, -2, -2, -1));
    }

    // (10b)
    @Test
    void whenSpiderMovesToCurrentSpotThenThrowIllegalMove() throws Hive.IllegalMove {
        Game game = new Game();
        // White
        game.play(QUEEN_BEE, 0,  0);
        // Black
        game.play(QUEEN_BEE, 3, 5);
        // White
        game.play(SPIDER, 0, 1);
        // Black
        game.play(SPIDER, 3, 6);
        // White
        assertThrows(Hive.IllegalMove.class, () -> game.move(0, 1, 0, 1));
    }
    
    // (9a)
    @Test
    void WhenSoldierAntTileIsMovedToHisBeginningPositionsThrowIllegalMove() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = game.getBoard();
        // White
        game.play(QUEEN_BEE, -2, -1);
        // Black
        game.play(QUEEN_BEE, +1, -1);
        // White
        game.play(SOLDIER_ANT, -1, -1);
        // Black
        game.play(GRASSHOPPER, +2, -1);
        // White turn
        assertThrows(Hive.IllegalMove.class, () -> game.move(-1, -1, -1, -1));
    }

    @Test
    void WhenSoldierAntTileIsMovedToANonEmptyEndpoint() throws Hive.IllegalMove {
        Game game = new Game();
        Board board = game.getBoard();
        // White
        game.play(QUEEN_BEE, -2, -1);
        // Black
        game.play(QUEEN_BEE, +1, -1);
        // White
        game.play(SOLDIER_ANT, -1, -1);
        // Black
        game.play(GRASSHOPPER, +2, -1);
        // White turn
        assertThrows(Hive.IllegalMove.class, () -> game.move(-1, -1, -2, -1));
    }

}
