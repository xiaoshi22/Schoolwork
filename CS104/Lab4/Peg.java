import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 *  Peg.java <p>
 * 
 * A class for pegs that bounce and flash inside PeggleGame. <p>
 */
public class Peg extends GCompound implements Runnable {
    // constants
    private static final double 
    DELAY = 60,
    OUTERSIZE = 35,
    INNERSIZE = OUTERSIZE*0.77;

    // instance variables
    private int counter; // the initial counter varies for each peg so that the inital direaction and the time to meet its "5-steptrunning point" is different from peg to peg.
    private boolean isExplosive; // determine whether the peg is a explosive peg or not
    private double speed; // speed of the peg
    private PeggleGame game; // the main game

    private boolean isAlive = true; // set isAlive initially be true
    private GOval innerPeg; // the inner peg 
    private Color color; // a explosive peg is in red and a inexplosive is in blue

    /** the constructor, create the peg */
    public Peg(int counter, boolean isExplosive, double speed, PeggleGame game) {
        // save the parameters in instance variables
        this.game = game;
        this.counter = counter;
        this.isExplosive = isExplosive;
        this.speed = speed;

        // create the outter peg, centered at the local origin
        GOval outerPeg = new GOval(OUTERSIZE, OUTERSIZE);
        outerPeg.setFilled(true);
        outerPeg.setFillColor(Color.WHITE);
        add(outerPeg, -OUTERSIZE/2, -OUTERSIZE/2);

        // we set a explosive peg is in red and a inexplosive is in blue 
        if(isExplosive) {
            color = Color.RED;
        } else {
            color = Color.BLUE;
        }
        // draw the inner peg
        innerPeg = new GOval(INNERSIZE, INNERSIZE);
        innerPeg.setFilled(true);
        innerPeg.setFillColor(color);
        add(innerPeg, -INNERSIZE/2, -INNERSIZE/2);
    }

    /** the run method, to animate the peg */
    public void run() {
        while (isAlive) { // we start amination if the peg is alive
            oneTimeStep();
            pause(DELAY);      
        }   
        explode(); // call explode method if peg is not alive
    }

    /** return isExplosive */ 
    public boolean isExplosive() {
        return isExplosive;
    }

    /** return isAlive */
    public boolean isAlive() {
        return isAlive;
    }

    /** kill the peg */
    public void die() {
        isAlive = false;
    }

    /** in each time step, move the peg and bounce */
    private void oneTimeStep() {
        counter ++; // add one more to our counter for each time step

		// make the flashing effect by change colors for each time step
        if(counter%2 == 0){ 
            innerPeg.setFillColor(color);
        } else { // for half of the time, the peg is in black
            innerPeg.setFillColor(Color.BLACK);
        }

		// we change the direction for each 5 steps
        if (counter%5 == 0) {
            speed = -speed;
        }

		// move the peg
        movePolar(speed, 90);
    }

    /** show explosion */
    private void explode() {
        removeAll(); // remove the peg

        // draw an explosion
        GImage explosion = new GImage("bigexplosion.gif");
        explosion.setSize(OUTERSIZE, OUTERSIZE);
        add(explosion, -OUTERSIZE/2, -OUTERSIZE/2);
        pause(500);
        removeAll();
    }
}