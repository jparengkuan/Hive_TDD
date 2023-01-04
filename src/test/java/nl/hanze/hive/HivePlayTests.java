package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HivePlayTests {

    @Test
    void WhenPlayerTriesToPlayQueenBeeTwiceThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); // White
        hiveGame.play(Hive.Tile.GRASSHOPPER, 1, 0); // Black
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.play(Hive.Tile.QUEEN_BEE, 2, 0));
    }
}
