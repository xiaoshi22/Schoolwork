import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * Projectile.java <p>
 * 
 * A class for balls shot from cannon or gun in TowerDefense. <p>
 */
public class Projectile extends GCompound implements Runnable {
    // constants
    private static final double
    DELAY = 20,
    GRAVITY = 0.1;

    // instant varibles
    //private boolean isGun;
    private double size; // size of the projectile
    private double speed; // speed of the projectile
    private double angle; // angle of the projectile
    private int proLevel; // projectile level
    private Weapon weapon; // the weapon it bolongs to
    private TowerDefense game; // main game

    private boolean isAlive = true; // true if projectile does not die
    private Color color; // different colors for different levels

    /** Constructor for objects of class Projectile */
    public Projectile(double size, double speed, double angle, int proLevel, Weapon weapon, TowerDefense game) {
        // initialise instance variables
        this.size = size;
        this.speed = speed;
        this.angle = angle;
        this.proLevel = proLevel;
        this.weapon = weapon;
        this.game = game;
        
        // different colors for different projectile level
        if (proLevel == 0) {
            color = Color.RED;
        }else if (proLevel == 1) {
            color = new Color(128,0,128);
            size = size*1.2;
        }else if (proLevel == 2) {
            color = Color.BLUE;
            size = size*1.5;
        }
        // draw the projectile
        GOval projectile = new GOval(size, size);
        add(projectile, -size/2, -size/2);
        projectile.setFilled(true);
        projectile.setFillColor(color);
    }

    /** animate the projectile */
    public void run() {
        while(isAlive) {
            oneTimeStep(); // call oneTimeStep
            pause(DELAY);
        }
        explode(); // explode if die
    }

    /** stop the animation */
    public void die() {
        isAlive = false;
    }

    /** return the weapon */
    public Weapon getWeapon() {
        return weapon;
    }

    /** set the proLevel */
    public int getProLevel() {
        return proLevel;
    }

    /** move the projectile and check collision in each time step */
    private void oneTimeStep() {
        movePolar(speed, angle); // move the ball
        applyGravity();// apply gravity
        game.checkCollision(this); // check if hit anything
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
        GStar explosion = new GStar(size*1.5, 10);
        add(explosion);
        explosion.setFilled(true);
        explosion.setColor(Color.RED);
        pause(200);
        removeAll();
    }
}