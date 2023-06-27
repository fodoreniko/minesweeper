package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    private final JTextField textField = new JTextField(10);
    private Style s = new Style();
    private boolean game;

    public Settings(boolean n) {
        game = n;

        JPanel panel = s.myPanel();
        panel.setLayout(new GridBagLayout());
        s.setFrame(this, panel, 500, 250);

        GridBagConstraints gbc = new GridBagConstraints();
        JButton ok = s.button("ok", 100, 30);
        JButton back = s.button("go back", 100, 30);
        JLabel ulabel;

        if (game) {
            ulabel = s.label("username: ");
        } else {
            ulabel = s.label("name your board: ");
        }

        JLabel levelText = s.label("select a difficulty level: ");
        String[] levels = {"beginner", "intermediate", "expert"};
        JComboBox<String> comboBox = new JComboBox<>(levels);
        comboBox.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        comboBox.setBackground(new Color(172, 172, 172));

        s.addSpace(0, gbc, panel);
        gbc.gridy = 1; panel.add(ulabel, gbc);
        gbc.gridx = 1; panel.add(textField, gbc);
        s.addSpace(2, gbc, panel);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(levelText, gbc);
        gbc.gridx = 1; panel.add(comboBox, gbc);
        s.addSpace(4, gbc, panel);
        gbc.gridy = 5; panel.add(ok, gbc);
        gbc.gridx = 0; panel.add(back, gbc);
        s.addSpace(6, gbc, panel);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                System.out.println(username);
                String difficulty = (String) comboBox.getSelectedItem();
                Board board = diff(difficulty);
                board.setDifficulty(difficulty);

                //új játék
                if (game) {
                    board.PlaceMines();
                    board.GenerateBoard();

                    BoardDisplay frame = new BoardDisplay(board, username);
                    s.setFrameSize(difficulty, frame);
                    frame.setLocationRelativeTo(null);
                    close();

                    //pályakészítés
                } else {
                    JFrame frame = new BoardMaker(board, username);
                    s.setFrameSize(difficulty, frame);
                    frame.setLocationRelativeTo(null);
                    close();
                }
            }
        });

        //vissza a menübe
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setSize(300, 450);
                close();
            }
        });
    }

    /**
     * a nehézségi szint szerint beállítja a táblának a méretét és a bombák számát
     * @param difficulty - nehézségi szint
     * @return a tábla
     */
    public Board diff(String difficulty) {
        int x, y, m;

        switch(difficulty) {
            case "intermediate":
                x = 16; y = 16;  m = 40;
                break;
            case "expert":
                x = 16; y = 30; m = 99;
                break;
            default:
                x = 9; y = 9; m = 10;
                break;
        }

        Board board = new Board(x, y, m);
        return board;
    }

    public void close() {
        this.dispose();
    }
}