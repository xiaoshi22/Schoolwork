import java.util.ArrayList;
import java.util.Arrays;

/**
 * an abstract class SortedList. Methods to add elements to form a 
 * list, merge sort and quick sort are included in this class.
 * 
 * @author Xiaoshi Wang
 * @version Feb 27, 2017
 */
public abstract class SortedList<E extends Comparable<E>>
{
    // instance variables
    protected ArrayList<E> data;

    /** add an element at the end of the list */
    public void addElement(E value){
        data.add(value); 
    }

    /** proceed the merge sort */
    public void mergeSort(){
        E[] tmpArray = (E []) new Comparable[data.size()]; 
        mergeSorting(tmpArray, 0, data.size()-1); // call mergeSorting
    }

    /** proceed the quick sort */
    public void quickSort(){
        quickSorting(data); // call quickSorting
    }

    /** print data for unit testing later, should be overwritted in children classes */
    public abstract <E> E[] printData();

    /** called in mergeSort */
    private void mergeSorting( E[] tmpA, int start, int end){
        if (start < end) {
            int mid = (start + end)/2; // find the middle 

            mergeSorting( tmpA, start, mid); // sort the first half
            mergeSorting( tmpA, mid+1, end); // sort the second half

            merge( tmpA, start, mid+1, end); // merge these two parts
        }
    }

    /** merge two parts of a list */
    private void merge( E[] tmpA, int leftPos, int rightPos, int rightEnd){
        int numElements = rightEnd - leftPos +1;
        int leftEnd = rightPos - 1; 
        int tmpPos = leftPos;

        // campare a element in the first and second parts, then select the smaller one and wirte it into tmp
        while (leftPos <= leftEnd && rightPos <= rightEnd){
            if(data.get(leftPos).compareTo(data.get(rightPos))<=0){
                tmpA[tmpPos++] = data.get(leftPos++); 
            }else{ 
                tmpA[tmpPos++] = data.get(rightPos++); 
            }
        }

        // if we have used up all elements in the second part, we directly copy all data in the first part to our tmp
        while (leftPos <= leftEnd){
            tmpA[tmpPos++] = data.get(leftPos++);
        }

        // if we have used up all elements in the first part, we directly copy all data in the second part to our tmp
        while (rightPos <= rightEnd){
            tmpA[tmpPos++] = data.get(rightPos++);
        }

        // copy the elements in tmp to data in the right position
        for( int i = 0; i < numElements; i++, rightEnd-- ) 
            data.set(rightEnd, tmpA[rightEnd]); 
    }

    /** called in mergeSort */
    private void quickSorting(ArrayList<E> items){
        if (items.size()>1){
            // create three empty list to store smller, same, and larger elements 
            ArrayList<E> smaller = new ArrayList<E>();
            ArrayList<E> same = new ArrayList<E>();
            ArrayList<E> larger = new ArrayList<E>();

            E chosenItem = items.get(items.size()/2);
            for (E i: items){
                if (i.compareTo(chosenItem)<0)
                    smaller.add(i);
                else if (i.compareTo(chosenItem)>0)
                    larger.add(i);
                else
                    same.add(i);
            }

            quickSorting(smaller); // sorting the smaller part
            quickSorting(larger); // sorting the middle part

            items.clear(); // clear our list and add the smaller, same and larger lists in order
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);
        }
    }
}