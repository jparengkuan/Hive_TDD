package nl.hanze.hive;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static nl.hanze.hive.Hive.Player.BLACK;
import static nl.hanze.hive.Hive.Player.WHITE;
import static nl.hanze.hive.Hive.Tile.*;
import static org.junit.jupiter.api.Assertions.*;

public class SanityCheck {
    static Class<? extends Hive> hiveClass;
    Hive hive;

    @BeforeAll
    static void setUpClass() throws IOException, URISyntaxException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources("");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if (!url.getProtocol().equals("file")) {
                continue;
            }
            Deque<File> queue = new ArrayDeque<>();
            queue.addFirst(new File(url.toURI()));
            String path = queue.peekFirst().getPath();
            while (!queue.isEmpty()) {
                File file = queue.remove();
                String name = file.getName();
                if (file.isDirectory() && !name.contains(".")) {
                    for (File child : file.listFiles()) {
                        queue.addFirst(child);
                    }
                } else if (file.isFile() && name.endsWith(".class")) {
                    Class cls = Class.forName(file.getPath().substring(path.length() + 1, file.getPath().length() - 6).replace(File.separator, "."));
                    if (!cls.equals(Hive.class) && Hive.class.isAssignableFrom(cls)) {
                        if (hiveClass != null) {
                            fail("Multiple implementation of " + Hive.class.getCanonicalName() + " found");
                        }
                        hiveClass = cls;
                    }
                }
            }
        }
        if (hiveClass == null) {
            fail("No implementations of " + Hive.class.getCanonicalName() + " found");
        }
    }

    @BeforeEach
    void setUp() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        hive = hiveClass.getDeclaredConstructor().newInstance();
    }

    @Test
    void testSanityCheck() throws Hive.IllegalMove {
        hive.play(QUEEN_BEE, 1, 2);
        assertThrows(Hive.IllegalMove.class, () -> hive.play(QUEEN_BEE, 1, 2));
        hive.play(QUEEN_BEE, 1, 1);
        assertThrows(Hive.IllegalMove.class, () -> hive.move(1, 2, 1, 3));
        hive.move(1, 2, 2, 1);
    }

    // (1c) Check if each deck got the right amounts of the tiles from the requirement.
    @Test
    void whenStartOfGameCheckDeckForPlayerWhite(){

        //Arrange maak een nieuwe deck aan voor player white
        Main main = new Main();

        // Act de methode aan roepen die moet worden getest
        // ! Deck moet een nieuwe klasse worden met een getter
        // Doen we morgen tijdens de refactor

        // Assert controleren van de uitvoer
        assertEquals(1, main.countTiles(QUEEN_BEE, WHITE));
        assertEquals(2, main.countTiles(SPIDER, WHITE));
        assertEquals(2, main.countTiles(BEETLE, WHITE));
        assertEquals(3, main.countTiles(SOLDIER_ANT, WHITE));
        assertEquals(3, main.countTiles(GRASSHOPPER, WHITE));
    }

    // (2a) Test if the cells on the board have (q,r) coordinates
    @Test
    void whenBoarcCheckCellCoordinates(){
        Board board = new Board();
        ArrayList<Cell> cells = board.getCells();
        for (Cell cell : cells) {
            assertNotNull(cell.q);
            assertNotNull(cell.r);
        }
    }

    // (2b) Test if a tile has six adjacent cells
    @Test
    void testAdjacentCells(){
        Board board = new Board();
        int[][] directions = new int[][] {Main.NORTH_WEST, Main.WEST, Main.NORTH_EAST, Main.SOUTH_WEST, Main.SOUTH_EAST, Main.EAST};
        board.addCell(0, 0);
        board.addCell(1,0);
        board.addCell(0,1);
        board.addCell(-1,1);
        board.addCell(-1,0);
        board.addCell(0,-1);
        board.addCell(1, -1);
        Cell middleCell = new Cell(0,0);
        for(Cell cell : board.getCells()){
            if(cell.q == 0 && cell.r == 0){
                middleCell = cell;
            }
        }
        for(int[] direction : directions){
            assertNotNull(board.getCell(middleCell.q + direction[0], middleCell.r + direction[1]));
        }
    }


    // (2e) Test if tiles can be played
    @Test
    void whenTilePlayed() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(BEETLE, 0, 0);
        Gametile tilePlayed = main.getBoard().getCell(0,0).getTiles().peek();
        assertEquals(BEETLE, tilePlayed.getTileName());

    }

    // (2d) is a bit difficult to test if tiles can already be in one place only

    // (2e) Test if tiles can be moved
    @Test
    void whenTileMoved() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(BEETLE, 0, 0);
      //  main.move(0, 0, 1, 0);
      //  assertEquals(BEETLE, main.getBoard().getCell(1, 0).getTiles().peek().getTileName());
        assertThrows(Hive.IllegalMove.class, () -> main.move(6, -4, 5, 3));
    }

    // (2f) Test if tiles can be on top of each other
    @Test
    void whenTileOnTopOfAnotherTile() throws Hive.IllegalMove {

        // Maak een nieuw spel aan
        Main main = new Main();

        // Player white speelt spider naar pos 0,0
        main.play(SPIDER, 0, 0);

        // Player black speelt grashopper naar 1,1
        main.play(GRASSHOPPER, 1, 1);

        // Player white verplaasts zijn spider bovenop de grashopper van player black
        main.move(0,0,1,1);

        // Check of er nu twee stenen staan op pos 1,1
        assertEquals(2, main.getBoard().getCell(1,1).getTiles().size());
    }

    // (3a) Test to make sure white has the first turn
    @Test
    void whenGameStartThenWhiteHasFirstTurn(){
        Main main = new Main();
        assertEquals(WHITE, main.getTurn());
    }

    // (3b) Test to make sure turn changes after player plays tile
    @Test
    void whenPlayerDoesPlayThenOtherPlayerHasTurn() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(GRASSHOPPER, 0, 0);
        assertEquals(BLACK, main.getTurn());
    }

    // (3b) Test to make sure turn changes after player moves tile
    @Test
    void whenPlayerDoesMoveThenOtherPlayerHasTurn() throws Hive.IllegalMove {
        Main main = new Main();
        main.play(BEETLE, 0, 0);
        main.move(0, 0, 1, 0);
        assertEquals(WHITE, main.getTurn());
    }

    // (3c) Test to make sure queen bee is surrounded.
   @Test
    void givenQueenBeeWhenSurroundedByTilesThenOpponentWins() throws Hive.IllegalMove {
        Main main = new Main();
        Board board = main.getBoard();
        main.play(QUEEN_BEE, 0, 0);
        board.addCell(0, -1);
        board.getCell(0, -1).getTiles().add(new Gametile(BLACK, SPIDER));
        board.addCell(0, 1);
        board.getCell(0,1).getTiles().add(new Gametile(BLACK, BEETLE));
        board.addCell(1, 0);
        board.getCell(1,0).getTiles().add(new Gametile(BLACK, BEETLE));
        board.addCell(-1, 0);
        board.getCell(-1, 0).getTiles().add(new Gametile(BLACK, BEETLE));
        board.addCell(-1, 1);
        board.getCell(-1, 1).getTiles().add(new Gametile(BLACK, SOLDIER_ANT));
        board.addCell(1, -1);
        board.getCell(1, -1).getTiles().add(new Gametile(BLACK, GRASSHOPPER));
        assertTrue(main.isWinner(BLACK));
    }

    // (3d) Test to make sure it's a draw if both players win at the same time.
    @Test
    void givenPlayersWhenBothQueenBeesSurroundedAtSameTimeThenDraw() throws Hive.IllegalMove {
        Main main = new Main();
        Board board = main.getBoard();
        board.addCell(1,-1);
        board.getCell(1,-1).getTiles().add(new Gametile(WHITE, QUEEN_BEE));
        board.addCell(-1, 1);
        board.getCell(-1,1).getTiles().add(new Gametile(BLACK, QUEEN_BEE));
        board.addCell(0, 1);
        board.getCell(0,1).getTiles().add(new Gametile(BLACK, GRASSHOPPER));
        board.addCell(1, 0);
        board.getCell(1,0).getTiles().add(new Gametile(WHITE, SOLDIER_ANT));
        board.addCell(2,-1);
        board.getCell(2,-1).getTiles().add(new Gametile(BLACK, SOLDIER_ANT));
        board.addCell(2, -2);
        board.getCell(2, -2).getTiles().add(new Gametile(BLACK, GRASSHOPPER));
        board.addCell(1, -2);
        board.getCell(1, -2).getTiles().add(new Gametile(BLACK, BEETLE));
        board.addCell(0, -1);
        board.getCell(0, -1).getTiles().add(new Gametile(WHITE, BEETLE));
        board.addCell(-1,0);
        board.getCell(-1,0).getTiles().add(new Gametile(WHITE, BEETLE));
        board.addCell(-2,1);
        board.getCell(-2,1).getTiles().add(new Gametile(BLACK, BEETLE));
        board.addCell(-2, 2);
        board.getCell(-2,2).getTiles().add(new Gametile(WHITE, SPIDER));
        board.addCell(-1, 2);
        board.getCell(-1,2).getTiles().add(new Gametile(WHITE, SPIDER));
        board.addCell(0,0);
        board.getCell(0,0).getTiles().add(new Gametile(BLACK, SPIDER));
        assertTrue(main.isDraw());
    }
}
