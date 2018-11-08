import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;

/**
 * PeggleGame.java <p>
 * 
 * What does it do? (A short Introduction)
 * 1) Our simplified version of the popular Peggle game starts with a starry 
 *    background, a cannon on the top and pegs.
 * 2) The cannon can move around as we move our mouse.
 * 3) The pegs bounce all the time up and down. The red ones can explode into 
 *    three balls after a ball hits them.
 * 4) when we drag and press the mouse, a new line and a new ball is created 
 *    at the place of our mouse.
 * 5) The balls we've just created from the cannon can bounce the wall and the 
 *    pegs, and the pegs hitted by the ball explode and the balls explod while 
 *    they hit the bottom wall.
 * 6) We succeed if all the pegs explode. <p>
 * 
 * How?
 * 1) Draw the backgroud and objects in right classes.
 * 2) Use GCompond to draw the cannon so that the graph can move.
 * 3) Use loop draw our 100 pegs and speperately define blue and red pegs.
 * 4) We can simply change the end point of our line and add a now ball at the 
 *    tip.
 * 5) Check collision of balls with walls and pegs. If they collide, we let them 
 *    either change moving angles or explode.
 * 6) Set what would happen after we succeed and call it if all the pegs explode. <p>
 *
 * Test of Hypothesis:
 * 1) In my design, I didn’t include the two labels as my instance variables. So I met 
 *    some difficulty when I try to update the labels. I add them in my program
 * 2) Also, I didn’t know how to create two new projectiles when one is hits an explosive 
 *    ball. So I write a helper method in main class to create a new projectile with 
 *    certain angles, speed and location. Then, I call this method twice when I check 
 *    collision with pegs and figure that the peg is explosive.
 * 3) I add restart method to my original design. I restart the game by draw the 100 pegs 
 *    again and set the counter to be 0.
 * 4) I noticed that all my pegs move in the same direction with the same frequency but 
 *    different altitudes if I follow the original design. I solve it by adding a new 
 *    parameter int counter to Peg class. (In my design, I set the counter to be 0 initially 
 *    for all pegs.) Then, the initial values of the counter are different form pegs to pegs. 
 *    And the pegs would change their moving directions at different time and flash in 
 *    different patterns.
 * 5) In my design, the projectiles would still remain in the window after I restart the game. 
 *    So I want to remove them all when game is over. But I cannot just call the projectile.die(), 
 *    because the projectile will explode then. So I add an instance variable, Boolean isActive,  
 *    to check the status of the projectile in main class by an instance variable. (isActive is 
 *    like isAlive. But isActive is false when game is over, and isAlive is false when the 
 *    projectile hits the bottom wall.)  For an inactive projectile, we just remove it. <p>
 *
 * @auther: Xiaoshi Wang
 * I work with: Jiyang Ge and Hanlu Gao
 */
public class PeggleGame extends GraphicsProgram {
    // initial size of the window
    public static int 
    APPLICATION_WIDTH = 800,
    APPLICATION_HEIGHT = 800;

    // constants
    private static final double
    SPEED = 15, // speed limit of the pegs
    BASE_SIZE = 100, // base size
    CANNON_SIZE = BASE_SIZE * 0.6; //canon size

    // instance variables 
    private double width, height; // width and height od the window
    private GPolygon cannon; // the cannon 
    private GLine aimLine; // a line to aim the cannon
    private double cannonAngle = 0; // current angle of the cannon
    private RandomGenerator rand = RandomGenerator.getInstance(); // a random generator
    private Peg[] peg = new Peg[100]; // an array of 4 pegs
    private boolean gameOver = false; // set gameOver to be false initially
    private int count = 0; // initial shot fired is 0
    private GLabel score; // the label to display shots fired as its score
    private GLabel label; // the label to display the final shots fired when game is over

    /** the run method, draw the inital graphics */
    public void run() {
        drawGraphics(); // call draw graphics method
    }

    /** rotate the cannon as mouse moves */
    public void mouseMoved(GPoint point) { 
        if (gameOver) return; 
        if (cannon != null) {
            rotateCannonToPoint(point); // call rotate cannon to point method
        }
    }

