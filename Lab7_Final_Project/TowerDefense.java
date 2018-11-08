import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * TowerDefense.java <p>
 * 
 * For this project, I will write a tower-defense game. The player defends her 
 * base by adding defensive weapons to the tower. The weapons can be purchased 
 * with the credits, which were earned by hitting and killing the enemies. Once 
 * added, the weapons can be upgraded with the credits. The goal is to defend 
 * the base and kill the boss.  <p>
 * 
 * @author: Xiaoshi Wang
 */
public class TowerDefense extends GraphicsProgram {
    // initial size of the window
    public static int 
    APPLICATION_WIDTH = 1200,
    APPLICATION_HEIGHT = 600;

    // constants

    // instance variables 
    private double width, height; // width and height of the window
    private RandomGenerator rand = RandomGenerator.getInstance(); // a random generator
    private GRect[][] cell = new GRect[2][5]; // an array of cells on the left
    private Turtle[] turtle = new Turtle [120]; // an array of turtles
    private Turtle boss; // the boss
    private GRect cannonBox; // the cannon box on the top right
    private GRect gunBox; // the gun box on the top right
    private boolean cannonBoxIsOpen = false; // the cannon box is open when the mouse clicked
    private boolean gunBoxIsOpen = false; // the gun box is open when the mouse clicked
    private GImage icon; // the icon dragged by the mouse
    private Weapon[][] weapon = new Weapon[2][5]; // an array of weapons on cells 
    private boolean[][] hasWeapons = new boolean[2][5]; // an array of boolean to record a certain cell has a weapon or not

    private boolean isUpgradable = false;
    private int score = 300; // the totoal score the player has
    private int credit = 0; // credit left after all purchases
    private int upgradeCost = 300; // upgrade cost doubled after each updation
    private int weaponCost = 200; // weapon cost doubled after each purchase
    private boolean pause = false; // pause if  press space
    private boolean isShown = false; // aim line is not shown
    private boolean bossIsComing = false; // boss is not coming at the begining

    private GLabel creditLabel; // label showing the credit
    private GLabel upgradeLabel; // label showing the upgrade cost
    private GLabel weaponLabel; // label showing the weapon cost
    private GLabel label; // the label in the middle
    private GLabel tutorialLabel; // the tutorial label on the top left conner
    private GLabel stopLabel; // a label shown when pause

    /** the run method, draw the inital graphics */
    public void run(){
        addKeyListeners(); // call the keyboard to listen
        drawGraphics(); // call draw graphics method
    }

    /** create the icon when mouse is pressed */
    public void mousePressed(GPoint point) {
        if (cannonBox.contains(point)) {
            // create the cannon icon when cannon box is clicked
            if (cannonBox.getFillColor() == Color.GREEN) {
                cannonBoxIsOpen = true; // set connonBoxIsOpen be true so we can distingish when mouseReleased
                icon = new GImage("cannonIcon.gif"); 
                icon.setSize(45, 35);
                add(icon, point.getX(), point.getY());
            } 
        } else if (gunBox.contains(point)) {
            // create the gun icon when gun box is clicked
            if (gunBox.getFillColor() == Color.GREEN){
                gunBoxIsOpen = true; // the same reasoning as cannonBoxIsOpen
                icon = new GImage("gunIcon.gif");
                icon.setSize(45, 35);
                add(icon, point.getX(), point.getY());
            }
        } 

        // if weapons are upgradable and we press the level bar of a certain weapon, we upgrade this weapon
        if (!isUpgradable) return;
        for (int i=0; i<2; i++){
            for (int j=0; j<5; j++) { 
                if (cell[i][j].contains(point) && hasWeapons[i][j] && weapon[i][j].getLevel()< 2) {
                    weapon[i][j].setLevel(weapon[i][j].getLevel()+1);
                    updateUpgradeLabel();
                    isUpgradable = false;
                }
            }
        }
    }

    /** set the icon move along with the mouse when mouse is dragged */
    public void mouseDragged(GPoint point) {
        if (cannonBoxIsOpen || gunBoxIsOpen) {
            icon.setLocation(point.getX(), point.getY());
        } 
    }

