import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class BinarySearchTreeTest.
 *
 * @author Xiaoshi Wang
 * @version March 19, 2017
 */
public class BinarySearchTreeTest
{
    /**
     * Default constructor for test class BinarySearchTreeTest
     */
    public BinarySearchTreeTest()
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
    public void insert1()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        int a = binarySe1.root.left.element;
        assertEquals(33, a);
    }
    
    @Test
    public void insert2()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        binarySe1.insert(22);
        int a = binarySe1.root.left.left.element;
        assertEquals(22, a);
    }
    
    @Test
    public void contains1()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        binarySe1.insert(22);
        assertEquals(true, binarySe1.contains(33));
    }
    
    @Test
    public void contains2()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        binarySe1.insert(22);
        assertEquals(false, binarySe1.contains(91));
    }
    
    @Test
    public void remove1()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        binarySe1.remove(42);
        assertEquals(false, binarySe1.contains(42));
    }
    
    @Test
    public void remove2()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        assertEquals(false, binarySe1.remove(29));
    }
    
    @Test
    public void findMax1()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        int a =  binarySe1.findMax();
        assertEquals(42,a);
    }
    
     @Test
    public void findMax2()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(49);
        binarySe1.insert(29);
        binarySe1.insert(67);
        binarySe1.insert(1);
        binarySe1.insert(39);
        binarySe1.insert(9);
        binarySe1.insert(100);
        int a =  binarySe1.findMax();
        assertEquals(100, a);
    }
    
    @Test
    public void findMin1()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        int a =  binarySe1.findMin();
        assertEquals(33, a);
    }
    
     @Test
    public void findMin2()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(49);
        binarySe1.insert(29);
        binarySe1.insert(67);
        binarySe1.insert(1);
        binarySe1.insert(39);
        binarySe1.insert(9);
        binarySe1.insert(100);
        int a =  binarySe1.findMin();
        assertEquals(1, a);
    }
    
    @Test
    public void empty()
    {
         BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(42);
        binarySe1.insert(33);
        binarySe1.empty();
        assertEquals(true, binarySe1.isEmpty());
    }
    
     @Test
    public void inOrderList1()
    {
        BinarySearchTree<Integer> binarySe1 = new BinarySearchTree<Integer>(49);
        binarySe1.insert(29);
        binarySe1.insert(67);
        binarySe1.insert(1);
        binarySe1.insert(39);
        binarySe1.insert(9);
        binarySe1.insert(100);
        
        
        ArrayList<Integer> a =  binarySe1.inOrderList();
        assertEquals(1, (int)a.get(0));
        assertEquals(9, (int)a.get(1));
        assertEquals(29, (int)a.get(2));
        assertEquals(39, (int)a.get(3));
        assertEquals(49, (int)a.get(4));
        assertEquals(67, (int)a.get(5));
        assertEquals(100, (int)a.get(6));
    }
}

