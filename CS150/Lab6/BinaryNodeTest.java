
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class BinaryNodeTest.
 *
 * @author Xiaoshi Wang
 * @version March 19, 2017
 */
public class BinaryNodeTest
{
    /**
     * Default constructor for test class BinaryNodeTest
     */
    public BinaryNodeTest()
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
    public void size1()
    {
        BinaryNode<Integer> ltlt = new BinaryNode(25, null, null);
        BinaryNode<Integer> rtlt = new BinaryNode(35, null, null);
        BinaryNode<Integer> lt = new BinaryNode(33, ltlt, rtlt);
        BinaryNode<Integer> ltrt = new BinaryNode(46, null, null);
        BinaryNode<Integer> rt = new BinaryNode(50, ltrt, null);
        BinaryNode<Integer> BiN1 = new BinaryNode(42, lt, rt);

        assertEquals(1, ltlt.size());
    }

    @Test
    public void size2()
    {
        BinaryNode<Integer> ltlt = new BinaryNode(25, null, null);
        BinaryNode<Integer> rtlt = new BinaryNode(35, null, null);
        BinaryNode<Integer> lt = new BinaryNode(33, ltlt, rtlt);
        BinaryNode<Integer> ltrt = new BinaryNode(46, null, null);
        BinaryNode<Integer> rt = new BinaryNode(50, ltrt, null);
        BinaryNode<Integer> BiN1 = new BinaryNode(42, lt, rt);

        assertEquals(1, rtlt.size());
    }

    @Test
    public void size3()
    {
        BinaryNode<Integer> ltlt = new BinaryNode(25, null, null);
        BinaryNode<Integer> rtlt = new BinaryNode(35, null, null);
        BinaryNode<Integer> lt = new BinaryNode(33, ltlt, rtlt);
        BinaryNode<Integer> ltrt = new BinaryNode(46, null, null);
        BinaryNode<Integer> rt = new BinaryNode(50, ltrt, null);
        BinaryNode<Integer> BiN1 = new BinaryNode(42, lt, rt);

        assertEquals(3, lt.size());
    }

    @Test
    public void size4()
    {
        BinaryNode<Integer> ltlt = new BinaryNode(25, null, null);
        BinaryNode<Integer> rtlt = new BinaryNode(35, null, null);
        BinaryNode<Integer> lt = new BinaryNode(33, ltlt, rtlt);
        BinaryNode<Integer> ltrt = new BinaryNode(46, null, null);
        BinaryNode<Integer> rt = new BinaryNode(50, ltrt, null);
        BinaryNode<Integer> BiN1 = new BinaryNode(42, lt, rt);

        assertEquals(1, ltrt.size());
    }

    @Test
    public void size5()
    {
        BinaryNode<Integer> ltlt = new BinaryNode(25, null, null);
        BinaryNode<Integer> rtlt = new BinaryNode(35, null, null);
        BinaryNode<Integer> lt = new BinaryNode(33, ltlt, rtlt);
        BinaryNode<Integer> ltrt = new BinaryNode(46, null, null);
        BinaryNode<Integer> rt = new BinaryNode(50, ltrt, null);
        BinaryNode<Integer> BiN1 = new BinaryNode(42, lt, rt);

        assertEquals(2, rt.size());
    }

    @Test
    public void size6()
    {
        BinaryNode<Integer> ltlt = new BinaryNode(25, null, null);
        BinaryNode<Integer> rtlt = new BinaryNode(35, null, null);
        BinaryNode<Integer> lt = new BinaryNode(33, ltlt, rtlt);
        BinaryNode<Integer> ltrt = new BinaryNode(46, null, null);
        BinaryNode<Integer> rt = new BinaryNode(50, ltrt, null);
        BinaryNode<Integer> BiN1 = new BinaryNode(42, lt, rt);

        assertEquals(6, BiN1.size());
    }

    @Test
    public void returnPreOrder1()
    {
        BinaryNode<Integer> ltlt = new BinaryNode(25, null, null);
        BinaryNode<Integer> rtlt = new BinaryNode(35, null, null);
        BinaryNode<Integer> lt = new BinaryNode(33, ltlt, rtlt);
        BinaryNode<Integer> ltrt = new BinaryNode(46, null, null);
        BinaryNode<Integer> rt = new BinaryNode(50, ltrt, null);
        BinaryNode<Integer> BiN1 = new BinaryNode(42, lt, rt);

//         BiN1.printPreOrder();
// 		BiN1.clearList();
// 		BiN1.returnPreOrder();
//         ArrayList<Integer> a = BiN1.returnList();
//         System.out.println(a.size());
// 		int w1 = a.get(0);
// 		int w2 = a.get(1);
// 		int w3 = a.get(2);
// 		int w4 = a.get(3);
// 		int w5 = a.get(4);
// 		int w6 = a.get(5);
//         assertEquals(42, w1);
//         assertEquals(33, w2);
//         assertEquals(25, w3);
//         assertEquals(35, w4);
//         assertEquals(50, w5);
//         assertEquals(46, w6);
    }
}
