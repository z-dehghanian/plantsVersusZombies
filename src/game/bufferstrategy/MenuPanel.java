/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bufferstrategy;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class MenuPanel extends JPanel {

    private Image jpvz;
    public static final int GAME_HEIGHT = 600;                  // 720p game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio
    private int i;

    /**
     *
     * @param n the image of the background
     * @param i change the background
     */
    public MenuPanel(ImageIcon n, int i) {
        jpvz = n.getImage();
        this.i = i;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (i == 0) {
            g.drawImage(jpvz, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        }
        if (i == 1) {
            g.drawImage(jpvz, 0, 0, 630, 650, null);
        }
    }

}
