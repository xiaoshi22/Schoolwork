import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 *  Weapon.java <p>
 * 
 * A class for Weapons to shot projectiles and defeat our tower in TowerDefense. <p>
 */

public class Weapon extends GCompound implements Runnable {
    // constants
    private static final double DELAY = 160;

    // instance variables
    boolean isCannon; // cannon or gun
    private double size; // size of the weapon
    private TowerDefense game; // main game

    private GPolygon cannon; // the weapon
    private double angle= 0; // angle of cannon
    private double speed = 10; // current speed
    private double aimPointX, aimPointY; // location of the aim point
    private GLine aimLine; // line for aiming
    private int level = 0; // inital weapon level
    private GRect[] levelBar = new GRect[3]; // level bar on the top of the weapon
    private int count = 0; // count used in oneTimeStep
    private boolean isAlive = true; // weapon is alive initially
    private boolean isShown = false; // the aim line is not shown initially

    /** the constructor, create the weapon */
    public Weapon(boolean isCannon, double size, TowerDefense game) {
        // save the parameters
        this.isCannon = isCannon;
        this.size = size;
        this.game = game;

        // draw a cannon or a gun
        if (isCannon) { 
            cannon = new GPolygon();
            // add the corners of the cannon
            cannon.addVertex(0, -size/4);
            cannon.addVertex(size, -size/6);
            cannon.addVertex(size, size/6);
            cannon.addVertex(0, size/4);
            cannon.setFilled(true);
            cannon.setFillColor(Color.ORANGE);
            add(cannon, 0, 0);

            GImage base = new GImage("base.gif");
            base.setSize(80, 65);
            add(base, -40, -20);

            speed = speed/1.5;
        } else {
            cannon = new GPolygon();
            // add the  corners of the gun
            cannon.addVertex(-size*3, -size/2);
            cannon.addVertex(size*6, -size/2);
            cannon.addVertex(size*6, 0);
            cannon.addVertex(size*6.5, 0);
            cannon.addVertex(size*6.5, size/4);
            cannon.addVertex(size*6.75, size/4);
            cannon.addVertex(size*6.75, size/2);
            cannon.addVertex(size*6.5, size/2);
            cannon.addVertex(size*6.5, 0);
            cannon.addVertex(size*6.5, size/2);
            cannon.addVertex(size*6, size/2);
            cannon.addVertex(size*6, 0);
            cannon.addVertex(size*6, size/2);
            cannon.addVertex(-size*3, size/2);
            cannon.setFilled(true);
            cannon.setFillColor(new Color(85, 107, 47));
            add(cannon, 0, 0);

            GImage base = new GImage("mgbase.gif");
            base.setSize(80, 50);
            add(base, -40, -5);
        }

        // draw the level bar on the top of the weapon with three levels
        for(int i = 0; i <3; i++){
            levelBar[i]=new GRect(15, 10);
            add(levelBar[i], -25+15*i, -45);
            levelBar[i].setFilled(true);
        }
        levelBar[0].setFillColor(Color.RED);
        levelBar[1].setFillColor(Color.WHITE);
        levelBar[2].setFillColor(Color.WHITE);

        // draw a line from center of star to mouse point
        aimLine = new GLine(getX(), getY(), getX(), getY());
        game.add(aimLine);
        aimLine.setColor(Color.red);
    }

    /** animation the weapon */
    public void run() {
        while (isAlive) {
            if(!game.getPause()){ // pause if press space
                oneTimeStep();
                pause(DELAY); 
                count ++;
            }
        }   
        explode(); // explode if die
    }

    /** set the aimPoint in oneTimeStep method*/
    public void setAimPoint(double aimPointX, double aimPointY) {
        aimLine.setStartPoint(getX(), getY());
        aimLine.setEndPoint(aimPointX, aimPointY);

        // angle of the aimLine determines the fire angle
        double lineAng = GMath.angle(getX(), getY(), 
                aimPointX, aimPointY);
        if (isCannon){
            lineAng = lineAng+45;   
        }

        cannon.rotate(lineAng - angle); // rotate cannon to new agle
        this.angle = lineAng;
    }

    /** set the aim line visible or invisible */
    public void setIsShown(boolean isShown){
        this.isShown = isShown;
    }

    /** the weapon is ready to update */
    public void upgradableNow() {
        if (level <2 ) {
            levelBar[level+1].setFillColor(Color.GREEN);
        }
    }

    /** update the level bar when we have enough credit to update */
    public void updateLevelBar() {
        if (level == 0){
            levelBar[1].setFillColor(Color.WHITE);
        } else if(level == 1){
            levelBar[1].setFillColor(Color.RED);
            levelBar[2].setFillColor(Color.WHITE);
        } else if(level == 2){
            levelBar[2].setFillColor(Color.RED);
        }
    }

    /** set the level */
    public void setLevel(int i) {
        this.level = i;
    }

    /** return level */
    public int getLevel() {
        return level; 
    }

    /** return speed */
    public double getSpeed() {
        return speed; 
    }

    /** return size */
    public double returnSize() {
        return size; 
    }

    /** kill the weapon */
    public void die() {
        isAlive = false;
    }

    /** shot a projectile in each step in the direction of aimLine */
    private void oneTimeStep() {
        if (!isCannon || count%20==0){ // th pause time between each one time step for a cannon is longer
            setAimPoint(game.firstTurtle(), 550); // find the location of the first turtle

            // set up the aim angle
            double proAng = angle;
            Projectile projectile = new Projectile(size*2/3, speed, proAng, level, this, game);
            game.add(projectile, getX(), getY());
            projectile.movePolar(size, proAng);
            if (!isCannon) projectile.movePolar(size*5, proAng);
            new Thread(projectile).start(); // start the animation
            aimLine.setVisible(isShown);
        }
    }

    /** show explosion */
    private void explode() {
        removeAll(); // remove the projectile

        // draw an explosion
        GStar explosion = new GStar(45, 10);
        add(explosion);
        explosion.setFilled(true);
        explosion.setColor(Color.RED);
        pause(500);
        removeAll();
    }
}