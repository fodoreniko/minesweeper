package minesweeper;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    private int seconds;
    private Timer timer = new Timer();
    private JLabel[] timeLabels = new JLabel[3];
    private ImageIcon[] time = new ImageIcon[10];
    public MyTimer() {
        for(int i = 0; i < 10; i++) {
            time[i] = new ImageIcon(getClass().getResource("image/time" + i + ".png"));
        }
        for(int i = 0; i < 3; i++) {
            timeLabels[i] = new JLabel(time[0]);}
    }
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            seconds++;
            timeLabels[0].setIcon(time[seconds/100]);
            timeLabels[1].setIcon(time[(seconds%100) / 10]);
            timeLabels[2].setIcon(time[(seconds%100) % 10]);
        }
    };

    /**
     * visszaadja az eltelt másodpercet
     * @return másodpercek
     */
    public int GetTime() {
        return seconds;
    }

    /**
     * elindítja az időzítőt, másodpercenként növeli a secondst, és eszerint állítja be az idő ikonokat
     */
    public void start() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    /**
     * megállítja az időzítőt
     */
    public void stop() {
        timer.cancel();
    }

    /**
     * visszaadja a paraméterként megadott helyiártákű szám ikonját
     * @param i - helyiérték
     * @return
     */
    public JLabel getTimeLabel(int i) {
        return timeLabels[i];
    }

}
