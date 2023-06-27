package tests;

import minesweeper.Board;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class BoardTest {
    Board board;

    @Before
    public void setUp() {
        board = new Board(9, 9, 10);

    }

    @Test
    public void testCheckIndexX() {
        boolean checkX1 = board.CheckIndexX(10);
        boolean checkX2 = board.CheckIndexX(5);

        assertFalse(checkX1);
        assertTrue(checkX2);
    }

    @Test
    public void testCheckIndexY() {
        boolean checkY1 = board.CheckIndexY(-1);
        boolean checkY2 = board.CheckIndexX(0);

        assertFalse(checkY1);
        assertTrue(checkY2);
    }

    @Test
    public void testPlaceMines() {
        board.PlaceMines();
        int mines = 0;
        for (List<Integer> list : board.getBoard()) {
            for (int item : list) {
                if(item == 9) mines++;
            }
        }
        assertEquals(10, mines);
    }

    @Test
    public void testGenerateBoard() {
        int count = 0;
        board.GenerateBoard();
        for (List<Integer> list : board.getBoard()) {
            for (int item : list) {
                if(item != 0) count++;
            }
        }
        assertNotEquals(71, count);
    }

    @Test
    public void testSetValue() {
        board.setValue(2, 3, 5);
        int value = board.GetValue(2, 3);
        assertSame(5, value);
    }






}
