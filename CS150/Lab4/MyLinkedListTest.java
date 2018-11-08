import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MyLinkedListTest.
 *
 * @author  Xiaoshi Wang
 * @version 2/20/2017
 */
public class MyLinkedListTest
{
    /**
     * Default constructor for test class MyLinkedListTest
     */
    public MyLinkedListTest()
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
    public void addFirst1()
    {
        MyLinkedList myLinked1 = new MyLinkedList();

        myLinked1.addFirst('a');
        assertEquals(myLinked1.getElement(0), 'a');
        assertEquals(myLinked1.getSize(), 1);
    }

    @Test
    public void addFirst2()
    {
        MyLinkedList myLinked1 = new MyLinkedList();

        myLinked1.addFirst("Test");
        myLinked1.addFirst("Unit");
        myLinked1.addFirst("More");

        assertEquals(myLinked1.getElement(0), "More");
        assertEquals(myLinked1.getElement(1), "Unit");
        assertEquals(myLinked1.getElement(2), "Test");
        assertEquals(myLinked1.getSize(), 3);
    }
    
    @Test
    public void addFirst3()
    {
        MyLinkedList myLinked1 = new MyLinkedList();

        myLinked1.addFirst('a');
        myLinked1.addFirst('b');
        myLinked1.addFirst('c');

        assertEquals(myLinked1.getElement(0), 'c');
        assertEquals(myLinked1.getElement(1), 'b');
        assertEquals(myLinked1.getElement(2), 'a');
        assertEquals(myLinked1.getSize(), 3);
    }

    @Test
    public void addBack1()
    {
        MyLinkedList myLinked1 = new MyLinkedList();

        myLinked1.addBack('a');
        assertEquals(myLinked1.getElement(0), 'a');
        assertEquals(myLinked1.getSize(), 1);
    }

    @Test
    public void addBack2()
    {
        MyLinkedList myLinked1 = new MyLinkedList();

        myLinked1.addBack("More");
        myLinked1.addBack("Unit");
        myLinked1.addBack("Test");

        assertEquals(myLinked1.getElement(0), "More");
        assertEquals(myLinked1.getElement(1), "Unit");
        assertEquals(myLinked1.getElement(2), "Test");
        assertEquals(myLinked1.getSize(), 3);
    }
    
    @Test
    public void addBack3()
    {
        MyLinkedList myLinked1 = new MyLinkedList();

        myLinked1.addBack('a');
        myLinked1.addBack('b');
        myLinked1.addBack('c');

        assertEquals(myLinked1.getElement(0), 'a');
        assertEquals(myLinked1.getElement(1), 'b');
        assertEquals(myLinked1.getElement(2), 'c');
        assertEquals(myLinked1.getSize(), 3);
    }
}