package tests;

import minesweeper.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class SettingsTest {
    Settings settings = new Settings(true);
    Board board;

    @Test
    public void testDiff() {
        board = settings.diff("intermediate");
        assertSame(board.GetWidth(), 16);
        assertSame(board.GetMines(), 40);

    }
}
