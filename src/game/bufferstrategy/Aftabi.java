/*
 * this class is written down to describe sun flowers which gives you additional sun in this game 

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
public class Aftabi extends Giyah implements Serializable {

    int aftab = 0;

    /**
     *
     * @param x position of plant
     * @param y position of plant
     * @param column the column of plant
     */
    public Aftabi(int x, int y, int column) {
        super(x, y, column);
        super.giyahImage = new ImageIcon("aftabi2.png");
        super.life = 100;
    }

    @Override
    public boolean baresiKhorde(ArrayList<Zombies> z) {
        if (firstZombie(z) <= x + 65 && firstZombie(z) >= x) {
            this.life--;
        }
        if (this.life > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int firstZombie(ArrayList<Zombies> z) {
        int min = 1240;
        for (int i = 0; i < z.size(); i++) {
            if (min > z.get(i).x) {
                min = z.get(i).x;
            }
        }
        return min;
    }

    @Override
    public boolean run(ArrayList<Zombies> z) {
        aftab++;
        if (aftab > 500) {
            aftab = 0;
            return true;
        }
        return false;
    }
}
