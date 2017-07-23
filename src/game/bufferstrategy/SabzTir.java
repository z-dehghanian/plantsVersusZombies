/*
 * this class is written  to perform peas 
 * wich damage zombies while shooting
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
public class SabzTir extends Tir implements Serializable {

    public SabzTir(int x, int y) {
        super(x, y);
        super.tirImage = new ImageIcon("sabzTir.png");
    }

    @Override
    public int run(ArrayList<Zombies> z) {
        x = x + 3;

        for (int i = 0; i < z.size(); i++) {

            if (z.get(i).x >= x - 3 && z.get(i).x <= x + 50) {

                return i;

            }

        }
        return -1;
    }
}
