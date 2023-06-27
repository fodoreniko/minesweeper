package minesweeper;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Scoreboard extends JFrame {
    private Style s = new Style();

    public Scoreboard() {

        JPanel panel = s.myPanel();
        s.setFrame(this, panel, 750, 220);

        JPanel upper = s.myPanel();
        JPanel lower = s.myPanel();
        JPanel l = s.myPanel();
        JButton back = s.button("go back", 100, 30);
        l.setLayout(new BorderLayout());
        l.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.PAGE_START);
        l.add(back, BorderLayout.LINE_END);
        l.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.PAGE_END);
        GridLayout layout = new GridLayout(3, 6, 20, 1);
        lower.setLayout(layout);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setSize(300, 450);
                close();
            }
        });

        upper.setLayout(new GridLayout(1, 3));
        upper.add(s.boldlabel("beginner"));
        upper.add(s.boldlabel("intermediate"));
        upper.add(s.boldlabel("expert"));

        java.util.List<ArrayList<String>> beginnerlist = readtolist("beginner.txt");
        java.util.List<ArrayList<String>> expertlist = readtolist("expert.txt");
        List<ArrayList<String>> intermediatelist = readtolist("intermediate.txt");

        for(int i = 0; i < 3; i++) {

            lower.add(s.label(i+1 + ".  " + beginnerlist.get(i).get(1)));
            lower.add(s.label(beginnerlist.get(i).get(0)));

            lower.add(s.label(i+1 + ".  "  + intermediatelist.get(i).get(1)));
            lower.add(s.label(intermediatelist.get(i).get(0)));

            lower.add(s.label(i+1 + ".  "  +expertlist.get(i).get(1)));
            lower.add(s.label(expertlist.get(i).get(0)));
        }
        this.add(upper);
        this.add(lower);
        this.add(l);
    }

    /** bezárja a frame-et
     *
     */
    public void close() {
        this.dispose();
    }

    /** a megfelelő fájlból beolvassa a játékosok felhasználónevüket és az időt egy listába,
     *   majd növekvő sorrendbe rendezi őket az idő alapján
     * @param name - a fájl neve
     * @return - a rendezett lista
     */
    public List<ArrayList<String>> readtolist(String name) {
        List<ArrayList<String>> list = new ArrayList<>();
        try {

            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                ArrayList<String> temp = new ArrayList<String>();
                String data = myReader.nextLine();
                String[] d = data.split(" ");
                if(d.length > 1) {
                    temp.add(d[0]);
                    temp.add(d[1]);
                    list.add(new ArrayList<String>(Arrays.asList(d[1], d[0])));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Collections.sort(list, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return  Integer.valueOf(o1.get(0)).compareTo(Integer.valueOf(o2.get(0)));
            }
        });
        return list;
    }
}