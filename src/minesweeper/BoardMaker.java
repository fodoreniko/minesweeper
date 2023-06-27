package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class BoardMaker extends JFrame {
    private Board b;
    private JButton[][] buttons;
    private int mines = 0;
    private String name;

    public BoardMaker(Board board, String n) {
        this.setVisible(true);
        b = board;
        name = n;
        buttons = new JButton[b.GetLength()][b.GetWidth()];
        Style s = new Style();
        JButton save = s.button("save", 80, 30);
        JButton menu = s.button("go back", 80, 30);
        ImageIcon bomb = new ImageIcon(getClass().getResource("image/9.png"));
        ImageIcon blank = new ImageIcon(getClass().getResource("image/0.png"));

        JPanel panel = new JPanel();
        s.border(panel);
        JPanel upperpanel = new JPanel();
        JPanel lowerpanel = new JPanel();

        upperpanel.setLayout(new GridLayout(b.GetLength(), b.GetWidth()));

        lowerpanel.setBackground(new Color(172,172,172));

        JLabel successful  = new JLabel("");
        successful.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        lowerpanel.add(successful);
        lowerpanel.add(menu);
        lowerpanel.add(save);

        panel.setBackground(new Color(172,172,172));
        panel.setLayout(new BorderLayout());

        panel.add(upperpanel, BorderLayout.PAGE_START);
        panel.add(lowerpanel, BorderLayout.PAGE_END);

        this.setContentPane(panel);
        this.setResizable(false);

        for(int i = 0; i < b.GetLength(); i++) {
            for(int j = 0; j < b.GetWidth(); j++) {
                buttons[i][j] = new JButton("", blank);
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
                        if(buttons[tx][ty].getIcon().equals(blank) && mines >= 0) {
                            buttons[tx][ty].setIcon(bomb);
                            b.setValue(tx, ty, 9);
                            mines++;
                        } else if(buttons[tx][ty].getIcon().equals(bomb)) {
                            buttons[tx][ty].setIcon(blank);
                            b.setValue(tx, ty, 0);
                            mines--;
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                upperpanel.add(buttons[i][j]);
            }
        }

        save.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.GenerateBoard();
                b.SetMines(mines);

                if(!name.isEmpty()) {
                    write(name + ".txt");
                    successful.setForeground(new Color(19, 131, 19));
                    successful.setText("success!");
                } else {
                    successful.setForeground(Color.RED);
                    successful.setText("error!");
                }

            }
        });

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                close();
            }
        });
    }

    /**
     * a paraméterként megadott nevű fáljba írja a táblát
     * @param name - a fájl neve
     */
    public void write(String name) {
        try {
            FileOutputStream fos = new FileOutputStream(name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(b);
            oos.close();
        } catch(IOException exception) {
            System.err.println("error");
        }
    }

    /** bezárja a frame-et
     *
     */
    public void close() {
        this.dispose();
    }
}
