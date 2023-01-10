package nl.hanze.hive;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void whenPlayerMovesATileToAnLocationWithNoNeighboursThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 1);
            put(Hive.Tile.SPIDER, 2);
            put(Hive.Tile.BEETLE, 2);
            put(Hive.Tile.SOLDIER_ANT, 3);
            put(Hive.Tile.GRASSHOPPER, 3);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, 1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, -1, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(-1, 0, -5, -5));
    }

    @Test
    void WhenChainLengthIsThreeAssertTrue()
    {
        HiveBoard hiveBoard = spy(HiveBoard.class);
        when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
            put(new Hexagon(-1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(0,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
        }});

        assertTrue(hiveBoard.getConnectedTilesCount(new Hexagon(0,0)) == 3);

    }

    //5d. Een steen mag niet verplaatst worden als er door het weghalen van de steen
    // twee niet onderling verbonden groepen stenen ontstaan.
    @Test
    void WhenAPlayerMakesAMoveAndSplitsTheHiveThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); //white
        hiveGame.play(Hive.Tile.QUEEN_BEE, -1, 0); //black;
        hiveGame.play(Hive.Tile.GRASSHOPPER, 1, 0); //white
        hiveGame.play(Hive.Tile.GRASSHOPPER, -2, 0); //black;
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(0, 0, 1, -1));
    }


}
