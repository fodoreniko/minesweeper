package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class Menu extends JFrame {
    private Style s = new Style();

    public Menu() {
        JPanel panel = s.myPanel();
        panel.setLayout(new GridBagLayout());
        s.setFrame(this, panel, 280, 420);

        GridBagConstraints gbc = new GridBagConstraints();

        JButton toGame = s.button("new game", 160, 40);
        JButton exit = s.button("exit", 160, 40);
        JButton boardmaker = s.button("board maker", 160, 40);
        JButton loadgame = s.button("load game", 160, 40);
        JButton viewScoreboard = s.button("view scoreboard", 160, 40);

        gbc.gridy = 1; panel.add(toGame, gbc);
        s.addSpace(2, gbc, panel);
        gbc.gridy = 3; panel.add(boardmaker, gbc);
        s.addSpace(4, gbc, panel);
        gbc.gridy = 5; panel.add(loadgame, gbc);
        s.addSpace(6, gbc, panel);
        gbc.gridy = 7; panel.add(viewScoreboard, gbc);
        s.addSpace(8, gbc, panel);
        gbc.gridy = 9; panel.add(exit, gbc);

        toGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings = new Settings(true);
                close();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        boardmaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings = new Settings(false);
                close();
            }
        });

        loadgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame();
                f.setUndecorated(true);
                f.setVisible(true);

                JPanel panel = s.myPanel();
                FlowLayout fl = new FlowLayout(5, 15, 10);
                f.setSize(new Dimension(400, 120));
                panel.setLayout(fl);
                f.setLocationRelativeTo(null);
                JLabel l = s.label("enter the name of the board: ");
                JLabel error = s.label("");
                error.setForeground(Color.RED);

                JButton okbutton = s.button("ok", 78, 28);
                JButton backbutton = s.button("go back", 78, 28);
                TextField tf = new TextField(20);
                panel.add(l);
                panel.add(tf);
                panel.add(okbutton);
                panel.add(backbutton);

                panel.add(error);
                f.add(panel);

                backbutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.dispose();
                    }
                });

                okbutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = tf.getText();
                        File file = new File(name + ".txt");
                        if(file.exists()) {
                            f.dispose();

                            Board board = read(name + ".txt");
                            String dif = board.getDifficulty();

                            JFrame frame = new BoardDisplay(board, "");
                            frame.setVisible(true);
                            s.setFrameSize(dif, frame);
                            frame.setLocationRelativeTo(null);
                            close();
                        } else {
                            error.setText("error! there's no board with that name!");
                        }
                    }
                });
            }
        });

        viewScoreboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scoreboard s = new Scoreboard();
                close();
            }
        });
    }

    /** fájlból beolvassa a táblát
     *
     * @param name a fájl neve
     * @return visszaadja a táblát
     */
    public Board read(String name) {
        try {
            FileInputStream fis = new FileInputStream(name);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Board nb = (Board) ois.readObject();
            ois.close();
            return nb;
        } catch(Exception exception) {
            System.err.println("error");
            return null;
        }
    }

    /** bezárja a frame-et
     *
     */
    public void close() {
        this.dispose();
    }

}
