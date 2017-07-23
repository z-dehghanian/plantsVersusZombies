/**
 * * In The Name of Allah **
 */
package game.bufferstrategy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The window on which the rendering is performed. This structure uses the
 * modern BufferStrategy approach for triple-buffering
 *
 *
 * @author zahra dehghanian & monire safari
 */
public class GameFrame extends JFrame implements Serializable {

    public static final int GAME_HEIGHT = 720;                  // 720p game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio

    private BufferStrategy bufferStrategy;
    int next = 0;
    private final ImageIcon jpvz;
    private final ImageIcon jpvzShab;
    private final ImageIcon sunNum;
    private final ImageIcon plantmenu;
    private final ImageIcon sabz;
    private final ImageIcon yakhi;
    private final ImageIcon gerdu;
    private final ImageIcon gilas;
    private final ImageIcon aftabi;
    private final ImageIcon sabz3;
    private final ImageIcon yakhi3;
    private final ImageIcon gerdu3;
    private final ImageIcon gilas3;
    private final ImageIcon aftabi3;
    private final ImageIcon bil;
    private final ImageIcon gharch;
    private final ImageIcon gharch3;
    private final ImageIcon jabili;

    private int gharchTime = 0;

    /**
     *
     * @param title title of frame
     */
    public GameFrame(String title) {
        super(title);
        this.aftabi = new ImageIcon("aftabi2.png");
        this.gilas = new ImageIcon("gilas2.png");
        this.gerdu = new ImageIcon("gerdu1.png");
        this.yakhi = new ImageIcon("yakhi2.png");
        this.sabz = new ImageIcon("giyah1 (2).png");
        this.plantmenu = new ImageIcon("plantmenu.png");
        this.jpvz = new ImageIcon("jpvz.jpg");
        this.jpvzShab = new ImageIcon("jpvzshab.jpg");
        this.sunNum = new ImageIcon("sunnum.png");
        this.aftabi3 = new ImageIcon("aftabi3.png");
        this.gilas3 = new ImageIcon("gilas3.png");
        this.gerdu3 = new ImageIcon("gerdu3.png");
        this.yakhi3 = new ImageIcon("yakhi3.png");
        this.sabz3 = new ImageIcon("giyah3.png");
        this.bil = new ImageIcon("bil.png");
        this.gharch3 = new ImageIcon("gharch3.png");
        this.gharch = new ImageIcon("gharch.png");
        this.jabili = new ImageIcon("jabili.png");

        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);

    }

    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true); and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }

    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {

        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state);
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) {
        if (state.level <= 6) {
            if (state.gameover) {
                g2d.setColor(Color.WHITE);
                g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
                int strWidth = g2d.getFontMetrics().stringWidth("باختی");
                g2d.drawString("باختی", (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
                next++;
                if (next == 100) {
                    this.setVisible(false);
                    next = 0;
                }
            } else if (state.nextLevel) {

                // Draw background
                if (state.level <= 5) {
                    g2d.drawImage(jpvz.getImage(), -200, 25, 2000, 695, null);
                } else {
                    g2d.drawImage(jpvzShab.getImage(), -200, 25, 2000, 695, this);
                }
                g2d.drawImage(jabili.getImage(), 550, 25, 60, 80, this);
                g2d.drawImage(plantmenu.getImage(), 20, 25, 480, 80, this);

                ////////////keshidane axe giyah entekhab shode ba harekate mouse
                if (null != state.axSabz) {
                    g2d.drawImage(state.axSabz.giyahImage.getImage(), state.axSabz.x, state.axSabz.y, 40, 40, this);
                } else if (null != state.axAftabi) {
                    g2d.drawImage(state.axAftabi.giyahImage.getImage(), state.axAftabi.x, state.axAftabi.y, 40, 40, this);
                } else if (null != state.axGerdu) {
                    g2d.drawImage(state.axGerdu.giyahImage.getImage(), state.axGerdu.x, state.axGerdu.y, 40, 40, this);
                } else if (null != state.axYakhi) {
                    g2d.drawImage(state.axYakhi.giyahImage.getImage(), state.axYakhi.x, state.axYakhi.y, 40, 40, this);
                } else if (state.bilFlag) {
                    g2d.drawImage(bil.getImage(), state.mouseMoveX, state.mouseMoveY, 40, 40, this);
                } else if (null != state.axGilas) {
                    g2d.drawImage(state.axGilas.giyahImage.getImage(), state.axGilas.x, state.axGilas.y, 40, 40, this);
                } else if (null != state.axGharch) {
                    g2d.drawImage(state.axGharch.giyahImage.getImage(), state.axGharch.x, state.axGharch.y, 40, 40, this);
                }

                /////////////////////// keyboard
                if (state.sabzhast) {
                    g2d.drawImage(sabz.getImage(), state.keyX, state.keyY, 40, 40, this);
                } else if (state.aftabihast) {
                    g2d.drawImage(aftabi.getImage(), state.keyX, state.keyY, 40, 40, this);
                } else if (state.gerduhast) {
                    g2d.drawImage(gerdu.getImage(), state.keyX, state.keyY, 40, 40, this);
                } else if (state.yakhihast) {
                    g2d.drawImage(yakhi.getImage(), state.keyX, state.keyY, 40, 40, this);
                } else if (state.bilFlag) {
                    g2d.drawImage(bil.getImage(), state.keyX, state.keyY, 40, 40, this);
                } else if (state.gilashast) {
                    g2d.drawImage(gilas.getImage(), state.keyX, state.keyY, 40, 40, this);
                } else if (state.gharchhast) {
                    g2d.drawImage(gharch.getImage(), state.keyX, state.keyY, 40, 40, this);
                }
                //////////////////////
                g2d.drawImage(bil.getImage(), 550, 30, 60, 70, this);

                if (state.noor - 50 < 0) {
                    g2d.drawImage(aftabi3.getImage(), 160, 30, 60, 70, this);
                } else {
                    g2d.drawImage(state.aftabi.getImage(), 160, 30, 60, 70, this);
                }
                if (state.noor - 100 < 0) {
                    g2d.drawImage(sabz3.getImage(), 100, 30, 60, 70, this);
                } else {
                    g2d.drawImage(state.giyah1.getImage(), 100, 30, 60, 70, this);
                }
                if (state.level >= 2) {
                    if (state.noor - 50 < 0) {
                        g2d.drawImage(gerdu3.getImage(), 220, 30, 60, 70, this);
                    } else {
                        g2d.drawImage(state.gerdu.getImage(), 220, 30, 60, 70, this);
                    }
                }
                if (state.level >= 3) {
                    if (state.noor - 175 < 0) {
                        g2d.drawImage(yakhi3.getImage(), 280, 30, 60, 70, this);
                    } else {
                        g2d.drawImage(state.yakhi.getImage(), 280, 30, 60, 70, this);
                    }
                }
                if (state.level >= 4) {
                    if (state.noor - 150 < 0) {
                        g2d.drawImage(gilas3.getImage(), 340, 30, 60, 70, this);
                    } else {
                        g2d.drawImage(state.gilas.getImage(), 340, 30, 60, 70, this);
                    }
                }
                if (state.level >= 6) {
                    if (state.gharchTime <= 50) {
                        g2d.drawImage(gharch3.getImage(), 400, 30, 60, 70, this);

                    } else {
                        g2d.drawImage(state.gharch.getImage(), 400, 30, 60, 70, this);

                    }
                }

                /////////////////////////////////////////////////
                for (int j = 0; j < 5; j++) {
                    for (int i = 0; i < state.giyah.get(j).size(); i++) {
                        int x = state.giyah.get(j).get(i).x;
                        int y = state.giyah.get(j).get(i).y;
                        g2d.drawImage(state.giyah.get(j).get(i).giyahImage.getImage(), x, y, 80, 80, null);
                    }
                }
                ////////////////////////////////////// mashinkeshidan

                for (int j = 0; j < state.cars.size(); j++) {

                    int x = state.cars.get(j).x;
                    int y = state.cars.get(j).y;
                    g2d.drawImage(state.cars.get(j).carImage.getImage(), x, y, 70, 70, this);
                }
                /////////////////////////////////////////////

                for (int j = 0; j < 5; j++) {
                    for (int i = 0; i < state.zombies.get(j).size(); i++) {
                        int x = state.zombies.get(j).get(i).x;
                        int y = state.zombies.get(j).get(i).y;

                        g2d.drawImage(state.zombies.get(j).get(i).zombieImage.getImage(), x, y, 100, 120, this);
                    }
                }

                for (int j = 0; j < 5; j++) {
                    for (int i = 0; i < state.tirZ.get(j).size(); i++) {
                        int x = state.tirZ.get(j).get(i).x;
                        int y = state.tirZ.get(j).get(i).y;

                        g2d.drawImage(state.tirZ.get(j).get(i).tirImage.getImage(), x, y, 20, 20, this);
                    }
                }

                for (int j = 0; j < 5; j++) {
                    for (int i = 0; i < state.tir.get(j).size(); i++) {
                        int x = state.tir.get(j).get(i).x;
                        int y = state.tir.get(j).get(i).y;

                        g2d.drawImage(state.tir.get(j).get(i).tirImage.getImage(), x, y, 25, 25, this);
                    }
                }

                for (int i = 0; i < state.noors.size(); i++) {
                    int x = state.noors.get(i).x;
                    int y = state.noors.get(i).y;
                    g2d.drawImage(state.noors.get(i).Image.getImage(), x, y, 100, 100, this);
                }

            ////////////////
                //g2d.drawImage(sunNum.getImage(), 20, 30, 80, 100, this);
                // g2d.setFont(new Font("Afra", Font.PLAIN, 25));
                g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(20.0f));
                g2d.drawString("" + state.noor + "", 40, 100);
                g2d.drawString("مرحله:" + state.level + "", 1200, 710);
                g2d.drawString("بازگشت ", 1200, 50);

                if (state.finish) {
                    this.setVisible(false);

                }
                /////////////
            } else {
                g2d.setColor(Color.RED);
                g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
                int strWidth = g2d.getFontMetrics().stringWidth("ایول بردی! رفتی مرحله ی بعد");
                g2d.drawString("ایول بردی! رفتی مرحله ی بعد", (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
                next++;
                if (next == 100) {
                    state.nextLevel = true;
                    next = 0;
                }
            }
        } else if (state.level >= 7) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
            int strWidth = g2d.getFontMetrics().stringWidth("همه ی مرحله ها تموم شد");
            g2d.drawString("همه ی مرحله ها تموم شد :)", (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);

        }
    }
}
