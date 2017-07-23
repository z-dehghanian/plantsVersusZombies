/*
 * this class is written down to describe wall-nut which blocks
 * off zombies and protects your other plants in this game 
 *
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
public class Gerdu extends Giyah implements Serializable {

    int nokhod;

    /**
     *
     * @param x position of plant
     * @param y position of plant
     * @param column column of plant
     */
    public Gerdu(int x, int y, int column) {
        super(x, y, column);
        super.giyahImage = new ImageIcon("gerdu1.png");
        super.life = 100;
        nokhod = 0;
    }

    @Override
    public boolean baresiKhorde(ArrayList<Zombies> z) {
        if (this.life <= 50) {
            super.giyahImage = new ImageIcon("gerdu2.png");
        }
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
        return false;

    }
}
