package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTests {

    // (2c) Test if board is empty at the start
    @Test
    void whenGameStartThenBoardIsEmpty(){
        Board board = new Board();
        assertTrue(board.isEmpty());
    }


}
