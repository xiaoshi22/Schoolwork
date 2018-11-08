import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.util.*;

/**
 * BlackHoleDesign.java <p>
 * 
 * a class for the bouncing black holes in the SpaceTravel game. <p>
 * 
 * @author: Xiaoshi Wang
 */
public class BlackHole extends GCompound implements Runnable{
    //constants
    private static final double 
    DELAY = 20;
    
    // instance variables
    private double speed, angle; // the speed and angle of the movement of the black hole
    private SpaceTravel game; // the game instance that this object belongs to the main class

    /** a constructor for creating an instance of the black hole */
    public BlackHole(double speed, double angle, SpaceTravel game) {
        // same the parameters to the instance vars
        this.speed = speed;
        this.angle = angle;
        this.game = game;
        
        // use a loop to create all the concentric circles with gradual changing shade of gray
        int n = 30; // number of rings
        double size = 40; // size of smallest ring
        double gap = 4; // gap between two consecutive rings
        int colorDiff = GMath.round(255/n); // diff in gray shade
        for (int i =0; i < n; i++) {
            GOval star = new GOval(size+(n-i)*gap*2,size+(n-i)*gap*2);// size of oval is decreasing
            add(star, 140-size/2-(n-i)*gap, 140-size/2-(n-i)*gap);// ovals are all centered in the center of window
            star.setFilled(true);
            int colorComp = colorDiff*(i+1); // color components of each ring is increasing
            star.setColor(new Color(colorComp,colorComp,colorComp));
        }
        GOval dot = new GOval(120, 120, 40, 40);
        add(dot);
        dot.setFilled(true);
        dot.setFillColor(Color.BLACK);
    }

    /** the run method, control the animation */
    public void run() {
        while (!game.gameIsOver) { // while isStopped is not true
            oneTimeStep();//continue the animation by calling oneTimeStep and pause methods
            pause(DELAY);
        }
    }

    /** return the current angle */
    public double getAngle() {
        return angle;
    }

    /** set the angle to a new value */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /** a helper method, move the object in each time step and check for collision */
    private void oneTimeStep() { 
        movePolar(speed, angle);// move the object using the variable speed and angle
        game.checkCollision(this);// call the check collision method in the game and pass this object as a parameter
    }
    }
