import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 *  Turtle.java <p>
 * 
 * A class for Turtles moving along the ground and defeat our tower in TowerDefense. <p>
 */

public class Turtle extends GCompound implements Runnable {
    // constants
    private static final double
    DELAY = 20,
    SIZE = 40;

    // instance variables
    private boolean boss;
    private TowerDefense game;

    private double speed; // speed of the turtlle
    private boolean isAlive = true; // turtle is alive initially
    private int count = 20; // count for the life left 
    private double size = 60; // size of the turtle
    private GRect bloodLeft; // a rectangle to show blood left
    private Color color; // color of the rectangle

    /** the constructor, create the turtle */
    public Turtle(boolean boss, double speed, TowerDefense game) {
        // save the parameters in instance variables
        this.boss = boss;
        this.speed = speed;
        this.game = game;

        // we set a boss turtle is larger than normal one
        if(boss) { // color, count size and image are different for boss
            color = Color.BLUE;
            count = 5000;
            size = 240;
            GImage turtle = new GImage("boss.gif");
            turtle.setSize(size, size*2/3);
            add(turtle, -size/2, -size/3); 

            // draw a box in white at the base to contain all the blood the turtle is left
            GRect base = new GRect(size, 5);
            add(base, -size/2, -size/3-10);

            // draw a red rect to represent the blood left
            bloodLeft = new GRect(size, 5);
            bloodLeft.setFilled(true);
            bloodLeft.setFillColor(color);
            add(bloodLeft, -size/2, -size/3-10);
        } else {
            color = Color.RED;
            GImage turtle = new GImage("turtle.gif");
            turtle.setSize(size, size*2/3);
            add(turtle, -size/2, -size/3); 

            // draw a box in white at the base to contain all the blood the turtle is left
            GRect base = new GRect(size, 5);
            add(base, -size/2, -size/3-10);

            // draw a red rect to represent the blood left
            bloodLeft = new GRect(size, 5);
            bloodLeft.setFilled(true);
            bloodLeft.setFillColor(color);
            add(bloodLeft, -size/2, -size/3-10);
        }
    }

    /** the run method, to animate the turtle */
    public void run() {
        while (true) { // we start amination if the game is not over
            if(!game.getPause()){ // pause if Space is pressed
                oneTimeStep();
                pause(DELAY);      
            } 
        }
    }

    /** call in game to deduce the blood left when the turtle is hited */
    public void deduceCount(int bloodLost){
        count = count - bloodLost;
        updateBloodLeft(bloodLeft, count);
    }

    /** update the bloodLeft */
    private void updateBloodLeft(GRect rect, int integer) {
        if (boss){
            rect.setSize(size*integer/5000, 5);
        } else {
            rect.setSize(size*integer/20, 5);
        }
    }

    /** return boolean boss */
    public boolean getIsBoss(){
        return boss;
    }

    /** reset the location when the turtle losses all its blood, else move the turtle in each step */
    private void oneTimeStep() {
        if (count <= 0) {
            if (boss) {
                game.youWin();
            } else {
                game.resetLocation(this);
                count = 20;
                updateBloodLeft(bloodLeft, count);
                return;
            }
        }
        // move the turtle
        movePolar(speed, 180);
        game.checkCollision(this); // check if hit anything
    }
}