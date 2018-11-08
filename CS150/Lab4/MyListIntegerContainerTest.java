import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MyListIntegerContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MyListIntegerContainerTest
{
    /**
     * Default constructor for test class MyListIntegerContainerTest
     */
    public MyListIntegerContainerTest()
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
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        myListIn1.addToFront(1);

        assertEquals(myListIn1.getNumber(0), 1);
    }

    @Test
    public void addToFront2()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        for (int i = 0; i<3; i++){
            myListIn1.addToFront(i);
        }

        assertEquals(myListIn1.getNumber(0), 2);
        assertEquals(myListIn1.getNumber(1), 1);
        assertEquals(myListIn1.getNumber(2), 0);

    }

    @Test
    public void addToFront3()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        myListIn1.addToFront(0);
        myListIn1.addToFront(1);
        myListIn1.addToFront(2);
        myListIn1.addToFront(4);
        myListIn1.addToFront(2);
        myListIn1.addToFront(5);

        assertEquals(myListIn1.getNumber(0), 5);
        assertEquals(myListIn1.getNumber(1), 2);
        assertEquals(myListIn1.getNumber(2), 4);
        assertEquals(myListIn1.getNumber(3), 2);
        assertEquals(myListIn1.getNumber(4), 1);
        assertEquals(myListIn1.getNumber(5), 0);
    }

    @Test
    public void addToBack1()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        myListIn1.addToBack(1);

        assertEquals(myListIn1.getNumber(0), 1); 
    }

    @Test
    public void addToBack2()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        for (int i = 0; i<3; i++){
            myListIn1.addToBack(i);
        }

        assertEquals(myListIn1.getNumber(0), 0);
        assertEquals(myListIn1.getNumber(1), 1);
        assertEquals(myListIn1.getNumber(2), 2);   
    }

    @Test
    public void addToBack3()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        myListIn1.addToBack(0);
        myListIn1.addToBack(1);
        myListIn1.addToBack(2);
        myListIn1.addToBack(4);
        myListIn1.addToBack(2);
        myListIn1.addToBack(5);

        assertEquals(myListIn1.getNumber(0), 0);
        assertEquals(myListIn1.getNumber(1), 1);
        assertEquals(myListIn1.getNumber(2), 2);
        assertEquals(myListIn1.getNumber(3), 4);
        assertEquals(myListIn1.getNumber(4), 2);
        assertEquals(myListIn1.getNumber(5), 5);
    }

    @Test
    public void search1()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        for (int i = 0; i<10; i++){
            myListIn1.addToBack(i+1);
        }

        assertEquals(myListIn1.search(0), -1); 
    }

    @Test
    public void search2()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        for (int i = 0; i<10; i++){
            myListIn1.addToBack(i+1);
        }

        assertEquals(myListIn1.search(5), 4); 
    }

    @Test
    public void search3()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        for (int i = 0; i<10; i++){
            myListIn1.addToBack(i+1);
        }

        assertEquals(myListIn1.search(10), 9); 
    }

    @Test
    public void search4()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        myListIn1.addToBack(0);
        myListIn1.addToBack(5);
        myListIn1.addToBack(3);
        myListIn1.addToBack(2);
        myListIn1.addToBack(2);
        myListIn1.addToBack(5);
        myListIn1.addToBack(2);
        myListIn1.addToBack(3);
        myListIn1.addToBack(4);
        myListIn1.addToBack(7);

        assertEquals(myListIn1.search(3),2); 
    }

    @Test
    public void searchByIterator1()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        for (int i = 0; i<10; i++){
            myListIn1.addToBack(i+1);
        }

        assertEquals(myListIn1.searchByIterator(11), -1); 
    }

    @Test
    public void searchByIterator2()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        for (int i = 0; i<10; i++){
            myListIn1.addToBack(i+1);
        }

        assertEquals(myListIn1.searchByIterator(5), 4); 
    }

    @Test
    public void searchByIterator3()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        for (int i = 0; i<10; i++){
            myListIn1.addToBack(i+1);
        }

        assertEquals(myListIn1.searchByIterator(10), 9); 
    }

    @Test
    public void searchByIterator4()
    {
        MyListIntegerContainer myListIn1 = new MyListIntegerContainer();

        myListIn1.addToBack(0);
        myListIn1.addToBack(5);
        myListIn1.addToBack(3);
        myListIn1.addToBack(2);
        myListIn1.addToBack(2);
        myListIn1.addToBack(5);
        myListIn1.addToBack(2);
        myListIn1.addToBack(3);
        myListIn1.addToBack(4);
        myListIn1.addToBack(7);

        assertEquals(myListIn1.searchByIterator(0),0); 
    }
}