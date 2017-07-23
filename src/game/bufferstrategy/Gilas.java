/*
 * this class is written down to describe cheries which blows up all zombies in an area in this game 

 @author zahra dehghanian & monire safari
 */
package game.bufferstrategy;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class Gilas extends Giyah {

    int shomarande;

    /**
     *
     * @param x position of plant
     * @param y position of plant
     * @param column of plant
     */
    public Gilas(int x, int y, int column) {
        super(x, y, column);
        super.giyahImage = new ImageIcon("gilas2.png");
        super.life = 100;
        shomarande = 0;
    }

    @Override
    public boolean baresiKhorde(ArrayList<Zombies> z) {

        return true;
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
        shomarande++;
        if (shomarande > 20 && shomarande < 30) {
            super.giyahImage = new ImageIcon("enfejar.png");
            return false;
        } else if (shomarande == 30) {
            return true;
        } else {
            return false;
        }

    }

}