    /** create a bouncy projectile when mouse is pressed */
    public void mousePressed(GPoint point) {
        if (gameOver){
            restart(); // if game is Over, call restart method
        } else {
            // the tip of the cannon is where the projectile appears
            GPoint cannonTip = getCannonTip();

            // the length of aimLine determines the speed of the projectile
            double lineLen = GMath.distance(aimLine.getStartPoint().getX(), 
                    aimLine.getStartPoint().getY(), 
                    aimLine.getEndPoint().getX(), 
                    aimLine.getEndPoint().getY());

            // create a projectile
            SpaceProjectile projectile = new SpaceProjectile(CANNON_SIZE/3, 0.05*lineLen,
                    getAngle(aimLine), this);
            add(projectile, cannonTip.getX(), cannonTip.getY()); // add to tip of cannon
            new Thread(projectile).start(); // start the animation
            count ++; // add one more to the pervious score
            updateLabel(); // call update label method to update the counters in both score label and final label
        }
    }

    /** check if projectile hits anything */
    public void checkCollision(SpaceProjectile projectile) {
        // bounce if hit left or right wall
        if (projectile.getX() < 0 ||
        projectile.getX() + projectile.getWidth()> width){
            projectile.setAngle(180-projectile.getAngle()); 
        }

        // kill the projectile if hit bottom wall
        if (projectile.getY() + projectile.getHeight()/2 > height) {
            projectile.setLocation(projectile.getX(), height-projectile.getHeight()/2);
            projectile.die(); // kill the projectile
        }

        // kill the peg if the projectile hit it
        for (int i = 0; i < 100; i++) {// check if projectile hits peg[i]
            if (peg[i].isAlive() && 
            projectile.getBounds().intersects(peg[i].getBounds())) {
                peg[i].die(); // kill the peg
                if (peg[i].isExplosive()) {
                    createProjectile(projectile.getX(), projectile.getY(), projectile.getSpeed(), -projectile.getAngle()-30);
                    createProjectile(projectile.getX(), projectile.getY(), projectile.getSpeed(), -projectile.getAngle()+30);
                }
                projectile.setAngle(-projectile.getAngle()); 
            }
        }

        // check each peg that whether it is alive or not
        for (int i = 0; i < 100; i++) {
            if (peg[i].isAlive()) return;
        }
        // if all pegs is not alive, game is over and we should deactivate the projectiles on the window
        gameOver(); // call gameOver method
        projectile.deactivate(); // call deactivate method in SpaceProjectile class to set the projectile inactive
    }

    /** rotate the cannon to aim at the point */
    private void rotateCannonToPoint(GPoint point) {
        // change the end point of the line
        aimLine.setEndPoint(point.getX(), point.getY());

        // calculate the current angle of the line
        double lineAngle = getAngle(aimLine); 

        // rotate the cannon by the difference 
        cannon.rotate(lineAngle - cannonAngle); 
        cannonAngle = lineAngle; // update the lastAngle
    }

    /** calculate the angle of the line */
    private double getAngle(GLine line) {
        return GMath.angle(line.getStartPoint().getX(), 
            line.getStartPoint().getY(), 
            line.getEndPoint().getX(), 
            line.getEndPoint().getY());
    }

