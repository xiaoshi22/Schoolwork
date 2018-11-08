import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class DirectedGraphTest.
 *
 * @author  Xiaoshi Wang
 * @version April 27, 2017
 */
public class DirectedGraphTest
{
    /**
     * Default constructor for test class DirectedGraphTest
     */
    public DirectedGraphTest()
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
    public void addNode1()
    {
        DirectedGraph<Integer> directed1 = new DirectedGraph<Integer>();

        directed1.addNode(1);
        ArrayList<Integer> list = directed1.getNodes();
        assertEquals(1, (int)list.get(0));
    }

    @Test
    public void addNode2()
    {
        DirectedGraph<Integer> directed1 = new DirectedGraph<Integer>();

        directed1.addNode(1);

        assertEquals(false, directed1.addNode(1));
    }

    @Test
    public void addNode3()
    {
        DirectedGraph<Integer> directed1 = new DirectedGraph<Integer>();

        directed1.addNode(1);
        directed1.addNode(1);

        assertEquals(false, directed1.addNode(1));
    }

    @Test
    public void addNode4()
    {
        DirectedGraph<Integer> directed1 = new DirectedGraph<Integer>();

        directed1.addNode(1);
        directed1.addNode(6);
        directed1.addNode(4);
        directed1.addNode(2);

        ArrayList<Integer> list = directed1.getNodes();

        assertEquals(true, list.contains(1));
        assertEquals(true, list.contains(6));
        assertEquals(true, list.contains(4));
        assertEquals(true, list.contains(2));
    }

    @Test
    public void addEdge1()
    {
        DirectedGraph<Integer> directed1 = new DirectedGraph<Integer>();

        assertEquals(false, directed1.addEdge(1, 2, 1));
    }

    @Test
    public void addEdge2()
    {
        DirectedGraph<Integer> directed1 = new DirectedGraph<Integer>();

        directed1.addNode(1);
        directed1.addNode(2);

        assertEquals(true, directed1.addEdge(1, 2, 1));

        ArrayList<Integer> list1 = directed1.getNeighbors(1);
        ArrayList<Integer> list2 = directed1.getNeighbors(2);

        assertEquals(0, list2.size());
        assertEquals(1, list1.size());
        assertEquals(2, (int)list1.get(0));
    }

    @Test
    public void addEdge3()
    {
        DirectedGraph<Integer> directed1 = new DirectedGraph<Integer>();

        directed1.addNode(1);
        directed1.addNode(2);
        directed1.addNode(3);

        assertEquals(true, directed1.addEdge(1, 2, 1));
        assertEquals(true, directed1.addEdge(1, 2, 1));

        directed1.addEdge(1, 3, 2);

        ArrayList<Integer> list1 = directed1.getNeighbors(1);

        assertEquals(2, list1.size());
        assertEquals(2, (int)list1.get(0));
        assertEquals(3, (int)list1.get(1));
    }
}