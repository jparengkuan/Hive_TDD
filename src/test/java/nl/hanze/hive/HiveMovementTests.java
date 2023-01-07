package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HiveMovementTests {

    //5a. Een speler mag alleen zijn eigen eerder gespeelde stenen verplaatsen.
    @Test
    void whenPlayerTriesToMoveOpponentsTileThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); //white
        hiveGame.play(Hive.Tile.GRASSHOPPER, 0, 1); //black;
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(0, 1, 1, 0));
    }

    //5a. Een speler kan alleen een steen verplaatsen als deze bestaat op het bord.
    @Test
    void whenPlayerTriesToMoveATileThatsDoesntExistsThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); //white
        hiveGame.play(Hive.Tile.GRASSHOPPER, 0, 1); //black;
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(1, 1, 1, 0));
    }
}