    /** initially draw the background, the stars, and a cannon */
    private void drawGraphics() {
        width = getWidth(); // width of the window
        height = getHeight(); // height of the window

        setBackground(Color.BLACK); // set background
        drawStars(); // call drawStars method

        // draw a blue base in the center
        GOval base = new GOval(BASE_SIZE,BASE_SIZE);
        base.setFilled(true);
        base.setFillColor(Color.BLUE);
        add(base, getWidth()/2-BASE_SIZE/2, -BASE_SIZE/2);

        // draw a GPolygon as the cannon
        createCannon(CANNON_SIZE);
        add(cannon, getWidth()/2, 0);
        cannon.setFilled(true);
        cannon.setFillColor(Color.GREEN);

        // draw a line from center of star to mouse point
        // the mouse point is unknow at this time
        aimLine = new GLine(cannon.getX(), cannon.getY(), 
            cannon.getX(), cannon.getY());
        add(aimLine);
        aimLine.setColor(Color.red);

        // draw a cap on the top of the base of the canno
        GOval cannonCap = new GOval(BASE_SIZE/2,BASE_SIZE/2);
        cannonCap.setFilled(true);
        cannonCap.setFillColor(Color.YELLOW);
        add(cannonCap, getWidth()/2-BASE_SIZE/4, - BASE_SIZE/4);

        // draw a label to display the score
        score = new GLabel("Shots fired: 0", 3*width/4, 40);
        score.setFont(new Font("Sanserif", Font.BOLD, 20));
        score.setColor(Color.white);
        score.setVisible(true);
        add(score);

        // draw a final label
        label = new GLabel("Game Over! 0 shot fired. Click to restart!");
        label.setFont(new Font("Sanserif", Font.BOLD, 25));
        label.setColor(Color.WHITE);
        label.move(width/2-label.getWidth()/2, height/3);
        label.setVisible(false);
        add(label);

        // draw 100 pegs
        drawPegs(); // call drawPegs method
    }

    /** draw a polygon as the cannon */
    private void createCannon(double size) {
        cannon = new GPolygon();
        // add the four corners of the cannon
        cannon.addVertex(0, -size/4);
        cannon.addVertex(size, -size/6);
        cannon.addVertex(size, size/6);
        cannon.addVertex(0, size/4);
    }

    /** get the point at the tip of the cannon */
    private GPoint getCannonTip() {
        // use a pen to find the tip
        GPen pen = new GPen(cannon.getX(), cannon.getY());
        pen.movePolar(CANNON_SIZE, getAngle(aimLine));
        return new GPoint(pen.getX(), pen.getY());
    }

    /** draw randomly placed colorful stars */
    private void drawStars() {
        for (int i = 0; i < 800; i++) {
            // location of the star
            double x = rand.nextDouble(0, getWidth());
            double y = rand.nextDouble(0, getHeight());
            double size = rand.nextDouble(1,7); 

            // draw a star
            GOval star = new GOval(x, y, size, size);
            add(star);
            star.setFilled(true);
            star.setColor(rand.nextColor());
        }
    }

    /** draw 100 pegs */
    private void drawPegs() {
        // draw 100 pegs
        for (int i = 0; i < 100; i++) {
            boolean isExplosive = (i%10 == 0) ; // if i%10 == 0, we have isExplosive = true; if i%10 is not 0, we have isExplosive = false
            peg[i] = new Peg(rand.nextInt(0, 5), isExplosive, rand.nextDouble(SPEED/5, SPEED), this); // put parameters in the constructor
            add(peg[i], rand.nextDouble(0, width), rand.nextDouble(height/4, 3*height/4)); // add the peg 
            new Thread(peg[i]).start(); // animate the peg
        } 
    }

    /** call in checkCollision method two create two more projectiles when a projectile hits a explosive peg */
    private void createProjectile(double x, double y, double speed, double angle){
        SpaceProjectile projectile = new SpaceProjectile(CANNON_SIZE/3, speed, angle, this); // create a projectile
        add(projectile, x, y); // add to tip of cannon
        new Thread(projectile).start(); // start the animation
    }

    /**update the score label and final label*/
    private void updateLabel() {
        score.setLabel ("Shots fired: " +count);// the score is added one more for each time
        label.setLabel ("Game Over! " +count+ " shot fired. Click to restart!");// the score in the final label is added 
    }

    /** game is over, draw game over label */
    private void gameOver() {
        gameOver = true; // set gameOver be true
        label.setVisible(true); // display the final label in the window
    }

    /** restart the whole game */
    private void restart() {
        drawPegs(); // redraw the pegs 
        gameOver = false; // set gameOver be false which is our initial stage for gameOver
        label.setVisible(false); // hidden the final label
        count = 0; // set count back to 0
        updateLabel(); // update our count in the two label
    }
}