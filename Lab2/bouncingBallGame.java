import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;

/**
 * bouncingBallGame.java <p>
 * 
 * 
 * This class creates a red ball  bouncing in the window and the players should 
 * moves a blue ball in the bottom to prevent the red ball. <p>
 * 
 * 
 *Testing of the hypothesis: 
 *In this program, I test my design by simply running the game. I found several problems in my design 
 *after running it:
 *1) My buleBall moved only after I pressed the mouse, which is different from what happens in the sample. 
 *As written in my design, I used the methods mousePressed, mouseDragged and mouseReleased to control the 
 *blue ball in my design. Then, I change the methods to mouseMoved, which could provide a movement of 
 *blueBall whenever the mouse moves. (And I did not realize that the blueBall remained inside window even if 
 *the mouse is outside in the window. Thus, I add two if statement to always keep the blueBall in window.)
 *
 *2) Also, the setDisplay method alone could not help me to count the score as I expected. The counting process 
 *is much more complicated than I thought. I should first set an instance variable with initial value of 0. 
 *Then put the addition process in the if statement when two balls are collision. 
 *
 *3) When I was lost, I could still move the blueBall by my mouse. Thus, to help my mouseMoved method (and some 
 *labels) to distinguish whether game is over or not, I need to introduce a new Boolean variable gameIsOver to 
 *list what the program should do after game is over. (My condition for while statement in Run should also change.)
 *
 *4) In the comment on Moodle of my design, Professor Liew pointed out that the “checkCollison” should be in 
 *oneTimeStep instead of mouseDragged because I actually check the overlap in the animation of the bouncingBall 
 *while checking collision.
 *
 *5) My bouncingBall sometimes “vibrates” at the edge of window because the bouncingBall still remain outside the 
 *window after reflecting angle. Professor Xia provide a solution in his lecture: reset the position of the 
 *bouncingBall before reflecting angle and let the bouncingBall just touch the wall. <p>
 * 
 * 
 * @author: Xiaoshi Wang
 * I work with: Hanlu Gao
 */
public class bouncingBallGame extends GraphicsProgram {
    // initial size of the window
    public static int 
    APPLICATION_WIDTH = 600,
    APPLICATION_HEIGHT = 600;

    // constants
    private static final double
    SIZE = 50,//radius of the blue ball or half of the radius of the bouncing ball
    DELAY = 30; // delay between two consecutive frames

    // instance variables 
    private GOval bouncingBall;//the bouncing ball at the center of window
    private GOval blueBall; // the blue ball at the bottom of window
    private GLabel start;//the initial label wrriten "click to start!"
    private GLabel score;//the label to display score
    private GLabel lost;//the label to show that you are lost
    private int count = 0;//initial score is 0
    boolean gameIsOver = false;//game is not "over" initiallym
    private double width, height; // width, height of the window
    private double // initial speed and angle of the buncing ball
    speed = 20,
    angle = 65;

    /** the run method, control the animation */
    public void run() {
        drawGraphics(); // draw the inital graphics
        addMouseListeners();
        waitForClick(); // wait for a mouse click to start the animation
        start.setVisible(false);// hide the start label
        score.setVisible(true);// score label are displayed
        while (!gameIsOver) {// use a while-loop to run the animation only when game is not over
            oneTimeStep(); // do the work in a one time step
            pause(DELAY); // set the pause between two frames     
        }
    }

    /** move the ball when the mouse moves */
    public void mouseMoved(GPoint point) {
        if(gameIsOver) // if game is over, we would not operate the next line about setLocation
            return; 

        double x = point.getX();
        //if mouse outside the window in the left,
        if (x > width-2*SIZE ){
            x = width - 2*SIZE;//fix the blueBall inside the window
        }
        //if mouse outside the window in the right,
        if (x < 0){
            x=0;//fix the blueBall inside the window
        }

        blueBall.setLocation(x, blueBall.getY());// the new location of the blue ball 
    }  

