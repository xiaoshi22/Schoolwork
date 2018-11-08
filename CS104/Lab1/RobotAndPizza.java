import acm.graphics.*; // import the Java ACM graphic components
import acm.program.*; // import the Java ACM programs
import java.awt.*; // import the Java Abstract Window Toolkit (AWT)
import java.awt.event.*; // import the tools for handling mouse events

/**
 * RobotAndPizza.java <p>
 *
 * This program creates a  robot and a piece of pizza on the screen 
 * and let the robot interact with the user controlled mouse. <p>
 * @author:Xiaoshi Wang 
 * I work with: Hanlu Gao, Tianman Huang
 */

public class RobotAndPizza extends GraphicsProgram {
    // instance variable
    GArc arc; 
    GLine lineL;
    GLine lineR;
    GOval ovalL;
    GOval ovalR;
    GLabel label;// the arc, lineL, lineR, ovalL, ovalR and label will be accessed in multiple methods
    
    
    /** create a robot and a piece of pizza on the screen */
    public void init() {
        //add a new line as the left wire
        lineL = new GLine(300, 100, 220, 20);
        lineL.setColor(Color.BLACK);
        add(lineL);
        
        //add anthor new line as the right wire
        lineR = new GLine(300, 100, 380, 20);
        lineR.setColor(Color.BLACK);
        add(lineR);
        
        // add a new square as the face
        GRect rect = new GRect(200, 100, 200, 200);
        rect.setFilled(true);
        rect.setColor(Color.BLACK);
        rect.setFillColor(Color.BLUE);
        add(rect);
        
        // add a new circle on the left as one eye
        ovalL = new GOval(210, 120, 60, 60);
        ovalL.setFilled(true);
        ovalL.setColor(Color.BLACK);
        ovalL.setFillColor(Color.BLACK);
        add(ovalL);
        
        // add a new circle on the right as the other eye
        ovalR = new GOval(330, 120, 60, 60);
        ovalR.setFilled(true);
        ovalR.setColor(Color.BLACK);
        ovalR.setFillColor(Color.BLACK);
        add(ovalR);
        
        //add a new rectangle as the nose
        GRect nose = new GRect(297, 180, 6, 40);
        nose.setFilled(true);
        nose.setColor(Color.BLACK);
        add(nose);
        
        // add a new rectangle as the mouse
        GRect mouse = new GRect(230, 240, 140, 40);
        mouse.setFilled(true);
        mouse.setColor(Color.BLACK);
        mouse.setFillColor(Color.WHITE);
        add(mouse);
        
        // add a new arc as the pizza
        arc = new GArc(300, 120, 400, 160, 0, 55);
        arc.setFilled(true);
        arc.setFillColor(Color.RED);
        arc.setColor(Color.BLACK);
        add(arc);
        
        // add three new ovals as the dots on the pizza 
        GOval ovalA = new GOval(565, 170, 27, 15);
        ovalA.setFilled(true);
        ovalA.setColor(Color.YELLOW);
        add(ovalA);
        
        GOval ovalB = new GOval(600, 150, 27, 15);
        ovalB.setFilled(true);
        ovalB.setColor(Color.YELLOW);
        add(ovalB);
        
        GOval ovalC = new GOval(640, 165, 27, 15);
        ovalC.setFilled(true);
        ovalC.setColor(Color.YELLOW);
        add(ovalC);
        
        //add a new rectangle as the crust
        GRect crust = new GRect(500, 200, 200, 6);
        crust.setFilled(true);
        crust.setColor(Color.BLACK);
        crust.setFillColor(Color.ORANGE);
        add(crust);
        
        //add a new label 
        label = new GLabel("Gimme the pizza!", 250, 265);
        label.setColor(Color.RED);
        label.setVisible(false);
        add(label);
        
        //tell the program to pay attention to mouse events
        addMouseListeners();
    }
    
    
    /** handles the mouse event when mouse enters the window */
    public void mouseEntered(MouseEvent e) {
       ovalL.setFillColor(Color.YELLOW);//change the color of the left eye
       ovalR.setFillColor(Color.YELLOW);// change the color of the right eye
    }
    
    
    /** handles the mouse event when mouse exits the window */
    public void mouseExited(MouseEvent e) {
        ovalL.setFillColor(Color.BLACK);
        ovalR.setFillColor(Color.BLACK);
    }
    
    
    /** handles the mouse event when mouse is pressed */
     public void mousePressed(GPoint point) { 
        if (arc.contains(point)) { // if the mouse is pressed inside the arc
            label.setVisible(true);//the label turns visible
        } else {
            ovalL.setFillColor(Color.RED);//change the color of the left eye
            ovalR.setFillColor(Color.RED);// change the color of the right eye
            lineL.setColor(Color.RED);// change the color of the left wire
            lineR.setColor(Color.RED);//change the color of the right wire
    } 
    }
         
    
    /** handles the mouse event when mouse is released */
    public void mouseReleased(GPoint pt) {
        ovalL.setFillColor(Color.YELLOW);//change the color of the left eye
       ovalR.setFillColor(Color.YELLOW);// change the color of the right eye
       lineL.setColor(Color.BLACK);// change the color of the left wire
       lineR.setColor(Color.BLACK);//change the color of the right wire
       label.setVisible(false);//the label turns invisible
    }
}
    
    
    
    

