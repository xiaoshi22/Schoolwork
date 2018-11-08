import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * SpaceProjectile.java <p>
 * 
 * A class for balls shot from cannon in PeggleGame. <p>
 */
public class SpaceProjectile extends GCompound implements Runnable {
    // constants
    private static final double 
    DELAY = 20,
    GRAVITY = 1;

    // instance variables
    private PeggleGame game; // the main game
    private double size; // size of the projectile
    private double speed, angle; // speed and direction of movement
    private boolean isAlive = true; // set initial isAlive be true, when game is over, we deactivate the projectile
    private boolean isActive = true; // set initial isActive be true

    /** the constructor, create the ball */
    public SpaceProjectile(double size, double speed, 
    double angle, PeggleGame game) {
        // save the parameters in instance variables
        this.game = game;
        this.size = size;
        this.speed = speed;
        this.angle = angle;

        // create the ball centered at the local origin
        GOval ball = new GOval(-size/2, -size/2, size, size);
        add(ball);
        ball.setFilled(true);
        ball.setFillColor(Color.GREEN);  
    }

    /** the run method, to animate the ball */
    public void run() {
        while (isAlive) {
            if (!isActive){ // if game is over, we set the projectile be inactive in main class and remove this type of projectile
                removeAll();
            } else { // if game is not over, we call oneTimeStep method to start the animation
                oneTimeStep();
                pause(DELAY);
            }
        }   
        explode(); // explode and disappear
    }

    /** return the angle of movement */
    public double getAngle() {
        return angle;
    }

    /** change the angle of movement when bounce */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /** return the speed of movement */
    public double getSpeed() {
        return speed;
    }
    
    /** stop the animation */
    public void deactivate() {
        isActive = false; // set isActive be false so that for a alive but inactive projectile we will remove it.
    }
    

    /** stop the animation */
    public void die() {
        isAlive = false; // set isAlive be false
    }

    /** in each time step, move the ball and bounce if hit the wall */
    private void oneTimeStep() {
        movePolar(speed, angle); // move the ball
        game.checkCollision(this); // check if hit anything
        applyGravity();// apply gravity
    }

    /** apply the effect of gravity */
    private void applyGravity() {
        //calculate xSpeed and ySpeed
        double xSpeed = speed*GMath.cosDegrees(angle);
        double ySpeed = -speed*GMath.sinDegrees(angle);

        ySpeed += GRAVITY; //apply gravity to ySpeed

        //calculate new spped and angle
        speed = GMath.distance(xSpeed, ySpeed);
        angle = GMath.angle(xSpeed, ySpeed);
    }

    /** show explosion and disappear */
    private void explode() {
        removeAll(); // remove the ball

        // draw an explosion
        GImage explosion = new GImage("bigexplosion.gif");
        explosion.setSize(2*size, 2*size);
        add(explosion, -2*size/2, -2*size/2);
        pause(500);
        removeAll();
    }
}