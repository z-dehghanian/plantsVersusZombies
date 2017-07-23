/**
 * * In The Name of Allah **
 */
package game.bufferstrategy;

/**
 * A simple structure for the main game loop. THIS IS NOT PERFECT, but works in
 * this situations. it just made of two method(update() and render()) to
 * indicate the game
 *
 * @author zahra dehghanian & monire safari
 */
public class GameLoop implements Runnable {

    /**
     * Frame Per Second. Higher is better, but any value above 24 is fine.
     */
    public static final int FPS = 30;

    private GameFrame canvas;
    private GameState state = null;

    public GameLoop(GameFrame frame) {
        canvas = frame;
    }

    /**
     * This must be called before the game loop starts.
     */
    public void init() {
                //Load.loadGame();
        //state = Load.gs;
        // Perform all initializations ...
        if (state == null) {
            state = new GameState();
        }

        canvas.addKeyListener(state.getKeyListener());
        canvas.addMouseListener(state.getMouseListener());
        canvas.addMouseMotionListener(state.getMouseMotionListener());
    }

    @Override
    public void run() {
        boolean gameOver = false;
        while (!gameOver) {
            try {
                long start = System.currentTimeMillis();
				//

                state.update();
                canvas.render(state);
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0) {
                    Thread.sleep(delay);
                }
            } catch (InterruptedException ex) {
            }
        }
    }
}
