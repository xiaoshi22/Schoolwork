import java.util.LinkedList;
import java.util.Iterator;

/**
 * Create a class MyLinkedList which is a singly linked, 
 * non-circular list where each node only has one link 
 * next and the list has a head and a tail link. 
 * 
 * @author Xiaoshi
 * @version 2/20/2017
 */
public class MyLinkedList<AnyType> implements Iterable
{
    /** a class for Node */
    public static class Node<AnyType>{
        protected AnyType data; // data stored in this node
        protected Node<AnyType> next; // the next node linked

        /** constructor */
        public Node(AnyType d, Node<AnyType> n){
            data = d;
            next = n;
        }
    }

    // instance variables
    private Node<AnyType> beginMarker; // head of the linkedList
    private Node<AnyType> endMarker; // tail of the LinkedList
    private int size; // used to track the size 

    /** Constructor for objects of class MyLinkedList */
    public MyLinkedList() {
        // initialise instance variables
        clear(); // construct an empty linked List
        size = 0;
    }

    /** create a head and tail for an empty list */
    private void clear(){
        endMarker = new Node<AnyType>(null, null);
        beginMarker = new Node<AnyType>(null, endMarker);
    }

    /** initialize the position of our iterator at the head */
    public MyLinkedListIterator<AnyType> iterator(){
        return new MyLinkedListIterator<AnyType>(beginMarker);
    }

    /** add an element to the front */
    public void addFirst(AnyType value){
        Node firstNode = beginMarker.next; // find the first node after the bignMarker
        Node newNode = new Node(value, firstNode); // define our new node
        beginMarker.next = newNode; // link newNode after the beginMarker
        size++; // keep tracking the change in size
    }

    /** add an element to the front */
    public void addBack(AnyType value){ 
        endMarker.data=value; // set our endMarker as the new node
        Node newEndMarker = new Node(null, null); // add a new endMarker in the end
        endMarker.next =newEndMarker;
        endMarker = newEndMarker;
        size ++; // keep tracking the change in size
    }

    /** get the element given its index */
    public AnyType getElement(int index){
        Node nextElement = beginMarker.next; // start from the first element
        Node newNextElement;
        for (int i = 0; i < index; i++){ // go through all the elements until we meet the deseried index 
            Node oldNextElement = nextElement;
            nextElement = oldNextElement.next;
        }
        AnyType nextValue = (AnyType) nextElement.data; // find the value of this node
        return nextValue;
    }

    /** return the size */
    public int getSize(){
        return size;
    }
}