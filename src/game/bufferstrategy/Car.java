/*
 * this class is written  to perform lawn mower 
 * wich run for first time when zombies try to enter to your house 
 @author zahra dehghanian & monire safari
 */
package game.bufferstrategy;

import javax.swing.ImageIcon;

/**
 *
 * @author zahra dehghanian & monire safari
 */
public class Car {
    
    int x , y ,row;
    boolean car = false;
    public ImageIcon carImage ;
    
    /**
     *
     * @param x position of car
     * @param y position of car
     */
    public Car (int x,int y) {
        this.x = x;
        this.y = y;
        carImage = new ImageIcon("car.png");
        
    }
    
    /**
     * move the car
     */
    public void run() {
       
       x = x + 3;
       
    }
    
}
