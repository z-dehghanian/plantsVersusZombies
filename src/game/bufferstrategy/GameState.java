/**
 * * In The Name of Allah **
 */
package game.bufferstrategy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * This class holds the state of the game and all of its elements. This class
 * also handles user inputs, which affect the game state.
 *
 * @author zahra dehghanian & monire safari
 */
public class GameState implements Serializable {

    public ArrayList<ArrayList<Zombies>> zombies;
    public ArrayList<ArrayList<Giyah>> giyah;
    public ArrayList<Noor> noors;
    public ArrayList<Car> cars;
    public ArrayList<ArrayList<Tir>> tir;
    public ArrayList<ArrayList<TirZ>> tirZ;
    public int level;
    public boolean sabzhast = false, aftabihast = false, gerduhast = false, yakhihast = false, gilashast = false, gharchhast = false;
    public boolean gameover = false;
    public int noor = 50;
    public int keyX = 160, keyY = 166;
    public boolean bilFlag = false;
    public int mouseMoveX, mouseMoveY;
    public boolean nextLevel = true, finish;
///////////////////////////////////////////////
    private final Random check;
    private final KeyHandler keyHandler;
    private final MouseHandler mouseHandler;
    private int tedadeZombie, rowGiyah, columnGiyah, w, s, mouseX = 135, mouseY = 166, flag = 0, noorFlag = 0;
    private boolean keyUP = false, keyDOWN = false, keyRIGHT = false, keyLEFT = false;
    private int deadZombies = 0, repeat = 0;

    public int forBil, gharchTime = 0;
    ////////
    public ImageIcon giyah1 = new ImageIcon("giyah1.png");
    public ImageIcon aftabi = new ImageIcon("Aftabi.png");
    public ImageIcon gerdu = new ImageIcon("gerdu.png");
    public ImageIcon yakhi = new ImageIcon("yakhi.png");
    public ImageIcon gilas = new ImageIcon("gilas.png");
    public ImageIcon gharch = new ImageIcon("gharch2.png");
    /////////////// baraye in k harvaght giyah entekhab kardim shekle mouse taghyir kone
    Sabz axSabz;
    Aftabi axAftabi;
    Gerdu axGerdu;
    Yakhi axYakhi;
    Gilas axGilas;
    Gharch axGharch;
    /////////////////

    /**
     * constructor for game state
     */
    public GameState() {
        zombies = new ArrayList();
        giyah = new ArrayList();
        cars = new ArrayList();
        tir = new ArrayList();
        tirZ = new ArrayList();
        for (int i = 0; i < 5; i++) {
            zombies.add(new ArrayList());
        }
        for (int i = 0; i < 5; i++) {
            giyah.add(new ArrayList());
        }
        for (int i = 0; i < 5; i++) {
            tir.add(new ArrayList());
        }
        for (int i = 0; i < 5; i++) {
            tirZ.add(new ArrayList());
        }
        noors = new ArrayList();
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        check = new Random(System.nanoTime());
        level = 1;
        tedadeZombie = 0;
        carSaz();
    }

    /*
     * The method which updates the game state.
     */
    ///////////////////// mashin dorost mikone
    private void carSaz() {
        int x = 100;
        int y = 130;
        for (int i = 0; i <= 4; i++) {
            Car c = new Car(x, y);
            y = y + 110;
            cars.add(c);
            cars.get(i).row = i;
        }
    }

    /////////////////////

