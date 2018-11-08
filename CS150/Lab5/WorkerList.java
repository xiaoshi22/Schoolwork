import java.util.ArrayList;

/**
 * a class WorkerList extends SortedList. printData method should
 * be overwrite in this class to print all elements to an array
 * in the list in order.
 * 
 * @author Xiaoshi Wang
 * @version Feb 27, 2017
 */
public class WorkerList extends SortedList
{
    // instance variables 
    /**
     * Constructor for objects of class WorkerList
     */
    public WorkerList()
    {
        data = new ArrayList<Worker>();
    }

    public Worker[] printData(){
        Worker[] array = new Worker[data.size()];
        data.toArray(array); // transform abstractList into array

        return array;
    }
}
