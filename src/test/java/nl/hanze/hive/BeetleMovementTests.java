package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BeetleMovementTests {

    //7a Een kever verplaatst zich door precies één keer te verschuiven.
    @Test
    void WhenPlayerTriesToMoveBeetleMultiplePositionsThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, +2, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, -1, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(+2, 0, 0, 0));
    }

    //7a Een kever kan zich verplaatsen op een bezet veld
    @Test
    void WhenPlayerTriesToMoveBeetleToAnOccupiedFieldThrowNowError() {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, 1, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, 0, 1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, 1, 0);

        assertDoesNotThrow(() -> hiveGame.move(1, 0, 0, 0));

    }

    @Test
    void WhenPlayerTriesToMoveBeetleFromAndToOccupiedFieldThrowNowError() {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.BLACK, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, 0, 1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, 0, 1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.BLACK, 0, -1);

        assertDoesNotThrow(() -> hiveGame.move(0, 1, 0, 0));

    }

    //7a Een kever kan zich niet verplaatsen naar een veld waar al vier stenen op staan
    //Bron HiveGame by BlueLines Gamestudios
    @Test
    void WhenPlayerTriesToMoveBeetleToAnOccupiedFieldWithFourTilesThrowError() {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 0);
        }});

        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, 1, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, 0, 1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.BEETLE, Hive.Player.WHITE, 1, 0);

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.move(1, 0, 0, 0));

    }

}
