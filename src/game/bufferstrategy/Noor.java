/*
 * this class is written  to perform sun 
 * wich stored in our bank and we should check it all the time 
 @author zahra dehghanian & monire safari
 */
package game.bufferstrategy;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class Noor implements Serializable {

    public int life;
    public int x, y;
    ImageIcon Image;
    boolean run;

    /**
     *
     * @param x position of sun
     * @param y position of sun
     */
    public Noor(int x, int y) {
        this.x = x;
        this.y = y;
        life = 700;
        Image = new ImageIcon("sun.png");
        run = true;
    }

    /**
     * to move the sun
     */
    public void run() {
        y++;

    }
}