    /**
     * check the element and update game state
     */
        public void update() {
        gharchTime++;
        //  if (tedadeZombie < level * 15) {
        if (nextLevel) {
            if (tedadeZombie < level * 10) {
                if (check.nextInt(500) < 1 + 2 * tedadeZombie) {
                    w = whichZombie();
                    s = whichRow();
                    switch (w) {
                        case 1:
                            zombies.get((s - 106) / 100).add(new Adi(1100, s));

                            tedadeZombie++;
                            break;
                        case 2:

                            zombies.get((s - 106) / 100).add(new kolah(1240, s));

                            tedadeZombie++;

                            break;
                        case 3:

                            zombies.get((s - 106) / 100).add(new Toopdar(1240, s));
                            tedadeZombie++;

                            break;
                        case 4:

                            zombies.get((s - 106) / 100).add(new Varzesh(1240, s));
                            tedadeZombie++;

                            break;
                        default:
                            break;

                    }

                }
            } else if (deadZombies == tedadeZombie) {
                System.out.println("wiiiiiiiiiiiiiiiiiiinnnnnnnnn");
                //win(); 
                nextLevel = false;
                deadZombies = 0;
                tedadeZombie = 0;
                noor = 50;
                level++;

                for (int j = 0; j < 5; j++) {
                    for (int i = this.giyah.get(j).size(); i > 0; i--) {
                        this.giyah.get(j).remove(i - 1);

                    }
                }
                for (int j = 0; j < 5; j++) {
                    for (int i = this.tir.get(j).size(); i > 0; i--) {
                        this.tir.get(j).remove(i - 1);

                    }
                }
                for (int j = this.cars.size(); j > 0; j--) {
                    cars.remove(j - 1);

                }
                for (int j = 0; j < 5; j++) {
                    for (int i = this.tirZ.get(j).size(); i > 0; i--) {
                        this.tirZ.get(j).remove(i - 1);
                    }
                }
                for (int j = this.noors.size(); j > 0; j--) {
                    noors.remove(j - 1);

                }
                carSaz();
            }
            /////////////////////

            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < zombies.get(j).size(); i++) {
                    if (zombies.get(j).get(i).x == 0) {
                        gameover = true;
                        //System.out.println("looooooooooooose");
                    }
                }
            }
            //////////////////////
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < giyah.get(j).size(); i++) {
                    if (!giyah.get(j).get(i).baresiKhorde(zombies.get(j))) {
                        giyah.get(j).remove(i);// tamoom shodane joone giyaha
                    }
                }
            }
            ////////////////////////////////////
            int gharchX = 0;
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < giyah.get(j).size(); i++) {
                    if (giyah.get(j).get(i).run(zombies.get(j))) {
                        if (giyah.get(j).get(i) instanceof Aftabi) {
                            Noor e = new Noor(giyah.get(j).get(i).x, giyah.get(j).get(i).y + 2);
                            e.run = false;
                            noors.add(e);
                        } else if (giyah.get(j).get(i) instanceof Sabz) {
                            Tir t = new SabzTir(giyah.get(j).get(i).x + 55, giyah.get(j).get(i).y + 4);
                            tir.get(j).add(t);
                        } else if (giyah.get(j).get(i) instanceof Yakhi) {
                            Tir t = new YakhiTir(giyah.get(j).get(i).x + 55, giyah.get(j).get(i).y + 4);
                            tir.get(j).add(t);
                        } else if (giyah.get(j).get(i) instanceof Gharch) {
                            Tir t = new GharchTir(giyah.get(j).get(i).x + 55, giyah.get(j).get(i).y + 4);
                            gharchX = giyah.get(j).get(i).x;
                            tir.get(j).add(t);

                        } else if (giyah.get(j).get(i) instanceof Gilas) {
                            if (j == 0) {
                                for (int a = 0; a < 2; a++) {
                                    for (int t = 0; t < zombies.get(a).size(); t++) {
                                        if (zombies.get(a).get(t).x < giyah.get(j).get(i).x + 110 && zombies.get(a).get(t).x > giyah.get(j).get(i).x - 110) {
                                            zombies.get(a).remove(t);
                                            deadZombies++;

                                        }
                                    }

                                }
                            } else if (j < 4) {
                                for (int a = j - 1; a < j + 2; a++) {
                                    for (int t = 0; t < zombies.get(a).size(); t++) {
                                        if (zombies.get(a).get(t).x < giyah.get(j).get(i).x + 110 && zombies.get(a).get(t).x > giyah.get(j).get(i).x - 110) {
                                            zombies.get(a).remove(t);
                                            deadZombies++;
                                        }
                                    }

                                }
                            } else if (j == 4) {
                                for (int a = 3; a < 5; a++) {
                                    for (int t = 0; t < zombies.get(a).size(); t++) {
                                        if (zombies.get(a).get(t).x < giyah.get(j).get(i).x + 110 && zombies.get(a).get(t).x > giyah.get(j).get(i).x - 110) {
                                            zombies.get(a).remove(t);
                                            deadZombies++;
                                        }
                                    }

                                }

                            }
                            giyah.get(j).remove(i);
                        }
                    }
                }
            }
            ///////////////////

            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < tir.get(j).size(); i++) {
                    int m = tir.get(j).get(i).run(zombies.get(j));
                    if (m != -1) {

                        if (tir.get(j).get(i) instanceof GharchTir) {
                            zombies.get(j).get(m).life = zombies.get(j).get(m).life - 1;
                        } else if (tir.get(j).get(i) instanceof SabzTir) {
                            zombies.get(j).get(m).life = zombies.get(j).get(m).life - 2;
                        } else if (tir.get(j).get(i) instanceof YakhiTir) {
                            zombies.get(j).get(m).life = zombies.get(j).get(m).life - 3;
                            zombies.get(j).get(m).speed = 1;

                        }
                        tir.get(j).remove(i);
                    }

                }
            }

            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < tirZ.get(j).size(); i++) {
                    int m = tirZ.get(j).get(i).run(giyah.get(j));
                    if (m != -1) {
                        tirZ.get(j).remove(i);

                        giyah.get(j).get(m).life = giyah.get(j).get(m).life - 25;
                    }
                }
            }
            /////////////
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < zombies.get(j).size(); i++) {
                    if (zombies.get(j).get(i).life == 0) {
                        zombies.get(j).remove(i);
                        deadZombies++;
                    }
                }
            }

            /////////////////////////////////////////////////////////////////////////
            ///////////////////////// zombie & cars
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < zombies.get(j).size(); i++) {
                    if (!(zombies.get(j).get(i) instanceof Toopdar)) {
                        zombies.get(j).get(i).run(giyah.get(j));
                    } else if (((Toopdar) zombies.get(j).get(i)).tirZan(giyah.get(j)) == 3) {
                        zombies.get(j).get(i).run(giyah.get(j));
                    } else if (((Toopdar) zombies.get(j).get(i)).tirZan(giyah.get(j)) == 1) {
                        TirZ t = new TirZ(zombies.get(j).get(i).x - 5, zombies.get(j).get(i).y + 80);
                        tirZ.get(j).add(t);
                    }

                    if (zombies.get(j).get(i).x < 120) {
                        for (int m = 0; m < cars.size(); m++) {
                            if (cars.get(m).row == j) {
                                cars.get(m).car = true;

                                zombies.get(j).remove((i));
                                deadZombies++;
                            }

                        }

                    }

                }
            }

            for (int k = 0; k < cars.size(); k++) {
                if (cars.get(k).car) {
                    cars.get(k).run();
                    int cRow = cars.get(k).row;
                    for (int i = 0; i < zombies.get(cRow).size(); i++) {

                        if (zombies.get(cRow).get(i).x <= cars.get(k).x + 20 && zombies.get(cRow).get(i).x >= cars.get(k).x) {
                            zombies.get(cRow).remove((i));
                            deadZombies++;
                        }

                        if (cars.get(k).x >= 1300) {
                            cars.get(k).car = false;
                            cars.remove(k);

                        }

                    }

                }
            }

            ////////
            if (check.nextInt(500) < 3 && level <= 5) {
                noors.add(new Noor(200 + check.nextInt(5) * 100, 0));
            }
            for (int i = 0; i < noors.size(); i++) {
                if (noors.get(i).y > 300) {
                    if (check.nextInt(1000) < 5) {
                        noors.get(i).run = false;
                    }
                }
                if (noors.get(i).run == true) {
                    noors.get(i).run();
                }
                noors.get(i).life--;
                if (noors.get(i).life < 0) {
                    noors.remove(i);
                }
            }
            /////////////////////////////////////////////////////////////////////////

            if (sabzhast && flag == 1) {
                axSabz = new Sabz(mouseMoveX, mouseMoveY, 0);
                axGerdu = null;
                axAftabi = null;
                axYakhi = null;
                axGilas = null;
                axGharch = null;
            } else if (aftabihast && flag == 1) {

                axAftabi = new Aftabi(mouseMoveX, mouseMoveY, 0);
                axSabz = null;
                axGerdu = null;
                axYakhi = null;
                axGilas = null;
                axGharch = null;
            } else if (gerduhast && flag == 1) {

                axGerdu = new Gerdu(mouseMoveX, mouseMoveY, 0);
                axSabz = null;
                axAftabi = null;
                axYakhi = null;
                axGilas = null;
                axGharch = null;
            } else if (yakhihast && flag == 1) {

                axYakhi = new Yakhi(mouseMoveX, mouseMoveY, 0);
                axSabz = null;
                axGerdu = null;
                axAftabi = null;
                axGilas = null;
                axGharch = null;
            } else if (gilashast && flag == 1) {

                axGilas = new Gilas(mouseMoveX, mouseMoveY, 0);
                axSabz = null;
                axGerdu = null;
                axAftabi = null;
                axYakhi = null;
                axGharch = null;

            } else if (gharchhast && flag == 1) {

                axGharch = new Gharch(mouseMoveX, mouseMoveY, 0);
                axSabz = null;
                axGerdu = null;
                axAftabi = null;
                axYakhi = null;
                axGilas = null;

            }
            if (repeat == 1 && !bilFlag) {
                flag = 1;
                repeat = 0;

            } else if (repeat == 1 && bilFlag) {
                repeat = 0;
                bilFlag = false;
                giyah.get(rowGiyah).remove(forBil);
            } else if (repeat == 2 && bilFlag) {
                bilFlag = false;
                repeat = 0;
            } else {
                if (sabzhast && flag == 0) {

                    giyah.get(rowGiyah).add(new Sabz(mouseX, mouseY, columnGiyah));

                    sabzhast = false;
                    axSabz = null;

                } else if (aftabihast && flag == 0) {

                    giyah.get(rowGiyah).add(new Aftabi(mouseX, mouseY, columnGiyah));

                    aftabihast = false;
                    axAftabi = null;
                } else if (gerduhast && flag == 0) {

                    giyah.get(rowGiyah).add(new Gerdu(mouseX, mouseY, columnGiyah));

                    gerduhast = false;
                    axGerdu = null;

                } else if (yakhihast && flag == 0) {

                    giyah.get(rowGiyah).add(new Yakhi(mouseX, mouseY, columnGiyah));

                    yakhihast = false;
                    axYakhi = null;

                } else if (gilashast && flag == 0) {

                    giyah.get(rowGiyah).add(new Gilas(mouseX, mouseY, columnGiyah));

                    gilashast = false;
                    axGilas = null;

                } else if (gharchhast && flag == 0) {

                    giyah.get(rowGiyah).add(new Gharch(mouseX, mouseY, columnGiyah));
                    gharchTime = 0;
                    gharchhast = false;
                    axGharch = null;

                }
            }

            if (!sabzhast && flag == 0 && !aftabihast && !gerduhast && !yakhihast && !gilashast && !bilFlag) {

                for (int i = 0; i < noors.size(); i++) {
                    if ((mouseX <= (100 + noors.get(i).x) && mouseX >= noors.get(i).x) && (mouseY <= (100 + noors.get(i).y) && mouseY >= noors.get(i).y)) {
                        noors.remove(noors.get(i));
                        noor = noor + 50;

                    }
                }
            }
            ///////////////////////////////////
            if (keyUP) {
                mouseY -= 140;
                if (mouseY < 231) {
                    mouseY = 135;
                } else {
                    jayeGiyah();
                }
                keyY = mouseY;
            }
            if (keyDOWN) {
                mouseY += 140;
                if (mouseY < 231) {
                    mouseY = 135;
                } else {
                    jayeGiyah();
                }
                keyY = mouseY;
            }
            if (keyLEFT) {
                mouseX -= 140;
                if (mouseX < 279) {
                    mouseX = 166;
                } else {
                    jayeGiyah();
                }
                keyX = mouseX;
            }
            if (keyRIGHT) {
                mouseX += 140;
                if (mouseX < 279) {
                    mouseX = 166;
                } else {
                    jayeGiyah();
                }
                keyX = mouseX;
            }

            //////////////////////
        }
    }

    private int whichZombie() {
        return check.nextInt(4) + 1;
    }

    public KeyListener getKeyListener() {
        return keyHandler;
    }

    public MouseListener getMouseListener() {
        return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }

    private int whichRow() {
        return 106 + check.nextInt(5) * 100;
    }

    /**
     * The keyboard handler.
     */
    class KeyHandler implements KeyListener {

        private static final long serialVersionUID = 9L;

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    keyUP = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = true;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
                    break;
                case KeyEvent.VK_ENTER:

                    for (int i = 0; i < giyah.get(rowGiyah).size(); i++) {
                        if (giyah.get(rowGiyah).get(i).column == columnGiyah) {
                            repeat = 1;
                            forBil = i;
                        }
                    }
                    jayeGiyah();
                    flag = 0;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    keyUP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    break;
            }
        }

    }

    /**
     * The mouse handler.
     */
    class MouseHandler implements MouseListener, MouseMotionListener, Serializable {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            if (!sabzhast) {
                keyX = 160;
                keyY = 166;
                if ((mouseY <= 100 && mouseY >= 30) && (mouseX <= 160 && mouseX >= 100) && (noor > 0 && noor - 100 >= 0)) {
                    sabzhast = true;
                    aftabihast = false;
                    gilashast = false;
                    yakhihast = false;
                    gharchhast = false;
                    gerduhast = false;
                    flag = 1;
                    noor = noor - 100;

                }
            } else {

                mouseX = e.getX();
                mouseY = e.getY();
                jayeGiyah();
                for (int i = 0; i < giyah.get(rowGiyah).size(); i++) {
                    if (giyah.get(rowGiyah).get(i).column == columnGiyah) {
                        repeat = 1;
                        forBil = i;
                    }
                }
                flag = 0;
            }
            if (!aftabihast) {
                keyX = 160;
                keyY = 166;
                if ((mouseY <= 100 && mouseY >= 30) && (mouseX <= 220 && mouseX >= 160) && (noor - 50 >= 0)) {
                    aftabihast = true;
                    sabzhast = false;
                    gilashast = false;
                    yakhihast = false;
                    gharchhast = false;
                    gerduhast = false;

                    flag = 1;
                    noor = noor - 50;

                }
            } else {

                mouseX = e.getX();
                mouseY = e.getY();
                jayeGiyah();
                for (int i = 0; i < giyah.get(rowGiyah).size(); i++) {
                    if (giyah.get(rowGiyah).get(i).column == columnGiyah) {
                        repeat = 1;
                    }

                }
                flag = 0;

            }
            if (!gerduhast && level >= 2) {
                keyX = 160;
                keyY = 166;
                if ((mouseY <= 100 && mouseY >= 30) && (mouseX <= 280 && mouseX >= 220) && (noor - 50 >= 0)) {
                    gerduhast = true;
                    aftabihast = false;
                    sabzhast = false;
                    gilashast = false;
                    yakhihast = false;
                    gharchhast = false;

                    flag = 1;
                    noor = noor - 50;

                }
            } else if (gerduhast && level >= 2) {

                mouseX = e.getX();
                mouseY = e.getY();
                jayeGiyah();
                for (int i = 0; i < giyah.get(rowGiyah).size(); i++) {
                    if (giyah.get(rowGiyah).get(i).column == columnGiyah) {
                        repeat = 1;
                    }
                }
                flag = 0;

            }
            if (!yakhihast && level >= 3) {
                keyX = 160;
                keyY = 166;
                if ((mouseY <= 100 && mouseY >= 30) && (mouseX <= 340 && mouseX >= 280) && (noor - 175 >= 0)) {
                    yakhihast = true;
                    aftabihast = false;
                    sabzhast = false;
                    gilashast = false;
                    gharchhast = false;
                    gerduhast = false;

                    flag = 1;
                    noor = noor - 175;

                }
            } else if (yakhihast && level >= 3) {

                mouseX = e.getX();
                mouseY = e.getY();
                jayeGiyah();
                for (int i = 0; i < giyah.get(rowGiyah).size(); i++) {
                    if (giyah.get(rowGiyah).get(i).column == columnGiyah) {
                        repeat = 1;
                    }
                }
                flag = 0;

            }
            /////////////////
            if (!gilashast && level >= 4) {
                keyX = 160;
                keyY = 166;
                if ((mouseY <= 100 && mouseY >= 30) && (mouseX <= 400 && mouseX >= 340) && (noor - 150 >= 0)) {
                    gilashast = true;
                    aftabihast = false;
                    sabzhast = false;
                    yakhihast = false;
                    gharchhast = false;
                    gerduhast = false;

                    flag = 1;
                    noor = noor - 200;

                }
            } else if (gilashast && level >= 4) {

                mouseX = e.getX();
                mouseY = e.getY();
                jayeGiyah();
                for (int i = 0; i < giyah.get(rowGiyah).size(); i++) {
                    if (giyah.get(rowGiyah).get(i).column == columnGiyah) {
                        repeat = 1;
                    }
                }
                flag = 0;

            }
            if (!gharchhast && level >= 6 && gharchTime >= 51) {
                keyX = 160;
                keyY = 166;
                if ((mouseY <= 100 && mouseY >= 30) && (mouseX <= 460 && mouseX >= 400)) {
                    gharchhast = true;
                    aftabihast = false;
                    sabzhast = false;
                    gilashast = false;
                    yakhihast = false;
                    gerduhast = false;

                    flag = 1;

                }
            } else if (gharchhast && level >= 6) {

                mouseX = e.getX();
                mouseY = e.getY();
                jayeGiyah();
                for (int i = 0; i < giyah.get(rowGiyah).size(); i++) {
                    if (giyah.get(rowGiyah).get(i).column == columnGiyah) {
                        repeat = 1;
                    }
                }
                flag = 0;

            }
/////////////
            if (!bilFlag) {
                keyX = 160;
                keyY = 166;
                if ((mouseY <= 100 && mouseY >= 30) && (mouseX <= 610 && mouseX >= 550)) {
                    bilFlag = true;
                    flag = 1;
                    aftabihast = false;
                    sabzhast = false;
                    gilashast = false;
                    yakhihast = false;
                    gharchhast = false;
                    gerduhast = false;

                }
            } else {
                mouseX = e.getX();
                mouseY = e.getY();
                jayeGiyah();
                for (int i = 0; i < giyah.get(rowGiyah).size(); i++) {
                    if (giyah.get(rowGiyah).get(i).column == columnGiyah) {
                        repeat = 1;
                        forBil = i;
                    }
                }
                if (repeat != 1) {
                    repeat = 2;
                }
                flag = 0;

            }
            if ((mouseX >= 1190 && mouseX <= 1250) && (mouseY >= 40 && mouseY <= 60)) {
                finish = true;
            }
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
            mouseMoveX = e.getX();
            mouseMoveY = e.getY();
        }
    }

    /**
     * to find the right place for plants in game
     */
    public void jayeGiyah() {
        if (mouseX < 160) {

            aftabihast = false;
            sabzhast = false;
            gilashast = false;
            yakhihast = false;
            gharchhast = false;
            gerduhast = false;
            axGharch = null;
            axSabz = null;
            axGerdu = null;
            axAftabi = null;
            axYakhi = null;
            axGilas = null;

        }
        if (mouseX >= 160 && mouseX < 279) {
            mouseX = 166;
            columnGiyah = 0;
        } else if (mouseX >= 279 && mouseX < 389) {
            mouseX = 279;
            columnGiyah = 1;
        } else if (mouseX >= 389 && mouseX < 512) {
            mouseX = 389;
            columnGiyah = 2;
        } else if (mouseX >= 512 && mouseX < 627) {
            mouseX = 512;
            columnGiyah = 3;
        } else if (mouseX >= 627 && mouseX < 739) {
            mouseX = 627;
            columnGiyah = 4;
        } else if (mouseX >= 739 && mouseX < 859) {
            mouseX = 739;
            columnGiyah = 5;
        } else if (mouseX >= 859 && mouseX < 964) {
            mouseX = 859;
            columnGiyah = 6;
        } else if (mouseX >= 964 && mouseX < 1079) {
            mouseX = 964;
            columnGiyah = 7;
        } else if (mouseX >= 1079) {
            mouseX = 1079;
            columnGiyah = 8;
        }
        ////////////
        if (mouseY < 115) {

            aftabihast = false;
            sabzhast = false;
            gilashast = false;
            yakhihast = false;
            gharchhast = false;
            gerduhast = false;
            axGharch = null;
            axSabz = null;
            axGerdu = null;
            axAftabi = null;
            axYakhi = null;
            axGilas = null;

        }

        if (mouseY >= 115 && mouseY < 231) {
            mouseY = 135;
            rowGiyah = 0;
        } else if (mouseY >= 231 && mouseY < 354) {
            mouseY = 231;
            rowGiyah = 1;
        } else if (mouseY >= 354 && mouseY < 476) {
            mouseY = 354;
            rowGiyah = 2;
        } else if (mouseY >= 476 && mouseY < 575) {
            mouseY = 476;
            rowGiyah = 3;
        } else if (mouseY >= 575) {
            mouseY = 575;
            rowGiyah = 4;
        }

    }
}
