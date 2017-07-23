/*
 * this class is written  to perform snow peas
 * wich shoot snow peas which damage and slow enemies

 @author zahra dehghanian & monire safari
 */package game.bufferstrategy;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class Yakhi extends Giyah implements Serializable {

    int nokhod;

    /**
     *
     * @param x x position of plant
     * @param y position of plant
     * @param column the column of plant
     */
    public Yakhi(int x, int y, int column) {
        super(x, y, column);
        super.giyahImage = new ImageIcon("yakhi2.png");
        super.life = 100;
        nokhod = 0;
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
        if (z.size() > 0) {
            if (nokhod < 0) {
                nokhod = 30;
                return true;
            }
        }
        nokhod--;
        return false;

    }

}
