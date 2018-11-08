import java.util.ArrayList;

/**
 * a class ContactList extends SortedList. printData method should
 * be overwrite in this class to print all elements to an array
 * in the list in order.
 * 
 * @author Xiaoshi Wang
 * @version Feb 27, 2017
 */
public class ContactList extends SortedList
{
    /**
     * Constructor for objects of class ContactList
     */
    public ContactList()
    {
        data = new ArrayList<Contact>();
    }

    public Contact[] printData(){
        Contact[] array = new Contact[data.size()];
        data.toArray(array); // transform abstractList into array
        return array;
    }
}