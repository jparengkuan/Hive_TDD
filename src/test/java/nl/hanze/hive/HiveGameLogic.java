package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class HiveGameLogic {

    //1c. Elke speler heeft aan het begin van het spel de beschikking over één
    // bijenkoningin, twee spinnen, twee kevers, drie soldatenmieren en drie
    // sprinkhanen in zijn eigen kleur.
    @Test
    void whenGameStartThenPlayerHaveACompleteDeck() {
        HiveGame hiveGame = new HiveGame();
        HashMap<Hive.Tile, Integer> playersDeck = hiveGame.getPlayersDeck(Hive.Player.WHITE);
        assertEquals(Hive.Tile.QUEEN_BEE, 1);
        assertEquals(Hive.Tile.SPIDER, 2);
        assertEquals(Hive.Tile.BEETLE, 2);
        assertEquals(Hive.Tile.SOLDIER_ANT, 3);
        assertEquals(Hive.Tile.GRASSHOPPER, 3);

    }

    // 3. Spelverloop Wit heeft de eerste beurt.
    @Test
    void whenGameStartThenPlayersItsWhiteturn()
    {
        HiveGame hiveGame = new HiveGame();
        Hive.Player player = hiveGame.getCurrenPlayer();
        assertEquals(Hive.Player.WHITE, player);
    }

    // 3 Tijdens zijn beurt kan een speler een steen spelen, een steen verplaatsen of
    // passen; daarna is de tegenstander aan de beurt
    @Test
    void whenPlayerMakesAMoveThenGiveTurntoOppositePlayer() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.GRASSHOPPER, 0,0);
        assertEquals(Hive.Player.BLACK, hiveGame.getCurrenPlayer());
    }


}
