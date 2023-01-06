package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class HivePlayTests {

    //4a Een speler mag alleen zijn eigen nog niet gespeelde stenen spelen
    @Test
    void WhenPlayerTriesToPlayQueenBeeTwiceThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); // White
        hiveGame.play(Hive.Tile.GRASSHOPPER, 1, 0); // Black
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.play(Hive.Tile.QUEEN_BEE, 2, 0));
    }

    @Test
    void givenAPlayerDoesNotHaveACertainTileinPlayersDeckThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = spy(HiveGame.class);
        when(hiveGame.getPlayersDeck(hiveGame.getCurrenPlayer())).thenReturn(new HashMap<Hive.Tile, Integer>()
        {{
            put(Hive.Tile.QUEEN_BEE, 1);
            put(Hive.Tile.SPIDER, 2);
            put(Hive.Tile.BEETLE, 2);
            put(Hive.Tile.SOLDIER_ANT, 3);
            put(Hive.Tile.GRASSHOPPER, 0);
        }});

        assertThrows(Hive.IllegalMove.class, () -> hiveGame.playTileFromDeck(Hive.Tile.GRASSHOPPER));
    }

    //4c Als er al stenen op het bord liggen moet een naast een andere steen
    // gespeeld worden
    @Test
    void whenPlayerDoesNotPlacesAnTileNextToAnotherOneThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); // White
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 2));
    }

    //4d Als er stenen van beide spelers op het bord liggen mag een steen niet naast
    //een steen van de tegenstander geplaatst worden
    @Test
    void whenPlayerTriesToPlayATileNextToAnOpponentThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.QUEEN_BEE, 0, 0); // White
        hiveGame.play(Hive.Tile.GRASSHOPPER, 1, 0); // Black
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.play(Hive.Tile.GRASSHOPPER, 1, 1));
    }

    //4e Als een speler al drie stenen gespeeld heeft maar zijn bijenkoningin nog niet,
    // dan moet deze gespeeld worden
    @Test
    void whenPlayerHasNotPlayedQueenBeeAfterThreeRoundsThrowError() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.playTileFromDeck(Hive.Tile.GRASSHOPPER); // White
        hiveGame.playTileFromDeck(Hive.Tile.GRASSHOPPER); // White
        hiveGame.playTileFromDeck(Hive.Tile.SOLDIER_ANT); // White
        assertThrows(Hive.IllegalMove.class, () -> hiveGame.play(Hive.Tile.SOLDIER_ANT, 0, 0));
    }
}
