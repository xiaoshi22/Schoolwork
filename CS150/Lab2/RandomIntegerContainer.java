import java.util.ArrayList;

/**
 *  this class contains several methods for series of integers
 * to operate, including adding a number to front, back, sorted 
 * and sorting a series of numbers.
 * 
 * @author Xiaoshi Wang
 * @version 02/06/2017
 */
public class RandomIntegerContainer
{
    private ArrayList<Integer> x;

    /**
     * Constructor for objects of class RandomIntegerContainer
     */
    public RandomIntegerContainer(){
        x = new ArrayList<Integer>();
    }

    /** add a certain element to the front */
    public void addToFront(int z){
        x.add(0, z); // add it to the first position
    }

    /** add a certain element to the back */
    public void addToBack(int z){
        x.add(z); // add it to the last position
    }

    /** add a certain element in order */
    public void addSorted(int z){
        int i = x.size();
        if (i == 0){
            x.add(z); // if there is no element initially, add this number
        } else {
            for(; i >0 ; i--){ 
                if (z > x.get(i-1)){
                    break; // find a certain i such that z is barely larger than the value in i-1 position
                }
            }
            x.add(i, z);
        }
    }

    /** sorting a list of number by insertion sort */
    public void insertionSort(){
        for (int i = 1; i < x.size(); i++) {
            int temp = x.get(i);  
            int p = i;
            for(; p >0 && temp < x.get(p-1); p--)
                x.set(p, x.get(p-1)); // change the position of each elements that is larger than temp
            x.set(p, temp);
        }
    }

    /** return original arrayList in the form of array */
    public Integer[] getArray(){
        Integer[] y = new Integer[x.size()];
        y = x.toArray(y); // transform arrayList into array
        return y;
    }
}
