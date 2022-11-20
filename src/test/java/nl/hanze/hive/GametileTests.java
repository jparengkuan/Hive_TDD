package nl.hanze.hive;
import nl.hanze.hive.Hive.Tile;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static nl.hanze.hive.Hive.Tile.*;

public class GametileTests {
    // (7a)
    @Test
    void whenBeetleIsMovedThenPushOnce() throws Hive.IllegalMove {
        Game game = new Game();
        game.play(BEETLE, 0, 0);
        game.move(0, 0, 1, 0);
        assertThrows(Hive.IllegalMove.class, () -> game.move(1, 0, 3, 0));
    }
}
