/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bufferstrategy;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class GharchTir extends Tir {

    /**
     *
     * @param x position of bullet
     * @param y position of bullet
     */
    public GharchTir(int x, int y) {
        super(x, y);
        super.tirImage = new ImageIcon("banafsh.png");
    }

    @Override
    public int run(ArrayList<Zombies> z) {
        x = x + 3;

        for (int i = 0; i < z.size(); i++) {

            if (z.get(i).x >= x - 10 && z.get(i).x <= x + 20) {

                return i;

            }

        }
        return -1;
    }
}
