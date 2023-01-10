package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

public class HiveGameLogic {

    //1c. Elke speler heeft aan het begin van het spel de beschikking over één
    // bijenkoningin, twee spinnen, twee kevers, drie soldatenmieren en drie
    // sprinkhanen in zijn eigen kleur.
    @Test
    void whenGameStartThenPlayerHaveACompleteDeck() {
        HiveGame hiveGame = new HiveGame();
        HashMap<Hive.Tile, Integer> playersDeck = hiveGame.getPlayersDeck(Hive.Player.WHITE);
        assertEquals(playersDeck.get(Hive.Tile.QUEEN_BEE), 1);
        assertEquals(playersDeck.get(Hive.Tile.SPIDER), 2);
        assertEquals(playersDeck.get(Hive.Tile.BEETLE), 2);
        assertEquals(playersDeck.get(Hive.Tile.SOLDIER_ANT), 3);
        assertEquals(playersDeck.get(Hive.Tile.GRASSHOPPER), 3);
    }

    // 3. Spelverloop Wit heeft de eerste beurt.
    @Test
    void whenGameStartThenPlayersItsWhiteturn() {
        HiveGame hiveGame = new HiveGame();
        Hive.Player player = hiveGame.getCurrenPlayer();
        assertEquals(Hive.Player.WHITE, player);
    }

    // 3 Tijdens zijn beurt kan een speler een steen spelen, een steen verplaatsen of
    // passen; daarna is de tegenstander aan de beurt
    @Test
    void whenPlayerMakesAMoveThenGiveTurntoOppositePlayer() throws Hive.IllegalMove {
        HiveGame hiveGame = new HiveGame();
        hiveGame.play(Hive.Tile.GRASSHOPPER, 0, 0);
        assertEquals(Hive.Player.BLACK, hiveGame.getCurrenPlayer());
    }

    // 3c Een speler wint als alle zes velden naast de bijenkoningin van de
    //tegenstander bezet zijn

    @Test
    void whenPlayerBlacksQueenHisBeeIsSurroundedThenPlayerWhiteWins() {
        HiveGame hiveGame = spy(HiveGame.class);

        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.BLACK, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, 0, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 1, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, -1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, -1, +1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 0, 1);

        assertTrue(hiveGame.isWinner(Hive.Player.WHITE));
    }

    @Test
    void whenPlayerWhiteQueenHisBeeIsSurroundedThenPlayerBlackWins() {
        HiveGame hiveGame = spy(HiveGame.class);

        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, 0, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, 0, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 1, -1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, -1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 1, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, -1, +1);
        hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 0, 1);

        assertTrue(hiveGame.isWinner(Hive.Player.BLACK));
    }

    @Test
    void whenQueenBeeisNotSurroundedThereIsNoWinner() {
        HiveGame hiveGame = spy(HiveGame.class);

        for (Hive.Player player : Hive.Player.values()) {
            hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, player, 0, 0);
            hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, 0, -1);
            hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 1, -1);
            hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, -1, 0);
            hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.BLACK, -1, +1);
            hiveGame.hiveBoard.placeTile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE, 0, 1);

            assertFalse(hiveGame.isWinner(player));

        }
    }

    // 3d. Als beide spelers tegelijk zouden winnen is het in plaats daarvan een
    //gelijkspel.

    @Test
    void whenBothPlayersHaveASurroundedQueenBeeThenItsADraw() {

        HiveGame hiveGame = spy(HiveGame.class);

        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, -2, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.BLACK, 1, 0);

        for (Hexagon neighbour : new Hexagon(-2, 0).getAllNeighBours()) {
            hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, neighbour.q, neighbour.r);
        }

        for (Hexagon neighbour : new Hexagon(1, 0).getAllNeighBours()) {
            hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.BLACK, neighbour.q, neighbour.r);
        }

        assertTrue(hiveGame.isDraw());
    }

    @Test
    void whenOnlyPlayerWhiteIsSurroundedThenItsNotaDraw() {
        HiveGame hiveGame = spy(HiveGame.class);

        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE, -2, 0);
        hiveGame.hiveBoard.placeTile(Hive.Tile.QUEEN_BEE, Hive.Player.BLACK, 1, 0);

        for (Hexagon neighbour : new Hexagon(-2, 0).getAllNeighBours()) {
            hiveGame.hiveBoard.placeTile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE, neighbour.q, neighbour.r);
        }

        assertFalse(hiveGame.isDraw());

    }


}
