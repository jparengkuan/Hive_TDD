package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SoldierAntMovementTests {

    //8a Een soldatenmier verplaatst zich door een onbeperkt aantal keren te verschuiven
    @Test
    void testSoldierAntMoveBehaviour(){
        HiveBoard hiveBoard = spy(HiveBoard.class);

        when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
            put(new Hexagon(0,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(0,1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
        }});

        SoldierAntMoveBehaviour soldierAnt = new SoldierAntMoveBehaviour();

        assertDoesNotThrow(() -> soldierAnt.moveIsPossible(hiveBoard, new Hexagon(1,1), new Hexagon(0, -1)));
    }

    //8b Een soldatenmier mag zich niet verplaatsen naar het veld waar hij al staat
    @Test
    void WhenPlayerTriesToMoveSoldierAntToStartPositionThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, +2, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, +1, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(+2, 0, +2, 0));
    }



}
