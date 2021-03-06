import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ArrayListIntegerContainerTest.
 *
 * @author  Xiaoshi Wang
 * @version Feb 13, 2017
 */
public class ArrayListIntegerContainerTest
{
    /**
     * Default constructor for test class ArrayListIntegerContainerTest
     */
    public ArrayListIntegerContainerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void addToFront1()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
        randomIn1.addToFront(5);

        Integer[] a = {5};

        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToFront2()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
        randomIn1.addToFront(12);

        Integer[] a = {12};

        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToFront3()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
        for (int i = 0; i<3; i++){
            randomIn1.addToFront(i+1);
        }

        Integer[] a = {3, 2,1};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToFront4()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
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
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
        for (int i = 0; i<3; i++){ // test addtoback in a for loop
            randomIn1.addToBack(i+1);
        }
        Integer[] a = {1, 2, 3};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToBack2()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
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
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        randomIn1.addToBack(0); // test what happen if there is only one elements

        Integer[] a = {0};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addToBack4()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
        // test for two elements
        randomIn1.addToBack(2);
        randomIn1.addToBack(7);

        Integer[] a = {2, 7};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addSorted1()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
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
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
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
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
        // add sorted for one element
        randomIn1.addSorted(1);
        Integer[] a = {1};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void addSorted4()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
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
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
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
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
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
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

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
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
        // what happen if we insertion sort "nothing"
        randomIn1.insertionSort();

        Integer[] a = {};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void insertionSort4()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();
        // last test for insertion sort
        randomIn1.addToFront(3);
        randomIn1.addToFront(10);
        randomIn1.addToFront(1);
        randomIn1.addToFront(6);

        randomIn1.insertionSort();

        Integer[] a = {1, 3, 6, 10};
        assertArrayEquals(randomIn1.getArray(),a);
    }

    @Test
    public void linearSearch1()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        for (int i = 0; i<8; i++){
            randomIn1.addToBack(i+1);
        }

        Integer[] n = {randomIn1.linearSearch(5)};
        Integer[] a = {4};
        assertArrayEquals(n ,a);
    }

    @Test
    public void linearSearch2()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        randomIn1.addToBack(4);
        randomIn1.addToBack(2);
        randomIn1.addToBack(6);
        randomIn1.addToBack(1);
        randomIn1.addToBack(8);
        randomIn1.addToBack(4);
        randomIn1.addToBack(2);
        randomIn1.addToBack(32);

        Integer[] n = {randomIn1.linearSearch(8)};
        Integer[] a = {4};
        assertArrayEquals(n ,a);
    }

    @Test
    public void linearSearch3()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        randomIn1.addToBack(2);
        randomIn1.addToBack(5);
        randomIn1.addToBack(6);
        randomIn1.addToBack(2);
        randomIn1.addToBack(8);
        randomIn1.addToBack(6);
        randomIn1.addToBack(6);

        Integer[] n = {randomIn1.linearSearch(2)};
        Integer[] a = {0};
        assertArrayEquals(n ,a);
    }

    public void linearSearch4()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        randomIn1.addToBack(2);
        randomIn1.addToBack(5);
        randomIn1.addToBack(6);
        randomIn1.addToBack(2);
        randomIn1.addToBack(8);
        randomIn1.addToBack(6);
        randomIn1.addToBack(6);

        Integer[] n = {randomIn1.linearSearch(9)};
        Integer[] a = {-1};
        assertArrayEquals(n ,a);

    }

    @Test
    public void binarySearch1()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        for (int i = 0; i<8; i++){
            randomIn1.addToBack(i+1);
        }

        Integer[] n = {randomIn1.binarySearch(5)};
        Integer[] a = {4};
        assertArrayEquals(n ,a);
    }

    @Test
    public void binarySearch2()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        randomIn1.addToBack(1);
        randomIn1.addToBack(2);
        randomIn1.addToBack(2);
        randomIn1.addToBack(4);
        randomIn1.addToBack(4);
        randomIn1.addToBack(6);
        randomIn1.addToBack(8);
        randomIn1.addToBack(32);

        Integer[] n = {randomIn1.binarySearch(8)};
        Integer[] a = {6};
        assertArrayEquals(n ,a);
    }

    @Test
    public void binarySearch3()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        randomIn1.addToBack(2);
        randomIn1.addToBack(2);
        randomIn1.addToBack(5);
        randomIn1.addToBack(6);
        randomIn1.addToBack(6);
        randomIn1.addToBack(6);
        randomIn1.addToBack(8);

        Integer[] n = {randomIn1.binarySearch(5)};
        Integer[] a = {2};
        assertArrayEquals(n ,a);
    }

    public void bianrySearch4()
    {
        ArrayListIntegerContainer randomIn1 = new ArrayListIntegerContainer();

        randomIn1.addToBack(2);
        randomIn1.addToBack(5);
        randomIn1.addToBack(6);
        randomIn1.addToBack(2);
        randomIn1.addToBack(8);
        randomIn1.addToBack(6);
        randomIn1.addToBack(6);

        Integer[] n = {randomIn1.binarySearch(9)};
        Integer[] a = {-1};
        assertArrayEquals(n ,a);

    }
}