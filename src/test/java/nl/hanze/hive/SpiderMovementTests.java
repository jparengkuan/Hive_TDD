package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpiderMovementTests {

    //8a Een spin verplaatst zich door precies drie keer te verschuiven
    @Test
    void WhenSpiderIsMovedMoreThenThreePositionsThrowError() {

        HiveBoard hiveBoard = spy(HiveBoard.class);
        when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
            put(new Hexagon(0, 0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(1, 0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(0, 1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
        }});

        SpiderMoveBehaviour spider = new SpiderMoveBehaviour();

        assertThrows(Hive.IllegalMove.class, () -> spider.moveIsPossible(hiveBoard, new Hexagon(-1, 0), new Hexagon(1,1)));
    }

    @Test
    void WhenSpiderIsMovedThreePositionsReturnTrue() throws Hive.IllegalMove {

        HiveBoard hiveBoard = spy(HiveBoard.class);
        when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
            put(new Hexagon(0, 0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(1, 0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(0, 1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
        }});

        SpiderMoveBehaviour spider = new SpiderMoveBehaviour();

        assertTrue(spider.moveIsPossible(hiveBoard, new Hexagon(-1, 1), new Hexagon(1,1)));
    }

    //8b Een spin mag zich niet verplaatsen naar het veld waar hij al staat.
    @Test
    void WhenPlayerTriesToMoveSpiderToStartPositionThrowError() {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.SPIDER, Hive.Player.WHITE, +2, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, +1, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(+2, 0, +2, 0));
    }

    //8c Een spin mag alleen verplaatst worden over en naar lege velden
    @Test
    void whenPlayerTriesToMoveSpiderOverOccupiedFieldThrowError() throws Hive.IllegalMove {
        HiveBoard hiveBoard = spy(HiveBoard.class);
        when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
            put(new Hexagon(0, -1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(1, -1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(-1, 0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(1, 0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(-1, 1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(0, 1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
        }});

        SpiderMoveBehaviour spider = new SpiderMoveBehaviour();

        assertThrows(Hive.IllegalMove.class, () -> spider.moveIsPossible(hiveBoard, new Hexagon(0, 0), new Hexagon(-1,-1)));
    }

}
