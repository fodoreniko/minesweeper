package minesweeper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Style {

    /**
     * egy szürke, keretezett gombot ad vissza, amelyen a megadott szöveg van
     * @param text - a gombon szereplő szöveg
     * @param x - gomb szélessége
     * @param y - gomb magassága
     * @return gomb
     */
    public JButton button(String text, int x, int y) {
        JButton b = new JButton(text);
        b.setPreferredSize(new Dimension(x, y));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setBackground(new Color(172,172,172));
        b.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        return b;
    }

    /** Egy formázott (Monospaced betűtípusú, 16-os méretű) szöveget ad vissza
     *
     * @param text - a szöveg tartalma
     * @return szöveg
     */
    public JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        return label;
    }

    /** Egy formázott (Monospaced betűtípusú, 18-as méretű, és félkövér) szöveget ad vissza
     *
     * @param text - a szöveg tartalma
     * @return - a szöveg
     */
    public JLabel boldlabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    /** Létrehoz egy panelt, aminek beállítja a hátterét szürkére, és keretet ad hozzá
     *
     * @return panel
     */
    public JPanel myPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        panel.setBackground(new Color(172,172,172));
        return panel;
    }

    /** üres részt ad hozzá a paraméterként megadott panelhez
     *
     * @param y - hova kerüljön az üres rész
     * @param gbc - a Gridbaglayout-nak a constraintje
     * @param panel - a panel, amihez hozzá szeretnénk adi az üres részt
     */
    public void addSpace( int y, GridBagConstraints gbc, JPanel panel) {
        gbc.gridy = y; //gbc.gridx = x;
        panel.add(Box.createRigidArea(new Dimension(20, 20)), gbc);
    }

    /** beállítja a paraméterként kapott frame-nek a nagyságát a nehézségi szinttől függően
     *
     * @param difficulty - nehézségi szint
     * @param frame - beállítandó frame
     */
    public void setFrameSize(String difficulty, JFrame frame) {

        switch(difficulty) {
            case "beginner":
                frame.setSize(new Dimension(222, 305)); //305
                break;
            case "intermediate":
                frame.setSize(new Dimension(362, 445));
                break;
            case "expert":
                frame.setSize(new Dimension(642, 445));
                break;
            default:
                break;
        }

    }

    /**
     *  A paraméternek megadott panelnek beállít egy szürke vastag keretet
     * @param panel - panel
     */
    public void border(JPanel panel) {
        Border b1 = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLineBorder(new Color(185,185,185,255), 8));
        Border b2 = BorderFactory.createCompoundBorder(b1, BorderFactory.createLoweredBevelBorder());
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(117,117,117,255), 1), b2));

    }

    /**
     * A paraméterként megadott frame-nek megcsinálja az alapbeállításait
     * @param frame - beállítandó frame
     * @param panel - frame-nek a panelja
     * @param x - frame szélessége
     * @param y - frame magassága
     */
    public void setFrame(JFrame frame, JPanel panel, int x, int y) {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setTitle("Minesweeper");
        frame.setResizable(false);
        frame.setSize(new Dimension(x, y));
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
    }



}
