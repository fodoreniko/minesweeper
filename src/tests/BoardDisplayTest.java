package tests;

import minesweeper.*;

import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class BoardDisplayTest {
    Board board = new Board(9, 9, 10);
    BoardDisplay bd = new BoardDisplay(board, "");


    @Test
    public void testUncover(){
        int uncovered = bd.getUncovered();
        bd.uncover(5, 1, 1);
        int uncovered2 = bd.getUncovered();
        assertNotSame(uncovered, uncovered2);
    }

    @Test
    public void testUncoverAll(){
        bd.uncoverAll();
        int uncovered = bd.getUncovered();
        assertSame(81, uncovered);
    }


    @Test
    public void testLose() {
        Icon face1 = bd.getFace().getIcon();
        bd.lose();
        Icon face2 = bd.getFace().getIcon();
        assertNotSame(face1, face2);
    }

    @Test
    public void testWin() {
        File file = new File("beginner.txt");
        long n = file.length();
        try {
            bd.win();
        } catch (IOException e) {
            System.err.println("error");
        }
        long n2 = file.length();
        assertEquals(n, n2);
    }


}
