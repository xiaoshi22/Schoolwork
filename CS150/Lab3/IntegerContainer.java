import java.util.AbstractList;

/**
 * build up the parent class for both LinkedList and ArrayList. In this
 * class, Write the methods needed later by these two children clsses.
 * 
 * @author Xiaoshi Wang
 * @version Feb 13, 2017
 */
public class IntegerContainer
{
    // instance variables - replace the example below with your own
    protected AbstractList<Integer> data;

    /**
     * Constructor for objects of class IntegerContainer
     */
    public IntegerContainer()
    {

    }

    /** add a certain element to the front */
    public void addToFront(int z){
        data.add(0, z); // add it to the first position
    }

    /** add a certain element to the back */
    public void addToBack(int z){
        data.add(z); // add it to the last position
    }

    /** add a certain element in order */
    public void addSorted(int z){
        int i = data.size();
        if (i == 0){
            data.add(z); // if there is no element initially, add this number
        } else {
            for(; i >0 ; i--){ 
                if (z > data.get(i-1)){
                    break; // find a certain i such that z is barely larger than the value in i-1 position
                }
            }
            data.add(i, z);
        }
    }

    /** sorting a list of number by insertion sort */
    public void insertionSort(){
        for (int i = 1; i < data.size(); i++) {
            int temp = data.get(i);  
            int p = i;
            for(; p >0 && temp < data.get(p-1); p--)
                data.set(p, data.get(p-1)); // change the position of each elements that is larger than temp
            data.set(p, temp);
        }
    }

    /** linear search an element */
    public int linearSearch(int z){
        for (int i = 0; i < data.size(); i++){
            if (z == data.get(i)) return i; // if the ith data is the value we expect, return it
        }
        return -1;
    }

    /** binary search an element */
    public int binarySearch(int z){
        return search(0, data.size()-1, z); // call search method
    }

    private int search(int start, int end, int key){
        if (start >= end) return -1; // if we've search all nunmbers, the key is not in our list
        int mid = (start + end)/2;
        if (key < data.get(mid)) { // if the number is smaller than the middle
            return search (start, mid, key); // search the first half of it
        } else if (key > data.get(mid)) { // if the number is larger
            return search (mid+1, end, key); // serch the second half
        } else {
            return mid; // if the middle is the number, return the middle
        }
    }

    /** return original abstrctlist in the form of array */
    public Integer[] getArray(){
        Integer[] y = new Integer[data.size()];
        y = data.toArray(y); // transform abstractList into array
        return y;
    }   
}