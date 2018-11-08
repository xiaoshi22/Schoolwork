
/**
 * an class Worker which extend the Person class and implement 
 * the Comparable interface. Besides firstName and lastName,
 * we also include ID. Methods to get ID isincluded in this 
 * class.
 * 
 * @author Xiaoshi Wang
 * @version Feb 27, 2017
 */
public  class Worker extends Person implements Comparable <Worker>
{
    private long iD;

    /**
     * Constructor for objects of class Worker
     */
    public Worker(String firstName, String lastName, long iD)
    {
        // initialise instance variables
        super(firstName, lastName);
        this.iD = iD;
    }

    /** return the worker's ID */
    public long getID(){
        return iD;
    }

    /** overwrite the compareTo method to compare the IDs when compare two workers */
    public int compareTo(Worker w){
        if (this.getID() < w.getID()) {
            return -1;
        } else if  (this.getID() > w.getID()) {
            return 1;
        } else {
            return 0;
        }
    }
}