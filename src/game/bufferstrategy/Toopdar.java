/*
 * this class is written  to perform some special kind of zombies which throw ball to plansts

 @author zahra dehghanian & monire safari
 */
package game.bufferstrategy;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class Toopdar extends Zombies {

    private int toop = 0;
    private int timeToop = 150;

    public Toopdar(int x, int y) {
        super(x, y);
        super.zombieImage = new ImageIcon("adventurer.png");
        super.life = 12;
    }

    @Override
    public void run(ArrayList<Giyah> g) {
        if (x >= lastGiyah(g) + 65 || x <= lastGiyah(g) + 30) {
            x = x - speed;
        } else {
            x = x - speed;
        }

    }

    private int lastGiyah(ArrayList<Giyah> g) {
        int max = 0;
        for (int i = 0; i < g.size(); i++) {
            if (max < g.get(i).x) {
                max = g.get(i).x;
            }
        }
        return max;
    }

    /**
     *
     * @param g list of all plants in special row
     * @return 1 or 2 or 3 depend on condition
     */
    public int tirZan(ArrayList<Giyah> g) {
        if (speed == 1) {
            zombieImage = new ImageIcon("adventurer2.png");
        }

        if ((g.size() != 0 && g.get(g.size() - 1).x < x) && toop <= 20 && x <= 1200) {

            if (timeToop < 0) {
                if (speed == 1) {
                    timeToop = 250;
                } else {
                    timeToop = 150;
                }
                toop++;
                return 1;
            }
            timeToop--;
            return 2;

        } else {
            return 3;
        }

    }
}
