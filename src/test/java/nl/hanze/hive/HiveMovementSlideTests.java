package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HiveMovementSlideTests {

    //6b. Een verschuiving moet schuivend uitgevoerd kunnen worden. Dit betekent
    //dat tijdens de verschuiving moet gelden dat de laagste van de twee stapels
    //die aan het begin- en eindpunt grenzen niet hoger mag zijn dan de hoogste
    //van de twee stapels op het begin- en eindpunt, waarbij de te verplaatsen
    //steen niet mee telt

    @Test
    void whenTileIsNotSlideToAdjacentPositionReturnFalse() throws Hive.IllegalMove {
        HiveBoard hiveBoard = spy(HiveBoard.class);

        when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
            put(new Hexagon(+1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(-2,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
        }});

        QueenBeeMoveBehaviour queenBee = new QueenBeeMoveBehaviour();

        assertFalse(queenBee.slideIsPossible(hiveBoard, new Hexagon(-1, 0), new Hexagon(+1, 0)));

    }

    //6c.Tijdens een verschuiving moet de steen continu in contact blijven met
    //minstens één andere steen.
    @Test
    void WhenTileIsLosingContactWithAnotherTileDuringASlideReturnFalse() {

        HiveBoard hiveBoard = spy(HiveBoard.class);
        when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
            put(new Hexagon(-1,1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(-1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(0,-1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
            put(new Hexagon(1,-1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
            put(new Hexagon(1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
        }});

        QueenBeeMoveBehaviour queenBee = new QueenBeeMoveBehaviour();

        assertFalse(queenBee.slideIsPossible(hiveBoard, new Hexagon(0, 1), new Hexagon(-1, 1)));

    }
@Test
    void WhenTileINotLosingContactWithAnotherTileDuringASlideReturnTrue() {

    HiveBoard hiveBoard = spy(HiveBoard.class);
    when(hiveBoard.getHiveboard()).thenReturn(new HashMap<Hexagon, TileStack>() {{
        put(new Hexagon(-1,1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
        put(new Hexagon(-1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
        put(new Hexagon(0,-1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
        put(new Hexagon(0,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
        put(new Hexagon(1,-1), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)));
        put(new Hexagon(1,0), new TileStack(new HiveTile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)));
    }});

    QueenBeeMoveBehaviour queenBee = new QueenBeeMoveBehaviour();

    assertTrue(queenBee.slideIsPossible(hiveBoard, new Hexagon(0, 1), new Hexagon(-1, 1)));

    }
}
