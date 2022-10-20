package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import nl.hanze.hive.Hive.Player;

import static nl.hanze.hive.Hive.Player.WHITE;
import static nl.hanze.hive.Hive.Tile.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTests {

    /**
     *  (1c) Check if each deck got the right amounts of the tiles from the requirement
     *       For player white
     */
    @Test
    void whenStartOfGameCheckDeckForPlayerWhite() {
        // Arrange
        Deck deckPlayerWhite = new Deck();
        Player player = Player.WHITE;

        // Assert
        assertEquals(1, deckPlayerWhite.countTiles(QUEEN_BEE, player));
        assertEquals(2, deckPlayerWhite.countTiles(SPIDER, player));
        assertEquals(2, deckPlayerWhite.countTiles(BEETLE, player));
        assertEquals(3, deckPlayerWhite.countTiles(GRASSHOPPER, player));
        assertEquals(3, deckPlayerWhite.countTiles(SOLDIER_ANT, player));
    }

    /**
     *  (1c) Check if each deck got the right amounts of the tiles from the requirement
     *       For player black
     */
    void whenStartOfGameCheckDeckForPlayerBlack() {
        // Arrange
        Deck deckPlayerWhite = new Deck();
        Player player = Player.BLACK;

        // Assert
        assertEquals(1, deckPlayerWhite.countTiles(QUEEN_BEE, player));
        assertEquals(2, deckPlayerWhite.countTiles(SPIDER, player));
        assertEquals(2, deckPlayerWhite.countTiles(BEETLE, player));
        assertEquals(3, deckPlayerWhite.countTiles(GRASSHOPPER, player));
        assertEquals(3, deckPlayerWhite.countTiles(SOLDIER_ANT, player));
    }

}
