/*
 * this abstract class is written down to describe the main properties of plants

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
public abstract class Giyah implements Serializable {

    public int life;
    public int x, y;
    public int column = 10;
    ImageIcon giyahImage;

    public Giyah(int x, int y, int column) {
        this.column = column;
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param z list of all zombies in game in special row
     * @return true if life = 0
     */
    abstract public boolean baresiKhorde(ArrayList<Zombies> z);

    /**
     *
     * @param z list of all plants in game in special row
     * @return
     */
    abstract public boolean run(ArrayList<Zombies> z);

    /**
     * to find first zombie in row
     *
     * @param z list of all zombies in game in special row
     * @return
     */
    abstract public int firstZombie(ArrayList<Zombies> z);
}
