package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import nl.hanze.hive.Hive.Player;

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
        Player playerWhite = Player.WHITE;

        // Assert
        assertEquals(1, deckPlayerWhite.countTiles(QUEEN_BEE, playerWhite));
        assertEquals(2, deckPlayerWhite.countTiles(SPIDER, playerWhite));
        assertEquals(2, deckPlayerWhite.countTiles(BEETLE, playerWhite));
        assertEquals(3, deckPlayerWhite.countTiles(GRASSHOPPER, playerWhite));
        assertEquals(3, deckPlayerWhite.countTiles(SOLDIER_ANT, playerWhite));
    }

    /**
     *  (1c) Check if each deck got the right amounts of the tiles from the requirement
     *       For player black
     */
    @Test
    void whenStartOfGameCheckDeckForPlayerBlack() {
        // Arrange
        Deck deckPlayerBlack = new Deck();
        Player playerBlack = Player.BLACK;

        // Assert
        assertEquals(1, deckPlayerBlack.countTiles(QUEEN_BEE, playerBlack));
        assertEquals(2, deckPlayerBlack.countTiles(SPIDER, playerBlack));
        assertEquals(2, deckPlayerBlack.countTiles(BEETLE, playerBlack));
        assertEquals(3, deckPlayerBlack.countTiles(GRASSHOPPER, playerBlack));
        assertEquals(3, deckPlayerBlack.countTiles(SOLDIER_ANT, playerBlack));
    }

    @Test
    void whenStartOfGameGetDeckFromPlayerWhite() {

        // Arrange
        Deck deckPlayerWhite = new Deck();
        Player playerWhite = Player.WHITE;

        // Assert


    }

}
