package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

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

    //5b. Een speler mag pas stenen verplaatsen als zijn bijenkoningin gespeeld is.
    @Test
    void whenPlayerHasNotPlayedQueenBeeAndDoesAMoveThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.GRASSHOPPER, 0, 0); //white
        hiveGame.play(Hive.Tile.GRASSHOPPER, 0, 1); //black;
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(0, 0, 1, 0));
    }

    //5c. Een steen moet na het verplaatsen in contact zijn met minstens één andere steen.
    @Test
    void whenPlayerMovesATileToAnLocationWithNoNeighboursThrowError()
    {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
        put(new Hexagon(0, 0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
        put(new Hexagon(1, 0), new TileStack(new HiveTile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE)));
        put(new Hexagon(-1, 0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER)));
        }});

        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
            put(Hive.Tile.SPIDER, 2);
            put(Hive.Tile.BEETLE, 2);
            put(Hive.Tile.SOLDIER_ANT, 3);
            put(Hive.Tile.GRASSHOPPER, 3);
        }});

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(-1, 0, -5, -5));
    }

}
