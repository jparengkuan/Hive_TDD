package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class GrassHopperMovementTests {

    //8b Een sprinkhaan mag zich niet verplaatsen naar het veld waar hij al staat.
    @Test
    void WhenPlayerTriesToMoveGrassHopperToStartPositionThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, +2, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, +1, 0);

        hiveGame.move(+2, 0, +2, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(+2, 0, +2, 0));
    }


}