    /** draw a cannon or gun in the cell we reach when mouse is realsed */
    public void mouseReleased(GPoint point) {
        if (icon != null) icon.setVisible(false); // set icon invisible whenever the mouse is relased
        for (int i=0; i<2; i++){
            for (int j=0; j<5; j++) { 
                if (cell[i][j].contains(point) && !hasWeapons[i][j]) { // if mouse is released in this cell and no weapons in the cell before

                    if (cannonBoxIsOpen){
                        weapon[i][j] = new Weapon(true, 60, this); // draw a cannon
                        add(weapon[i][j], i*height/4+50, height/12+j*height/6+50);
                        new Thread(weapon[i][j]).start(); // animate the weapon
                        hasWeapons[i][j] = true;
                    }  else if (gunBoxIsOpen){
                        weapon[i][j] = new Weapon(false, 10, this); // draw a gun
                        add(weapon[i][j], i*height/4+50, height/12+j*height/6+50);
                        new Thread(weapon[i][j]).start(); // animate the weapon
                        hasWeapons[i][j] = true;
                    }
                    updateWeaponLabel();
                } 
            }
        }
        cannonBoxIsOpen = false; // close the box
        gunBoxIsOpen = false; // close the box
    }

    /** pause when we press Space, shown aimLine wehn we press A */
    public void keyPressed(KeyEvent e){
        // press up botton, rotate
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            pause = !pause;
            tutorialLabel.setVisible(!pause);    
            stopLabel.setVisible(pause);
        }

