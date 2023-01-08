package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HiveMovementSlideTests {

    //6b. Een verschuiving moet schuivend uitgevoerd kunnen worden. Dit betekent
    //dat tijdens de verschuiving moet gelden dat de laagste van de twee stapels
    //die aan het begin- en eindpunt grenzen niet hoger mag zijn dan de hoogste
    //van de twee stapels op het begin- en eindpunt, waarbij de te verplaatsen
    //steen niet mee telt

    @Test
    void whenTileIsNotSlideToAdjacentPositionThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, +1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, -2, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(+1, 0, -1, 0));

    }

    //6c.Tijdens een verschuiving moet de steen continu in contact blijven met
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

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(-1, 1, 0, 1));
    }
@Test
    void WhenTileINotsLosingContactWithAnotherTileDuringASlideDontThrowError() throws Hive.IllegalMove {

        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, -1, 1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, -1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 0, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, 1, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, 1, 0);

        assertDoesNotThrow(() -> hiveGame.move(-1, 1, 0, 1));

    }
}
