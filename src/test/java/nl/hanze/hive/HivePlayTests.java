package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HivePlayTests {

    //4a Een speler mag alleen zijn eigen nog niet gespeelde stenen spelen
    @Test
    void WhenPlayerTriesToPlayQueenBeeTwiceThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); // White
        hiveGame.play(Hive.Tile.GRASSHOPPER, 1, 0); // Black
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.play(Hive.Tile.QUEEN_BEE, 2, 0));
    }

    //4e Als een speler al drie stenen gespeeld heeft maar zijn bijenkoningin nog niet,
    // dan moet deze gespeeld worden
    @Test
    void whenPlayerHasNotPlayedQueenBeeAfterThreeRoundsThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.playTileFromDeck(Hive.Tile.GRASSHOPPER); // White
        hiveGame.playTileFromDeck(Hive.Tile.GRASSHOPPER); // White
        hiveGame.playTileFromDeck(Hive.Tile.SOLDIER_ANT); // White
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0));
    }
}
