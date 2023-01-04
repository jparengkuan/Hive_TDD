package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HiveGameLogic {

    @Test
    void whenGameStartThenPlayersItsWhiteturn()
    {
        hiveGame = new HiveGame();
        player = hiveGame.getPlayerTurn();
        assertEquals(Player.WHITE, player);
    }

}
