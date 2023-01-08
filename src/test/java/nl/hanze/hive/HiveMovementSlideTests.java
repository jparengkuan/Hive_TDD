package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HiveMovementSlideTests {

    //5c.Tijdens een verschuiving moet de steen continu in contact blijven met
    //minstens één andere steen.
    @Test
    void WhenTileIsLosingContactWithAnotherTileDuringASlideThrowError() throws Hive.IllegalMove {

        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, -1, 1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, -1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 0, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, 1, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, 1, 0);

        hiveGame.move(-1, 1, 0, 1);

    }
}