    /**initially draw the background, two balls and three labels*/
    private void drawGraphics() {
        width = getWidth(); // width of the window
        height = getHeight(); // height of the window

        // draw the boundary
        add(new GRect(0,0,width-1,height-1)); 

        // draw a bouncing ball
        bouncingBall = new GOval(width/2-SIZE/2, height/2-SIZE/2, SIZE, SIZE);
        bouncingBall.setFilled(true);
        bouncingBall.setFillColor(Color.RED);
        add(bouncingBall);

        // draw a blue ball at the bottom of window
        blueBall = new GOval(width/2-SIZE, height-SIZE, SIZE*2, SIZE*2);
        blueBall.setFilled(true);
        blueBall.setFillColor(Color.BLUE);
        add(blueBall);

        // draw the initial label wrriten "click to start!"
        start = new GLabel("Click to start!", width/2, 40);
        start.setFont(new Font("Sanserif", Font.BOLD, 20));
        start.move(-start.getWidth()/2, start.getHeight());
        add(start);

        // draw a label to display the score
        score = new GLabel("score = 0", width/2, 40);
        score.setFont(new Font("Sanserif", Font.BOLD, 20));
        score.move(-score.getWidth()/2, score.getHeight());
        score.setVisible(false);
        add(score);

        // draw a label to show that you are lost
        lost = new GLabel("You are lost!", width/2, 40);
        lost.setFont(new Font("Sanserif", Font.BOLD, 20));
        lost.move(-lost.getWidth()/2, lost.getHeight());
        lost.setColor(Color.red);
        lost.setVisible(false);
        add(lost);
    }

    /**in each time step, move the ball and check for collision*/
    private void oneTimeStep() {
        bouncingBall.movePolar(speed, angle); // move the ball
        checkCollision(); // check for collision
    }

    /**check for collision */ 
    private void checkCollision() {
        /* check for collision between the bouncing ball and the boundary */
        // first, calculate the x, y-coodinates of the bouncing ball
        double x = bouncingBall.getX(); 
        double y = bouncingBall.getY();
        // if the ball hits the left wall, 
        if (x < 0) {
            bouncingBall.setLocation(0,y);//move the bouncingBall so that it just touches the wall
            angle = 180-angle; // reflect angle along the y-axis
        }
        // if the ball hits the right wall, 
        if (x+SIZE > width){
            bouncingBall.setLocation(width-SIZE,y);//move the bouncingBall so that it just touches the wall
            angle = 180-angle;// reflect angle along the y-axis
        }
        // if the ball hits the top wall, 
        if (y < 0) {
            angle = -angle; // reflect angle along the x-axis
        }
        //if the ball passes the bottom wall,
        if (bouncingBall.getY() > height){
            gameIsOver();
        } 

        /* check for collision between two balls */
        double dist = getDistance(blueBall, bouncingBall);// first calculate the distance between two balls
        double overlap = SIZE/2+SIZE - dist; // amount of overlap between two balls
        if (overlap > 0) { // two balls collide
            // angle from center of centerBall to center of bouncingBall
            double angle = getAngle(blueBall, bouncingBall);
            // move bouncingBall away to so that they just touch each other
            bouncingBall.movePolar(overlap, angle);
            // new angle of bouncingBall
            // a more realistic way to calculate the angle
            this.angle = 2*(angle+90)-this.angle;
            count ++;//add one more to the pervious score
            updateLabel();//display the scorce on the label
            speed = 1.03*speed;//increase the speed by 3 percent after each collsions 
        }

        //if the ball passes the bottom wall,
        if (bouncingBall.getY() > height){
            gameIsOver();
        } 
    }

    /**update the score label*/
    private void updateLabel() {
        score.setLabel ("score = " +count);// the score is added one more for each time
    }

    /**define what happen after the player is lost*/
    private void gameIsOver (){
        gameIsOver = true;
        score.setVisible(false);// let the score label be invisible
        lost.setVisible(true);// display the lost label
        lost.setLabel("You lost! Score = " +count);// display the final score
    }

    /**distance between centers of two balls*/
    private double getDistance(GOval ball1, GOval ball2) {
        return GMath.distance(ball1.getX()+ball1.getWidth()/2,// why ball 1 ball 2 ??????????????????????
            ball1.getY()+ball1.getWidth()/2,
            ball2.getX()+ball2.getWidth()/2, 
            ball2.getY()+ball2.getWidth()/2);
    }

    /**angle from center of ball1 to center of ball2*/
    private double getAngle(GOval ball1, GOval ball2) {
        return GMath.angle(ball1.getX()+ball1.getWidth()/2, 
            ball1.getY()+ball1.getWidth()/2,
            ball2.getX()+ball2.getWidth()/2, 
            ball2.getY()+ball2.getWidth()/2);
    } 
}