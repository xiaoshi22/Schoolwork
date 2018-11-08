import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * MineCell.java
 * 
 * Model mine cells with mines or hints in our MineSweeper game
 * 
 * In this class, we create a constructor for a cell. Then, we return serveral 
 * varibles for the use in main class and write the flag method. The flag method 
 * helps us to switch from a cell flagged to unflagged. Our open method is also 
 * in this class. We do different things when the cell has mine or hint = 0 or
 * hint>0. 
 *
 * @author: Xiaoshi Wang
 */
public class MineCell extends GCompound {
    // instance variables 
    private double size;
    private int row, col; // the location of the cell 
    private boolean hasMine; // the cell either has mine or doesn't have mines
    private MineSweeper game; // the main class
    private int hint = 0; // hint for the number of mines around this cell
    private GLabel hintLabel; // a label to display the hint
    private GRect cover; // gray cover when the cell does not open
    private GPolygon flag; // a flag to show that the cell has mine
    private boolean opened = false; // the cell is opened or not (initially the cell is not opened)
    private boolean flagged = false; // the cell is flagged or not (initially there is no flags)

    /** a Constructor to create a cell and save the parameters */
    public MineCell(double size,int row, int col, boolean hasMine, MineSweeper game) {
        // save the parameters
        this.size = size;
        this.row = row;
        this.col = col;
        this.hasMine = hasMine;
        this.game = game;

        // create the cover
        cover = new GRect(size, size);
        cover.setFilled(true);
        cover.setFillColor(Color.GRAY);
        cover.setVisible(true);
        add(cover, -size/2, -size/2);

        double flagSize = size*0.3;
        // create the flag and hide it
        flag = new GPolygon();
        flag.addVertex(0, 0);
        flag.addVertex(flagSize/2, -flagSize/4);
        flag.addVertex(0, -flagSize/2);
        flag.addVertex(0, flagSize/2);
        flag.addVertex(-flagSize/2, flagSize/2);
        flag.addVertex(flagSize/2, flagSize/2);
        flag.addVertex(0, flagSize/2);
        flag.setFilled(true);
        flag.setFillColor(Color.RED);
        flag.setVisible(false);
        add(flag);

        // if hint is greater than 0, create the hintLabel and hide it
        hintLabel = new GLabel(""+hint);
        hintLabel.setFont(new Font("Sanserif", Font.BOLD, 25));
        hintLabel.setColor(Color.BLACK);
        hintLabel.move(-hintLabel.getWidth()/2, hintLabel.getHeight()/2);
        hintLabel.setVisible(false);
        add(hintLabel);
    }

    /** return hasMine */
    public boolean hasMine(){
        return hasMine;// return hasMine
    }

    /** increase the hint by one more */
    public void increaseHint(){
        hint ++;// increase the hint by one more
        hintLabel.setLabel(""+hint);// update the label
    }

    /** open the cell and explore if the cell is empty */
    public void open() {
        if (hasMine){// if the cell has mine
            explode();// explode and call loss method of game
            game.loss();
        }else { 
            cover.setFillColor(Color.WHITE);// set cover color to whte and boolean opened to true
            opened = true;
            if (hint == 0) {
                game.explore(row, col);// call explore method in main class
            } else {
                hintLabel.setVisible(true);// show the hintLabel
            }
        }
    }

    /** return opened */
    public boolean isOpened(){
        return opened; // return opened
    }

    /** return flagged */
    public boolean isFlagged(){
        return flagged; // return flagged
    }

    /** switch either from showing a flag to not showing a flag, or in the other direction */
    public void flag(){
        flagged = !flagged; // switch from flag to unflagged or flagged
        flag.setVisible(flagged); // set the flag visible or invisible
    }

    /** if the cell with mine is clicked, the cell explodes */
    private void explode(){
        GPolygon explosion = new GPolygon();
        GPen pen = new GPen(0, 0); // use a pen to trace the vertices
        double radius = size*0.3; // the radius of our explosion
        // use a loop to add the vertices
        for (int i = 0; i < 10; i++) {
            // add a point on the outer circle
            pen.setLocation(0, 0);
            pen.movePolar(radius, 90+i*360/10);
            explosion.addVertex(pen.getX(), pen.getY());

            // add a point on the inner circle
            pen.setLocation(0, 0);
            pen.movePolar(radius/2, 90+i*360/10+180/10);
            explosion.addVertex(pen.getX(), pen.getY());
        }
        add(explosion); // add the explosion
        explosion.setFilled(true);
        explosion.setFillColor(Color.RED);
    }
}
