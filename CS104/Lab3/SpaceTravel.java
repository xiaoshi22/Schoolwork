import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;
import java.lang.System.*;

/**
 * The SpaceTravel game. 
 * 
 * It starts with three planets and two bouncing black holes on a background of a stary night.
 * Player drags the ice planet to push the iron planet, which in turn pushed the fire planet.
 * The goal is for the fire planet to reach the the destination sign. If the ice planet touches 
 * the fire planet, the game is over. If a black hole touches any planet, the game is over. <P>
 * 
 * Test of Hypothesis:
 *1) When I described the variables in the constructor of black holes, I directly copied from bouncing 
 *   Bell and mentioned size, speed, angle, color and game. However, since color and size are constant 
 *   from one black hole to the other, I got trouble in my main class. Thus, I reduced the variables to 
 *   speed, angle and game.
 *2) In the run method of my black hole class, I tried to call Boolean game is over in the main class but 
 *   failed. Since game is over is the instance variable in main class, I should add game dot before it. 
 *   Also, since game is over was private originally, which implies I can only use it in the current class, 
 *   I change it to public. 
 *3) I put too many labels in the design. Thus, I set the labels at the same place appeared in different time 
 *   as the same label. 
 *4) I thought push method is a defined method in ACM and I didn’t write codes for it, but it is not. Then, 
 *   I write it at this time.
 *5) I didn’t put the animation for black holes at first because I thought the animation came along with each 
 *   black hole. I add it in this time. <p>

 * @Author: Xiaoshi Wang
 */
public class SpaceTravel extends GraphicsProgram {
    //initial size of the window
    public static int 
    APPLICATION_WIDTH = 1000,
    APPLICATION_HEIGHT = 1000;

    private static final double 
    DIAMETER = 60;

    //instance variables
    private GOval ice, iron, fire; // three planets
    private GPoint lastPoint; // save the previous mouse point for the purpose of dragging
    private boolean isDragging = false; // indicate if the player is dragging the ice planet
    public boolean gameIsOver = false; // indicate if the game is over
    private GLabel startLabel; // the initial messgae on the screen
    private GLabel destLabel; // the destination sign
    private RandomGenerator rand = RandomGenerator.getInstance();

    /** set up the game */
    public void run() {
        drawGraphics();// call drawGraphics to draw initial graphics
    }

    /** if mouse is pressed on ince planet, start dragging */
    public void mousePressed(GPoint point) {
        if (gameIsOver) return;// if gameIsOver is true, return

        if (ice.contains(point)){// if pressed in the ice planet, set isDragging to true and save point to lastPoint 
            startLabel.setVisible(false);
            isDragging = true;
            lastPoint = point;
        } else {
            isDragging = false; 
        }
    }

    /** allow player to drag the ice planet and push other planets */
    public void mouseDragged(GPoint point) {
        if (gameIsOver) return;// if gameIsOver is true, return
        if (isDragging){// if isDragging is true, 
            ice.move(point.getX()-lastPoint.getX(),point.getY()-lastPoint.getY());// move the ice planet from lastPoint to point
            lastPoint = point;
        }

        double dist = getDistance(ice, fire);
        double overlap = DIAMETER - dist;
        // if the ice planet touched the fire planet, remove the ice planet and call gameOver
        if (overlap > 0){
            ice.setVisible(false);
            gameIsOver();
        }
        push(ice, iron);// call the push method to let the ice planet push the iron planet
        push(iron,fire);// call the push method to let the iron planet push the fire planet

        // if the fire planet reaches the destination sign, change the label to "Succeed" and call gameOver
        if(fire.getBounds().intersects(destLabel.getBounds())){
            destLabel.setLabel("Succeed");
            gameIsOver();
        }
    }

    /** called by a black hole to check if it collides with the boundary or with the planets */
    public void checkCollision(BlackHole blackHole) {
        double x = blackHole.getX(); 
        double y = blackHole.getY();
        double size = blackHole.getWidth(); // size of ball
        double angle = blackHole.getAngle(); // current angle of ball
        double width = getWidth();
        double height = getHeight();
        // if the black hole hits the left or the right wall, set angle = 180-angle
        if (x < 0) {
            blackHole.setLocation(0, y); // move to left edge
            blackHole.setAngle(180-angle); // reflect angle along the y-axis
        }
        if (x+size > width) {
            blackHole.setLocation(width - size, y); // move to right edge
            blackHole.setAngle(180-angle); // reflect angle along the y-axis
        }
        // if the black hole hits the top or the bottom wall, set angle = -angle
        if (y < 0) {
            blackHole.setLocation(x,  0); // move to top edge
            blackHole.setAngle(-angle); // reflect angle along the x-axis
        }
        if (y+size > height) {
            blackHole.setLocation(x, height - size); // move to bottom edge
            blackHole.setAngle(-angle); // reflect angle along the x-axis
        }
        // call the checkCollisionWithPlanet method to check if the black hole hits each of the three planets
        checkCollisionWithPlanet(blackHole, ice);
        checkCollisionWithPlanet(blackHole, iron);
        checkCollisionWithPlanet(blackHole, fire); 
    } 

