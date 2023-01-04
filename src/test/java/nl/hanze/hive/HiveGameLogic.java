package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HiveGameLogic {

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
