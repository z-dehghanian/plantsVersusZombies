
/*
 * this abstract class is written down to describe the main properties of zombies

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
public abstract class Zombies implements Serializable {

    public int life;
    public int x, y, speed = 2;
    ImageIcon zombieImage;

    public Zombies(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param z all plants in special row to move zombie
     */
    abstract public void run(ArrayList<Giyah> z);

}
