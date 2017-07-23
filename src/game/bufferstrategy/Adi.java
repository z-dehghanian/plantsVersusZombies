/*
 * this class is written  to perform the simplest kind of zombies

 @author zahra dehghanian & monire safari
 */
package game.bufferstrategy;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class Adi extends Zombies {

    /**
     *
     * @param x position of zombie
     * @param y position zombie
     */
    public Adi(int x, int y) {
        super(x, y);
        super.zombieImage = new ImageIcon("adizombie.png");
        super.life = 6;
    }

    @Override
    public void run(ArrayList<Giyah> g) {
        if (g.size() != 0) {
            if (x >= lastGiyah(g) + 65 || x <= lastGiyah(g)) {
                x = x - speed;
            }

        } else {
            x = x - speed;
        }

        if (speed == 1) {
            zombieImage = new ImageIcon("adizombie2.png");
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
}
