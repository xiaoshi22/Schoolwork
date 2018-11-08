import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RandomIntegerContainerTest.
 *
 * @author  Xiaoshi Wang
 * @version 02/06/2017
 */
public class RandomIntegerContainerTest
{
    /**
     * Default constructor for test class RandomIntegerContainerTest
     */
    public RandomIntegerContainerTest()
    {

    }

    @Test
    public void addToFront1()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        randomIn1.addToFront(5);

        Integer[] a = {5};

        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToFront2()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        randomIn1.addToFront(12);

        Integer[] a = {12};

        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToFront3()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        for (int i = 0; i<3; i++){
            randomIn1.addToFront(i+1);
        }

        Integer[] a = {3, 2,1};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToFront4()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // we add the numbers to front one by one
        randomIn1.addToFront(2);
        randomIn1.addToFront(8);
        randomIn1.addToFront(9);
        randomIn1.addToFront(1);

        Integer[] a = {1, 9, 8, 2};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToBack1()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        for (int i = 0; i<3; i++){ // test addtoback in a for loop
            randomIn1.addToBack(i+1);
        }
        Integer[] a = {1, 2, 3};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToBack2()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // we add the numbers to back one by one
        randomIn1.addToBack(3);
        randomIn1.addToBack(4);
        randomIn1.addToBack(1);
        randomIn1.addToBack(2);
        randomIn1.addToBack(9);

        Integer[] a = {3, 4, 1, 2, 9};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToBack3()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();

        randomIn1.addToBack(0); // test what happen if there is only one elements

        Integer[] a = {0};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToBack4()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // test for two elements
        randomIn1.addToBack(2);
        randomIn1.addToBack(7);

        Integer[] a = {2, 7};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addSorted1()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        for (int i = 0; i<3; i++){
            randomIn1.addToBack(i+1); // we first make a list of {1, 2, 3}
        }

        randomIn1.addSorted(2); // then we addSort 2 in the list

        Integer[] a = {1, 2, 2, 3};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addSorted2()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // test what happen for multiple addSorted
        randomIn1.addSorted(7);
        randomIn1.addSorted(2);
        randomIn1.addSorted(5);

        Integer[] a = {2, 5, 7};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addSorted3()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // add sorted for one element
        randomIn1.addSorted(1);
        Integer[] a = {1};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addSorted4()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // for four elements
        randomIn1.addSorted(6);
        randomIn1.addSorted(5);
        randomIn1.addSorted(4);
        randomIn1.addSorted(2);

        Integer[] a = {2, 4, 5, 6};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addSorted5()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // eight elements
        randomIn1.addSorted(12);
        randomIn1.addSorted(2);
        randomIn1.addSorted(5);
        randomIn1.addSorted(1);
        randomIn1.addSorted(1);
        randomIn1.addSorted(3);
        randomIn1.addSorted(5);
        randomIn1.addSorted(18);

        Integer[] a = {1, 1, 2, 3, 5, 5, 12, 18};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void insertionSort1()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // insertion sort an unsorted array
        randomIn1.addToBack(3);
        randomIn1.addToBack(10);
        randomIn1.addToBack(1);
        randomIn1.addToBack(6);

        randomIn1.insertionSort();

        Integer[] a = {1, 3, 6, 10};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void insertionSort2()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();

        for (int i = 0; i<3; i++){
            randomIn1.addToBack(i+1);
        }
        // sort a sorted array
        randomIn1.insertionSort();

        Integer[] a = {1, 2, 3};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void insertionSort3()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // what happen if we insertion sort "nothing"
        randomIn1.insertionSort();

        Integer[] a = {};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void insertionSort4()
    {
        RandomIntegerContainer randomIn1 = new RandomIntegerContainer();
        // last test for insertion sort
        randomIn1.addToFront(3);
        randomIn1.addToFront(10);
        randomIn1.addToFront(1);
        randomIn1.addToFront(6);

        randomIn1.insertionSort();

        Integer[] a = {1, 3, 6, 10};
        assertArrayEquals(randomIn1.getArray(),a);
    }
}