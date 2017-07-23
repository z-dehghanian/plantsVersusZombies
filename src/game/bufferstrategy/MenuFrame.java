/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bufferstrategy;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author USER!
 */
public class MenuFrame extends JFrame implements MouseListener, MouseMotionListener {

    public static final int GAME_HEIGHT = 600;                  // 720p game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio
    boolean startFlag = false;

    /**
     * constructor for menu 
     */
    public MenuFrame() {
        super(" JPVZ - MZ ");
        addMouseListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLayout(null);

        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        initUI();
    }

    private void initUI() {

        ImageIcon ii = new ImageIcon("menu.png");
        setContentPane(new MenuPanel(ii, 0));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if ((x <= 957 && x >= 555) && (y <= 220 && y >= 130)) {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GameFrame frame = new GameFrame("JPVZ - MZ");
                    frame.setLocationRelativeTo(null); // put frame at center of screen
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.initBufferStrategy();
                    // Create and execute the game-loop
                    GameLoop game = new GameLoop(frame);
                    game.init();
                    ThreadPool.execute(game);
				// and the game starts ...

                }
            });

        } else if ((x <= 865 && x >= 755) && (y <= 551 && y >= 500)) {
            System.out.println("1");
            System.exit(0);
        } else if ((x <= 1038 && x >= 951) && (y <= 567 && y >= 511)) {
            System.out.println("1");
            newFrame();
        }
    }

    /**
     * show new frame for help button
     */
    public void newFrame() {
        JFrame m = new JFrame("راهنمایی");
        ImageIcon ii = new ImageIcon("help.png");
        m.setContentPane(new MenuPanel(ii, 1));
        m.setVisible(true);
        m.setLocation(350, 70);
        m.setSize(630, 600);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
