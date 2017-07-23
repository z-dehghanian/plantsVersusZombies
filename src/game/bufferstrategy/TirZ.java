package game.bufferstrategy;
/*
 * this class is written  to perform bullet of zomies 
 * 
 @author zahra dehghanian & monire safari
 */

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class TirZ {

    public int life;
    public int x, y, counter;
    public ImageIcon tirImage;

    public TirZ(int x, int y) {
        this.x = x;
        this.y = y;

        tirImage = new ImageIcon("toop.png");
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
     * @param z list of all zombie in special row
     * @return position of which zombie is same with bullet
     */
    public int run(ArrayList<Giyah> z) {
        counter++;
        x = x - 3;
        if (x - (lastGiyah(z) + 80) >= (x + (counter * 3) - (lastGiyah(z) + 80)) / 2) {
            y--;
        } else {
            y++;
        }

        for (int i = 0; i < z.size(); i++) {
            if (z.get(i).x >= x - 25 && z.get(i).x <= x + 20) {

                return i;

            }

        }
        return -1;
    }
}
