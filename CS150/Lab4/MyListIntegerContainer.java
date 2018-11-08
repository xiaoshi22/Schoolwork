/**
 * A class called MyListIntegerContainer which uses our 
 * new data structure. It only needs to implement the 
 * following methods: addToBack, addToFront, and Search 
 * for a value.
 * 
 * @author Xiaoshi
 * @version 2/20/2017
 */

public class MyListIntegerContainer
{
    // instance variables 
    protected MyLinkedList<Integer> list;

    /** Constructor for objects of class IntegerContainer */
    public MyListIntegerContainer(){
        list = new MyLinkedList<Integer>();
    }

    /** add a certain element to the front */
    public void addToFront(int z){
        list.addFirst(z); // add it to the first position
    }

    /** add a certain element to the back */
    public void addToBack(int z){
        list.addBack(z); // add it to the last position
    }

    /** linear search an element */
    public int search(int z){//One which uses an iterator and one which does not
        for (int i = 0; i < list.getSize(); i++){
            if (z == list.getElement(i)) return i; // if the ith data is the value we expect, return it
        }
        return -1;
    }

    /** search an element by iterator*/
    public int searchByIterator(int z){// this is the one by iterator
        MyLinkedListIterator<Integer> iterator = list.iterator();
        int value;
        
        for(int i=0; iterator.hasNext(); i++){ // if there are some nodes remaining
            value = iterator.next(); // find the next value
            if (value == z) {
                return i; // if the ith data is the value we expect, return it
            }
        }
        return -1;
    }

    /** return the value of our list for unit test */
    public int getNumber(int index){
        return list.getElement(index);
    }  
}