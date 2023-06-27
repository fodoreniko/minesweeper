package minesweeper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board implements Serializable {
    private int length;
    private int width;
    private int mines;
    private String difficulty;
    private ArrayList<List<Integer>> board;

    public Board(int l, int w, int m) {
        length = l;
        width = w;
        mines = m;
        board = new ArrayList<List<Integer>>();
        for (int i = 0; i < length; i++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < width; j++)
                list.add(0);
            board.add(list);
        }
    }

    public int GetLength() {
        return length;
    }
    public int GetWidth() {
        return width;
    }
    public int GetValue(int x, int y) {
        return board.get(x).get(y);
    }
    public void setValue(int x, int y, int value) {
        board.get(x).set(y, value);
    }
    public int GetMines() {
        return mines;
    }
    public void SetMines(int m) {
        mines = m;
    }

    /*
    public void PrintBoard() {
        for (List<Integer> list : board) {
            for (int item : list) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
    */

    /**
     * elhelyezi random a bombákat a táblán
     */
    public void PlaceMines() {
        Random r = new Random();
        for (int i = 0; i < mines; i++) {
            int randx = r.nextInt(length);
            int randy = r.nextInt(width);
            if (board.get(randx).get(randy).equals(9))
                i--;
            else
                board.get(randx).set(randy, 9);

        }

    }

    /**
     * megnézi, hogy az adott index nemnegatív és a tábla hosszánál kisebb-e
     * @param x - a vizsgált index
     * @return - visszaadja, hogy megfelelő-e az index
     */
    public boolean CheckIndexX(int x) {
        return (x >= 0 && x < length);
    }

    /**
     * megnézi, hogy az adott index nemnegatív és a tábla szélességénél kisebb-e
     * @param x - a vizsglt index
     * @return - visszaadja, hogy megfelelő-e az index
     */
    public boolean CheckIndexY(int x) {
        return (x >= 0 && x < width);
    }

    /**
     * kiszámítja a tábla mezőinek az értékét a bombák alapján
     */
    public void GenerateBoard() {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                if (!(board.get(x).get(y).equals(9))) {
                    int count = 0;
                    for (int i = x - 1; i <= x + 1; i++)
                        for (int j = y - 1; j <= y + 1; j++)
                            if (CheckIndexX(i) && CheckIndexY(j) && board.get(i).get(j).equals(9))
                                count++;
                    board.get(x).set(y, count);
                }
            }
        }
    }

    public void setDifficulty(String d) {
        difficulty = d;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<List<Integer>> getBoard() {
        return board;
    }
}