        // press left botton, shift left
        if(e.getKeyCode() == KeyEvent.VK_A){
            isShown = !isShown;
            for (int i=0; i<2; i++){
                for (int j=0; j<5; j++) { 
                    if (hasWeapons[i][j]) {
                        weapon[i][j].setIsShown(isShown);
                    }
                }
            }
        }
    }

    /** check collision of projectile with the ground and turtles */
    public void checkCollision(Projectile projectile) {
        // if the prrojectile intersects the ground
        if (projectile.getY() > height*11/12) { 
            projectile.die();
            return;
        }

        // if the projectile intersects the boss
        if (projectile.getBounds().intersects(boss.getBounds())){
            if (projectile.getProLevel()>0) { // create three more projectile when the level > 0
                int proLevel = projectile.getProLevel()-1;
                double x = projectile.getX();
                double y = projectile.getY()-20;
                Weapon thisWeapon = projectile.getWeapon();

                createThreeProjectiles(x, y, proLevel, thisWeapon);
            }
            int bonus = (int)(projectile.getHeight()/2); // get the bonus
            projectile.die();
            boss.deduceCount(bonus); // reduce the blood by 5

            score= score +bonus; // add 5 to our score
            updateCreditLabel(bonus); // update the credit label}

            return;
        }

        // if  the projectile intersects the turtles
        for (int i = 0; i < 40; i++) {
            if (projectile.getBounds().intersects(turtle[i].getBounds())){ // if the projectile intersects the turtle
                if (projectile.getProLevel()>0) {
                    int proLevel = projectile.getProLevel()-1;
                    double x = projectile.getX();
                    double y = projectile.getY()-10;
                    Weapon thisWeapon = projectile.getWeapon();

                    createThreeProjectiles(x, y, proLevel, thisWeapon);
                }

                int bonus = (int)(projectile.getHeight()/2);
                projectile.die();
                turtle[i].deduceCount(bonus); // reduce the blood by bonus

                score= score +bonus; // add bonus to our score
                updateCreditLabel(bonus); // update the credit label}
                // if we are not at the end of the game, start animation of two more turtles for each 50 increase in score
                if (!bossIsComing){
                    if (score%50==0 && score < 2750) { // animate two more turtle
                        int k = credit/50;
                        new Thread(turtle[k+10]).start(); 
                        new Thread(turtle[k+11]).start();  
                    } else if (score > 2750) { // animate the boss
                        new Thread(boss).start(); 
                        bossComes();
                    }
                }
            } 
        }
    }

    /** check collision of turtle with the cell array */
    public void checkCollision(Turtle turtle) {
        // if turtle hits the second column cells, kill all the weapons on the first column
        if (turtle.getX() < 250) {
            for (int i = 0; i < 5; i++) {
                if (hasWeapons[1][i]){
                    weapon[1][i].die();
                    hasWeapons[1][i]=false;
                    weaponCost = weaponCost/2;
                }
            }

            // if turtle hits the first column cells, kill all the weapons on the second column
            if (turtle.getX() < 100){
                for (int i = 0; i < 5; i++) {
                    if (hasWeapons[0][i]){
                        weapon[0][i].die();
                        hasWeapons[0][i]=false;
                        weaponCost = weaponCost/2;
                    }
                }

                // if turtle hits the left bounary, you lose
                if (turtle.getX() < 0) {
                    gameOver();
                }
            }
            // upgrade the weapon label
            credit = credit + weaponCost;
            weaponCost = weaponCost/2;
            updateWeaponLabel();
        }
    }
        
    /** stop the animation of all objects */
    public boolean getPause(){
        return pause;
    }

    /** called in OneTimeStep method in Weapon class to return the location of the first turtle to adjust our aimmLine  */
    public double firstTurtle(){
        int firstTurtle = 0; 
        // compare the location of all turtle with that of the first one
        for (int i=0; i<120; i++){
            if (turtle[i].getX() < turtle[firstTurtle].getX())
                firstTurtle = i; 
        }
        double x = turtle[firstTurtle].getX()+150; 
        if (x >width) x = width+100;
        return x; // return the location
    }

    /** called in oneTimeStep method in Turtle class to reset its location if no blood left */
    public void resetLocation(Turtle turtle){
        turtle.setLocation(turtle.getX()+width, turtle.getY());
    }
    
    /** we win the game */
    public void youWin() {
        label.setLabel("YOU WIN");
        label.setVisible(true);
        pause = true;
    }
    
    /** initially draw the labels, ground, cells, turtles, weapons, boxes and trees */
    private void drawGraphics() {
        width = getWidth(); // width of the window
        height = getHeight(); // height of the window

        // draw the labels
        creditLabel = new GLabel("Credit: 0");
        add(creditLabel, 950, 15);

        upgradeLabel = new GLabel("Upgrade cost: 300");
        add(upgradeLabel, 950, 30);

        weaponLabel = new GLabel("Weapon cost: 200");
        add(weaponLabel, 950, 45);

        // draw a final label
        label = new GLabel("YOU LOSE!");
        label.setFont(new Font("Sanserif", Font.BOLD, 50));
        label.setColor(Color.RED);
        label.move(width/2-label.getWidth()/2, height/3);
        label.setVisible(false);
        add(label);

        tutorialLabel = new GLabel("When aviliable, drag weapon to empty base or click weapon to upgrade");
        tutorialLabel.setColor(Color.RED);
        tutorialLabel.move(10,20);
        add(tutorialLabel);

        GLabel aimLineLabel = new GLabel("Press A to show the aim lines and press Space to pause");
        aimLineLabel.setColor(Color.RED);
        aimLineLabel.move(10,40);
        aimLineLabel.setVisible(true);
        add(aimLineLabel);

        stopLabel = new GLabel("Press Space to Start");
        stopLabel.setFont(new Font("Sanserif", Font.BOLD, 40));
        stopLabel.setColor(Color.RED);
        stopLabel.move(width/2-label.getWidth()/2, height/3);
        stopLabel.setVisible(false);
        add(stopLabel);

        // draw the green ground
        GRect ground = new GRect(width, height/12);
        ground.setFilled(true);
        ground.setFillColor(Color.GREEN);
        add(ground, 0, height*11/12);

        // use for loop to draw grass
        for (int i=0; i< width; i = i+3){
            GLine grass = new GLine(i, rand.nextDouble(height*11/12-15, height*11/12-7), i,  height*11/12);
            add(grass);
            grass.setColor(Color.GREEN);
        }

        // draw an array of rectangles on the left
        for (int i=0; i<2; i++){
            for (int j=0; j<5; j++) { 
                hasWeapons[i][j] = false;
                cell[i][j] = new GRect(height/6, height/6);
                cell[i][j].setFilled(true);
                cell[i][j].setFillColor(rand.nextColor());
                add(cell[i][j], i*height/4, height/12+j*height/6); 
            }
        }

        // draw an array of 120 turtles 
        for (int i=0; i<120; i++){
            turtle[i] = new Turtle(false, rand.nextDouble(1, 2.2), this);
            add(turtle[i], rand.nextDouble(width, 2*width)+50, 11*height/12-turtle[i].getHeight()/2);
        }

        // draw the boss
        boss = new Turtle(true, 0.2, this);
        add(boss, width+150, 11*height/12-boss.getHeight()/2);

        // start the animation of first 10 turtles
        for (int i=0; i<10; i++){
            new Thread(turtle[i]).start(); 
        }

        // draw two weapons initially in the cells
        weapon[1][2] = new Weapon(false, 10, this);
        add(weapon[1][2], height/4+50, height/12+2*height/6+50);
        new Thread(weapon[1][2]).start(); // animate the zombie
        hasWeapons[1][2] = true;

        weapon[1][3] = new Weapon(true, 60, this);
        add(weapon[1][3], height/4+50, height/12+3*height/6+50);
        new Thread(weapon[1][3]).start(); // animate the zombie
        hasWeapons[1][3] = true;

        // draw two rectangles as weapons boxes and add the images of weapons on the top
        cannonBox = new GRect(55, 50);
        cannonBox.setFilled(true);
        cannonBox.setFillColor(Color.RED);
        add(cannonBox, 8*width/9, 0);

        gunBox = new GRect(55, 50);
        gunBox.setFilled(true);
        gunBox.setFillColor(Color.RED);
        add(gunBox, 8*width/9+65, 0);

        GImage cannonIcon = new GImage("cannonIcon.gif");
        cannonIcon.setSize(45, 35);
        add(cannonIcon, 8*width/9+5, 8);

        GImage gunIcon = new GImage("gunIcon.gif");
        gunIcon.setSize(45, 35);
        add(gunIcon, 8*width/9+70, 8);

        //draw four trees
        Tree tree1 = new Tree(200, 90, new Color(0,0,0), rand);
        add(tree1, width/2, height*11/12);

        Tree tree2 = new Tree(300, 90, new Color(0,0,0), rand);
        add(tree2, width/2 + width/6+50, height*11/12);

        Tree tree3 = new Tree(200, 90, new Color(0,0,0), rand);
        add(tree3, width/2 + width/3+50, height*11/12);

        Tree tree4 = new Tree(550, 90, new Color(0,0,0), rand);
        add(tree4, width+50, height*11/12);
    }

    /**update the credit label*/
    private void updateCreditLabel(int bonus) {
        credit = credit + bonus;
        creditLabel.setLabel ("Credit: " +credit);// update the label when credit is changed

        // boxes are green if we can afford weapons
        if (credit >= weaponCost) {
            cannonBox.setFillColor(Color.GREEN);
            gunBox.setFillColor(Color.GREEN);
        }

        // the next level bars for all weapons turn green, if we can afford upgrade cost
        if (credit >= upgradeCost) {
            isUpgradable = true;
            for (int i=0; i<2; i++){
                for (int j=0; j<5; j++) { 
                    if(hasWeapons[i][j]) {
                        weapon[i][j].upgradableNow();
                    }
                }
            }
        }
    }

    /**update the upgrade cost labels*/
    private void updateUpgradeLabel() {
        credit = credit - upgradeCost;
        upgradeCost = upgradeCost *2;
        updateColor();
        upgradeLabel.setLabel ("Upgrade cost: " + upgradeCost);// update the label when weapon cost is changed
    }

    /**update the weapon cost labels*/
    private void updateWeaponLabel() {
        credit = credit - weaponCost;
        weaponCost = weaponCost *2;
        updateColor();
        weaponLabel.setLabel ("Weapon cost: " + weaponCost);// update the label when weapont cost is changed
    }

    /** update the color of boxes and level bar after each pauchasion */
    private void updateColor() {
        for (int i=0; i<2; i++){
            for (int j=0; j<5; j++) {
                if(hasWeapons[i][j]) {
                    weapon[i][j].updateLevelBar();
                }
            }
        }

        cannonBox.setFillColor(Color.RED);
        gunBox.setFillColor(Color.RED);
    }

    /** call in checkCollision method to create two more projectiles when a projectile hits a turtle */
    private void createThreeProjectiles(double x, double y, int proLevel, Weapon weapon){
        double speed = weapon.getSpeed()/2;
        double size = weapon.returnSize();

        Projectile projectileRight = new Projectile(size*2/3, speed, 45, proLevel, weapon, this); // create a projectile
        add(projectileRight, x, y); // add to the location of the original projectile
        new Thread(projectileRight).start(); // start the animation

        Projectile projectileMid = new Projectile(size*2/3, speed, 90, proLevel, weapon, this); // create a projectile
        add(projectileMid, x, y); // add to the location of the original projectile
        new Thread(projectileMid).start(); // start the animation

        Projectile projectileLeft = new Projectile(size*2/3, speed, 135, proLevel, weapon, this); // create a projectile
        add(projectileLeft, x, y); // add tothe location of the original projectile
        new Thread(projectileLeft).start(); // start the animation
    }

    /** labels to warm boss is coming */
    private void bossComes(){
        bossIsComing = true;
        label.setLabel("BOSS IS COMING...");
        label.setVisible(true);
        pause(1000);
        label.setVisible(false);
    }

    /** game is over */
    private void gameOver() {
        label.setLabel("YOU LOSE");
        label.setVisible(true);
        pause = true;
    }
}