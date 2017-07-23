/*
 * this class is written  to perform some special kind of zombies which jump from fist plant 

 @author zahra dehghanian & monire safari
 */
package game.bufferstrategy;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class Varzesh extends Zombies implements Serializable {

    public Varzesh(int x, int y) {
        super(x, y);
        super.zombieImage = new ImageIcon("varzesh2.png");
        super.life = 12;
    }

    @Override
    public void run(ArrayList<Giyah> g) {
        if (g.size() != 0) {
            // if(flag==0){
            if (this.x - 30 > lastGiyah(g)) {
                x = x - speed;
            } else if (this.x < lastGiyah(g) + 30 && this.x > lastGiyah(g)) {
                x = x - speed;
                y--;;
            } else if (this.x + 30 > lastGiyah(g) && this.x < lastGiyah(g)) {
                x = x - speed;
                y++;;
            }
            //}

            if (this.x - 30 != secLastGiyah(g)) {
                x = x - speed;
            }
        } else {
            x = x - speed;
        }
        if (speed == 1) {
            zombieImage = new ImageIcon("varzesh3.png");
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

    private int secLastGiyah(ArrayList<Giyah> g) {
        int max = 0, max2 = 0;
        for (int i = 0; i < g.size(); i++) {
            if (max < g.get(i).x) {
                max = g.get(i).x;
            } else if (max2 < max && max2 < g.get(i).x) {
                max2 = g.get(i).x;
            }
        }

        return max2;
    }
}
