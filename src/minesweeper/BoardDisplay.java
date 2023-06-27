package minesweeper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;


public class BoardDisplay extends JFrame {
    private Board b;
    private JButton[][] buttons;
    private int minesLeft;
    private int uncovered = 0;
    private MyTimer timer = new MyTimer();
    private boolean firstClick = true;
    private JButton face;
    private String username;
    private ImageIcon covered = new ImageIcon(getClass().getResource("image/covered.png"));
    private ImageIcon[] numbers = new ImageIcon[10];

    public BoardDisplay(Board board, String u) {

        this.setVisible(true);
        username = u;
        b = board;
        buttons = new JButton[b.GetLength()][b.GetWidth()];
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        minesLeft = b.GetMines();
        Style s = new Style();
        ImageIcon flag = new ImageIcon(getClass().getResource("image/flag.png"));
        ImageIcon[] time = new ImageIcon[10];

        for(int i = 0; i < 10; i++) {
            time[i] = new ImageIcon(getClass().getResource("image/time" + i + ".png"));
        }
        for(int i = 0; i < 10; i++) {
            numbers[i] = new ImageIcon(getClass().getResource("image/" + i + ".png"));
        }

        JPanel panel0 = new JPanel();
        panel0.setLayout(new BorderLayout());
        panel0.setPreferredSize(new Dimension(222, 25));
        panel0.setBackground(new Color(185,185,185,255));
        panel0.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

        JButton menu = jbutton("menu",12, new Color(172, 172, 172),new Dimension(78, 20));

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setSize(280, 420);
                close();
            }
        });

        panel0.add(menu, BorderLayout.LINE_START);

        JPanel gamepanel = new JPanel();
        gamepanel.setLayout(new BoxLayout(gamepanel, BoxLayout.PAGE_AXIS));

        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(new Color(185,185,185,255), 6));
        s.border(gamepanel);

        JPanel panel2 = new JPanel();
        JPanel panel1Left = new JPanel();
        JPanel panel1Right = new JPanel();
        GridLayout gl = new GridLayout(b.GetLength(), b.GetWidth());
        BorderLayout bl = new BorderLayout();
        FlowLayout left = new FlowLayout(3, 0, 0);
        FlowLayout right = new FlowLayout(3, 0, 0);

        panel1Left.setLayout(left);
        panel1Right.setLayout(right);
        panel1.setLayout(bl);
        panel2.setLayout(gl);

        JLabel[] mineLabels = new JLabel[3];

        mineLabels[0] = new JLabel(time[minesLeft/100]);
        mineLabels[1] = new JLabel(time[(minesLeft%100) / 10]);
        mineLabels[2] = new JLabel(time[(minesLeft%100) % 10]);

        panel1.add(panel1Left, BorderLayout.LINE_START);
        face = jbutton("",10, new Color(185,185,185,255), new Dimension(23, 23));
        face.setBorderPainted(false);
        face.setIcon(new ImageIcon(getClass().getResource("image/smile.png")));
        face.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board newboard = new Board(b.GetLength(), b.GetWidth(), b.GetMines());
                newboard.PlaceMines();
                newboard.GenerateBoard();
                BoardDisplay bd = new BoardDisplay(newboard, username);
                Dimension d = getDimension();
                bd.setframe(d.width, d.height);
                bd.setLocationRelativeTo(null);
                close();
            }
        });
        panel1.add(face, BorderLayout.CENTER);
        panel1.add(panel1Right, BorderLayout.LINE_END);

        gamepanel.add(panel1);
        gamepanel.add(panel2);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(panel0);

        panel.add(gamepanel);
        setContentPane(panel);
        this.setResizable(false);

        for(int i = 0; i < b.GetLength(); i++) {
            for(int j = 0; j < b.GetWidth(); j++) {
                buttons[i][j] = new JButton("", covered);
                buttons[i][j].setPreferredSize(new Dimension(20, 20));
                int tx = i;
                int ty = j;
                buttons[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //első kattintáskor elindul az időzítő
                        if(firstClick) {
                            timer.start();
                            firstClick = false;
                        }
                        switch(e.getButton()) {

                            case MouseEvent.BUTTON1:

                                if(!buttons[tx][ty].getIcon().equals(flag)) {
                                    int value = b.GetValue(tx, ty);
                                    if(value == 9) {
                                        lose();
                                    } else {
                                        uncover(value, tx, ty);
                                        //fel van-e fedve az összes nem bomba mező
                                        if(uncovered == b.GetLength() * b.GetWidth() - b.GetMines()) {
                                            try {
                                                win();
                                            } catch (IOException exception) {
                                                System.out.println("hiba");
                                            }
                                        }
                                    }
                                }
                                break;
                            case MouseEvent.BUTTON3:
                                if(buttons[tx][ty].getIcon().equals(covered) && minesLeft > 0) {
                                    buttons[tx][ty].setIcon(flag);
                                    minesLeft--;

                                } else if(buttons[tx][ty].getIcon().equals(flag)) {
                                    buttons[tx][ty].setIcon(covered);
                                    minesLeft++;
                                }

                                mineLabels[0].setIcon(time[minesLeft/100]);
                                mineLabels[1].setIcon(time[(minesLeft%100) / 10]);
                                mineLabels[2].setIcon(time[(minesLeft%100) % 10]);

                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                panel2.add(buttons[i][j]);
            }
        }

        for(int i = 0; i < 3; i++) {
            panel1Right.add(mineLabels[i]);
            panel1Left.add(timer.getTimeLabel(i));
        }


    }

    /**
     * felfedi az összes mezőt
     */
    public void uncoverAll() {
        for (int i = 0; i < b.GetLength(); i++) {
            for (int j = 0; j < b.GetWidth(); j++) {
                int value = b.GetValue(i, j);
                if(buttons[i][j].getIcon().equals(covered)) {
                    uncover(value, i, j);
                }
            }
        }
    }

    /**
     *  felfedi a táblán a megadott indexű mezőt az érték alapján
     * @param value - a mező értéke
     * @param i - mező x koordinátája
     * @param j - mező y koordinátája
     */
    public void uncover(int value, int i, int j) {
        uncovered++;
        switch (value) {
            case 0:
                buttons[i][j].setIcon(numbers[0]);

                for(int x = i-1; x <= i+1; x++) {
                    for(int y = j-1; y <= j+1; y++) {
                        if(x>=0 && y>=0&& y< b.GetWidth()&& x< b.GetLength()) {
                            if(buttons[x][y].getIcon().equals(covered)){
                                uncover(b.GetValue(x, y),  x, y);
                            }
                        }
                    }
                }
                break;
            case 1:
                buttons[i][j].setIcon(numbers[1]);
                break;
            case 2:
                buttons[i][j].setIcon(numbers[2]);
                break;
            case 3:
                buttons[i][j].setIcon(numbers[3]);
                break;
            case 4:
                buttons[i][j].setIcon(numbers[4]);
                break;
            case 5:
                buttons[i][j].setIcon(numbers[5]);
                break;
            case 6:
                buttons[i][j].setIcon(numbers[6]);
                break;
            case 7:
                buttons[i][j].setIcon(numbers[7]);
                break;
            case 8:
                buttons[i][j].setIcon(numbers[8]);
                break;
            case 9:
                buttons[i][j].setIcon(numbers[9]);
                break;

        }
    }

    /**
     * ha a játékos nyert
     * leállítja az időzítőt, beállítja a nyerős ikont, és felfedi az összes mezőt
     * a nehézségi szint szerinti fáljba írja a felhasználó nevét, és az időt amivel végzett
     * @throws IOException
     */
    public void win() throws IOException {
        String time = String.valueOf(timer.GetTime());
        timer.stop();
        face.setIcon(new ImageIcon(getClass().getResource("image/win.png")));
        numbers[9] = new ImageIcon(getClass().getResource("image/unclicked-bomb.png"));
        uncoverAll();

        if(!username.isEmpty()) {
            FileWriter writer = new FileWriter(b.getDifficulty()+".txt", true);
            writer.write(username + " " + time + "\n");
            writer.close();
        }
    }

    /**
     * ha a játékos veszít
     * leállítja az időzítőt, beállítja a vesztős ikont, és felfedi az összes mezőt
     */
    public void lose() {
        timer.stop();
        face.setIcon(new ImageIcon(getClass().getResource("image/lose.png")));
        uncoverAll();
    }

    /**
     * beállítja a frame-nek a nagyságát
     * @param x - frame szélessége
     * @param y - frame magassága
     */
    public void setframe(int x, int y) {
        this.setSize(new Dimension(x,y));
    }

    /**
     * bezárja a frame-et
     */
    public void close() {
        this.dispose();
    }

    /**
     * visszaadja a frame nagyságát
     * @return frame nagysága
     */
    public Dimension getDimension() {
        return this.getSize();
    }

    /**
     * visszaad egy formázott gombot
     * @param name - gombon lévő szöveg
     * @param size - szöveg nagysága
     * @param color - gomb színe
     * @param dimension - gomb nagysága
     * @return gomb
     */
    public JButton jbutton(String name, int size, Color color, Dimension dimension) {
        JButton button = new JButton(name);
        button.setFont(new Font(Font.MONOSPACED, Font.PLAIN, size));
        button.setBackground(color);
        button.setPreferredSize(dimension);
        return button;
    }

    public JButton getFace() {
        return face;
    }
    public int getUncovered() {
        return uncovered;
    }

}
