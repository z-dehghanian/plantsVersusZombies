package game.bufferstrategy;

/**
 * this class is used to provide sound as a new thread in the whole of the game
 *
 *
 *
 * @author zahra dehghanian & monire safari
 */
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Sound extends Thread {

    private int num;

    public Sound(int num) {
        // TODO Auto-generated constructor stub
        this.num = num;
    }

    @Override
    public void run() {
        if (num == 2) {

            try {
                FileInputStream file = new FileInputStream("crazy-dave.mp3");
                Player playMP3 = new Player(file);
                playMP3.play();
            } catch (Exception e) {
                System.out.println("not  2    sounddddddddddddddddddddddddddddddddddddddddddddddd");
            }
        } else if (num == 1) {
            try {
                FileInputStream file = new FileInputStream("05 - It's a Game (Reprise).mp3");
        //Player playMP3 = new Player(file);
                //playMP3.play();
            } catch (Exception e) {
                System.out.println("not 1  sounddddddddddddddddddddddddddddddddddddddddddddddd");
            }
        }
    }

}
