package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class GrassHopperMovementTests {

    //11a Een sprinkhaan verplaatst zich door in een rechte lijn een sprong te maken
    //naar een veld meteen achter een andere steen in de richting van de sprong.
    @Test
    public void WhenPlayerMovesGrashopperInStraightDirectionReturnTrue() throws Hive.IllegalMove {
        HiveBoard hiveBoard = spy(HiveBoard.class);
        when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
            put(new Hexagon(-1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(0,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
        }});

        GenericSlideBehaviour grassHopper = new GrassHopperMoveBehaviour();

        assertTrue(grassHopper.moveIsPossible(hiveBoard, new Hexagon(2,0), new Hexagon(-2, 0)));
    }

    //11b Een sprinkhaan mag zich niet verplaatsen naar het veld waar hij al staat.
    @Test
    void WhenPlayerTriesToMoveGrassHopperToStartPositionThrowError() {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, +2, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, +1, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(+2, 0, +2, 0));
    }

    //11d Een sprinkhaan mag niet naar een bezet veld springen.
    @Test
    void WhenPlayerTriesToMoveGrassHopperToOccupiedFieldThrowError() {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, -2, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, -1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, 0, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(-22, 0, 0, 0));
    }


}
