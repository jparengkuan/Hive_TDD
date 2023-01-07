package nl.hanze.hive;

import org.junit.jupiter.api.Test;

public class HiveMovementTests {

    //5a. Een speler mag alleen zijn eigen eerder gespeelde stenen verplaatsen.

    @Test
    void whenPlayerTriesToMoveOpponentsTileThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); //white
        hiveGame.play(Hive.Tile.GRASSHOPPER, 0, 1); //black;
        hiveGame.move(0, 1, 1, 0);
    }
}
