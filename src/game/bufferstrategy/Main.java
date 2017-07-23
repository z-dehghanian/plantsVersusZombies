/**
 * * In The Name of Allah **
 */
package game.bufferstrategy;

import java.io.Serializable;

/**
 * *the Main class is a simple class just to start program
 *
 * @version 2.0.1
 * @since 2016-07-02
 *
 *
 * @author zahra dehghanian & monire safari
 */
public class Main implements Serializable {

    public static void main(String[] args) {
        // Initialize the global thread-pool
        ThreadPool.init();

        ///////////////////// menu
        MenuFrame menu = new MenuFrame();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);

                /////////////////
        Sound sound1 = new Sound(2);
        sound1.start();

    }
}
