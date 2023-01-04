package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HiveGameLogic {

    // 3. Spelverloop Wit heeft de eerste beurt.
    @Test
    void whenGameStartThenPlayersItsWhiteturn()
    {
        HiveGame hiveGame = new HiveGame();
        player = hiveGame.getCurrenPlayer();
        assertEquals(Hive.Player.WHITE, player);
    }

}
