import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * a class MyLinkedListIerator that implements the Iterator 
 * interface.
 * 
 * @author Xiaoshi Wang
 * @version 2/20/2017
 */
public class MyLinkedListIterator<AnyType> implements Iterator {

    private MyLinkedList.Node<AnyType> theNode; // the node that the iterator currently at

    /** Constructor for objects of class MyLinkedListInerator */
    public MyLinkedListIterator(MyLinkedList.Node<AnyType> theNode)
    {
        // initialise instance variables
        this.theNode = theNode;
    }

    /** cheack whether the list has any nodes left or not */
    public boolean hasNext(){
        if (theNode.next.data!=null) {
            return true;
        } else {
            return false;
        }
    }

    /** return the value of next node */
    public AnyType next(){
        if(!hasNext()){ // check whether the next node exists
            throw new NoSuchElementException("No next element");
        }
        theNode =  theNode.next; // move the iterator to the next node
        AnyType nextValue= theNode.data; // read the value of this node
        return nextValue;
    }
}