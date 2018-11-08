import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * MineSweeper.java
 * 
 * What to do?
 * 1. In this lab, we will design a game for players to explore and fine mines 
 *    in a field. The initial graph of this game is a 10*10 grid with 10 percent 
 *    of mines.
 * 2. The player can click their left button to open the cover and click their 
 *    right button to flag a cell. The hint would be shown to indicate how many 
 *    cells around this cell have mines. 
 * 3. If the player left clicks a cell with a mine, the player losses. And the player 
 *    need to flag all mines and open any cells else to win the game.
 * 4. When the player left clicks the empty cell (with hint = 0), the cell automatically 
 *    “explores” further until it “hits” the boundary made by hints.
 * 
 * How to do?
 * 1. We use the drawGraphics method to draw the initial graph by for loop. The mines 
 *    are randomly assigned. 
 * 2. In the mousePressed method, we call open method in MineCell class while the left 
 *    button is clicked; we call flag method when the right button is clicked.
 * 3. Loss method is called when the cell we left click has mine. And win method is called 
 *    when all cells with mine are flaged. 
 * 4. The explore method in main class is called in the open method in MineCell. Since we 
 *    call the open method in explore method, there is a recursive, which means we call a 
 *    method inside itself.
 *    
 *    
 * Test of Hypothesis
 * 1. When I tried to compile my code at the first time, I met a null point exception in the 
 *    drawGraphics method. After using the terminal and debugger I realized that I forgot to 
 *    define my instance variable rand of the  random generator.
 * 2. After I fixed the last problem, I figured out that my hint number are all 0. This is because 
 *    I didn’t update the hint label in my Mine Cell class. 
 * 3. The location of the cells and the hint labels inside the cells are not correct. I changed the 
 *    location to the right place after careful calculation. 
 * 4. While I was testing my program by playing it, I found that I could still open the cell with a 
 *    flag by left click, which is different from the original edition. Thus, I add one more condition, 
 *    a cell without a flag, to where I call my open method.
 * 5. I added the restart method by removing all and calling the draw graphics method once again. <p>
 
 * 
 * @author: Xiaoshi Wang
 */
public class MineSweeper extends GraphicsProgram {
    // initial size of the window
    public static int 
    APPLICATION_WIDTH = 600,
    APPLICATION_HEIGHT = 625;

    //constants
    private static final int
    ROW = 10, // number of rows of bricks
    COL = 10; // number of columns of bricks
    double SIZE = 60; // size of the side of each cell

    // instance variables
    private MineCell[][] cell = new MineCell[ROW][COL]; // array of cells
    private boolean gameOver = false; // game is not over initially
    private RandomGenerator rand = RandomGenerator.getInstance(); // a random generator
    private GLabel finalLabel; // the label shown when game is over

    /** the run method, call drawGraphics method to create the grid */
    public void run() {
        drawGraphics();//call drawGraphics method
        addMouseListeners();
    }

    /** open the cover when left buttom is pressed, put a flag when right buttom is pressed */
    public void mousePressed(MouseEvent e){
        if (gameOver) {
            restart();
            return;
        }
        double x = e.getPoint().getX();
        double y = e.getPoint().getY();
        // use a for loop to check all cells
        for (int i=0; i<ROW; i++){
            for (int j=0; j<COL; j++) {
                // if this cell contains the point and is not opened
                if (!cell[i][j].isOpened() && cell[i][j].contains(x,y)){
                    if (e.getButton() == MouseEvent.BUTTON1) {// when left buttom is pressed
                        if (cell[i][j].isFlagged()) return;
                        cell[i][j].open();// we call open method in MineCell to open (and explore if it is empty)
                    } else{// when right buttom is pressed 
                        cell[i][j].flag();// we call flag method in MineCell 
                    }
                    checkWin();// then check whether we've won or not by checkWin
                }
            }
        }
    }

    /** draw the initial graphics */
    private void drawGraphics() {
        // use a nested for-loop to create the grid of minecells
        for (int i=0; i<ROW; i++){
            for (int j=0; j<COL; j++) {
                cell[i][j] = new MineCell(SIZE, i, j, rand.nextInt(10)== 0, this);//draw a cell
                add(cell[i][j],SIZE/2+j*SIZE, SIZE/2+i*SIZE);//add the cell 
            }
        }

        //calculate the hint for each cell
        for (int i=0; i<ROW; i++){
            for (int j=0; j<COL; j++) {
                if (cell[i][j].hasMine()){// if the cell has mine
                    for (int k=-1; k<=1; k++){
                        for(int l=-1; l<=1; l++){ // use a for-loop for all nine cells around this cell
                            // if the cell is in the bound (check in isInBound method)
                            if (isInBound (i+k, j+l))
                                cell[i+k][j+l].increaseHint();// call the increaseHint method in MineCell to increase the hint by 1
                        }
                    }
                }
            }
        }

        //draw the final label in the middle of the window and hide it
        finalLabel = new GLabel("YOU WIN!", getWidth()/2, getHeight()/2);
        finalLabel.setFont(new Font("Sanserif", Font.BOLD, 50));
        finalLabel.setColor(Color.RED);
        finalLabel.move(-finalLabel.getWidth()/2, -finalLabel.getHeight()/2);
        finalLabel.setVisible(false);
        add(finalLabel);
    }

    /** open the neighbors of a given cell */
    public void explore(int row, int col){
        for (int k=-1; k<=1; k++){
            for(int l=-1; l<=1; l++){ // use a for-loop for all nine cells around this cell
                if (isInBound (row+k, col+l) && !cell[row+k][col+l].isOpened() && !cell[row+k][col+l].isFlagged())// if the cell is in the bound and is not opened
                    cell[row+k][col+l].open(); // call open method to open it
            }
        }
    }

    /** called in MousePressed method to check whether we win or not */
    private void checkWin(){
        for (int i=0; i<ROW; i++){
            for (int j=0; j<COL; j++) {
                if (cell[i][j].hasMine()) {// if the cell has mine,
                    if (!cell[i][j].isFlagged()) return;// return when we find some cell has not been flagged yet
                } else {// else (if the cell has no mine),
                    if (!cell[i][j].isOpened()) return;// return when we find some cell has not beem opened yet
                }
            }
        }
        win();// call win method now
    }

    /** a helper method called when we win */
    private void win(){
        gameOver = true;// set game is over
        finalLabel.setLabel("YOU WIN!");// set the final label to "YOU WIN!" and visible
        finalLabel.setVisible(true);
    }

    /** check whether the given row and column numbers is in our array or not */
    private boolean isInBound (int i, int j){
        return i>=0 && i<ROW && j>=0 && j<COL;// return true or false by comparing i and j with ROW and COL
    }

    /** a helper method called when we loss */ 
    public void loss() {
        gameOver = true; // set game is over
        finalLabel.setLabel("YOU LOSS!");// set the final label to "YOU LOSS!" and visible
        finalLabel.setVisible(true);
    }

    /** a helper method called to restart */ 
    private void restart() {
        gameOver = false;
        removeAll();
        drawGraphics();
    }
}
