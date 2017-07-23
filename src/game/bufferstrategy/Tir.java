/*
 * this class is written  to perform common properities of all bullet 
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
public abstract class Tir implements Serializable {

    public int life;
    public int x, y;
    public ImageIcon tirImage;

    /**
     *
     * @param x position of bullet
     * @param y position of bullet
     */
    public Tir(int x, int y) {
        this.x = x;
        this.y = y;

    }

    /**
     *
     * @param z list of all zombie in special row
     * @return position of which zombie is same with bullet
     */
    abstract public int run(ArrayList<Zombies> z);

}