    // check if a black hole and a planet collide
    private void checkCollisionWithPlanet(BlackHole blackHole, GOval planet) {
        // calculate the distance between the black hole and the planet
        double dist = GMath.distance(blackHole.getX()+blackHole.getWidth()/2, blackHole.getY()+blackHole.getWidth()/2, 
                planet.getX()+DIAMETER/2, planet.getY()+DIAMETER/2);
        // calculate the overlap between the black hole and the planet
        double overlap = blackHole.getWidth()/2 + DIAMETER/2-dist;
        // if overlap > 0, remove the planet and call gameOver
        if (overlap > 0){
            planet.setVisible(false);
            gameIsOver();
        }
    }

    // let one planet push another planet
    private void push(GOval pushingPlanet, GOval pushedPlanet) {
        // calculate the distance between the two planets
        double dist = getDistance(pushingPlanet, pushedPlanet);
        // calculate the overlap between the two planets
        double overlap = DIAMETER-dist;
        // if overlap > 0, 
        if(overlap > 0){
            // calculate the angle from pushingPlanet to pushedPlanet
            double angle = GMath.angle(pushingPlanet.getX()+DIAMETER/2, pushingPlanet.getY()+DIAMETER/2,
                    pushedPlanet.getX()+DIAMETER/2, pushedPlanet.getY()+DIAMETER/2);
            // move pushedPlanet in the above angle by an amount equal to overlap
            pushedPlanet.movePolar(overlap, angle);
            //System.out.println("overlap= "+overlap+" angle= "+angle);
        }
    }

    /** draw the initial graphics */
    private void drawGraphics() {
        double width = getWidth(); // width of the window
        double height = getHeight(); // height of the window

        setBackground(Color.BLACK);// set the background color

        // use a loop to draw randomly colored and randomly placed stars
        for (int i = 0; i < 800; i++) {
            // location of the star
            double x = rand.nextDouble(0, width);
            double y = rand.nextDouble(0, height);
            double size = rand.nextDouble(1,5); 
            //draw the star
            GOval star = new GOval(x, y, size, size);
            add(star);
            star.setFilled(true);
            star.setColor(rand.nextColor());
        }

        // draw three planets
        ice = new GOval(100, 100, DIAMETER, DIAMETER);
        add(ice);
        ice.setFilled(true);
        ice.setFillColor(Color.BLUE);

        iron = new GOval(100, 200, DIAMETER, DIAMETER);
        add(iron);
        iron.setFilled(true);
        iron.setFillColor(Color.GRAY);

        fire = new GOval(100, 300, DIAMETER, DIAMETER);
        add(fire);
        fire.setFilled(true);
        fire.setFillColor(Color.RED);

        //choose two random angles for black holes
        double angle1 = rand.nextDouble(0, 360);
        double angle2 = rand.nextDouble(0, 360);

        // create two blackholes and start their animation
        BlackHole blackHole1 = new BlackHole(1, angle1, this);
        add(blackHole1, width/2, height/2);
        new Thread(blackHole1).start();

        BlackHole blackHole2 = new BlackHole(1, angle2, this);
        add(blackHole2, 200, height/2);
        new Thread(blackHole2).start();

        // draw the labels
        startLabel = new GLabel("Click to start!", 200, 300);
        startLabel.setFont(new Font("Sanserif", Font.BOLD, 20));
        startLabel.setColor(Color.white);
        add(startLabel); 

        destLabel = new GLabel("distination", 600, 600);
        destLabel.setFont(new Font("Sanserif", Font.BOLD, 20));
        destLabel.setColor(Color.white);
        add(destLabel);
    }

    /** called when the game is over */
    private void gameIsOver() {
        gameIsOver = true;// set gameIsOver to true
        startLabel.setLabel("Lost!");// show a message in the label
        startLabel.setVisible(true);
    }

    /** a helper method, compute the distance from the centers of ice to fire*/
    private double getDistance(GOval planet1, GOval planet2) {
        return GMath.distance(planet1.getX()+DIAMETER/2, planet1.getY()+DIAMETER/2, 
            planet2.getX()+DIAMETER/2, planet2.getY()+DIAMETER/2);
    } 
}

