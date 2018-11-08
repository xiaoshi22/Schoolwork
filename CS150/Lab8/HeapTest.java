
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Stack;
import java.util.ArrayList;

/**
 * The test class HeapTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HeapTest
{
    /**
     * Default constructor for test class HeapTest
     */
    public HeapTest()
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

    //     @Test
    //     public void getDirections1()
    //     {
    //         Heap<Integer> heap1 = new Heap<Integer>(12);
    //         Stack<Boolean> dirs = heap1.getDirections(11);
    //         assertEquals(true, dirs.pop());
    //         assertEquals(false, dirs.pop());
    //         assertEquals(false, dirs.pop());
    //     }
    // 
    //     @Test
    //     public void getDirections2(){
    //         Heap<Integer> heap1 = new Heap<Integer>(12);
    //         Stack<Boolean> dirs = heap1.getDirections(9);
    //         assertEquals(true, dirs.pop());
    //         assertEquals(true, dirs.pop());
    //         assertEquals(false, dirs.pop());
    //         // assertEquals(true, dirs.pop());
    //     }

    @Test
    public void insert1()
    {
        Heap<Integer> heap1 = new Heap<Integer>(13);
        heap1.insert(21);
        heap1.insert(16);

        ArrayList<Integer> a = heap1.inOrderList();
        assertEquals(21, (int)a.get(0));
        assertEquals(13, (int)a.get(1));
        assertEquals(16, (int)a.get(2));
    }

    @Test
    public void insert2()
    {
        Heap<Integer> heap1 = new Heap<Integer>(13);
        heap1.insert(21);
        heap1.insert(16);
        heap1.insert(12);

        ArrayList<Integer> a = heap1.inOrderList();
        assertEquals(21, (int)a.get(0));
        assertEquals(13, (int)a.get(1));
        assertEquals(12, (int)a.get(2));
        assertEquals(16, (int)a.get(3));
    }

    @Test
    public void insert3()
    {
        Heap<Integer> heap1 = new Heap<Integer>(13);
        heap1.insert(21);
        heap1.insert(16);
        heap1.insert(24);
        heap1.insert(31);
        heap1.insert(19);
        heap1.insert(68);
        heap1.insert(63);
        heap1.insert(26);
        heap1.insert(32);
        heap1.insert(14);

        ArrayList<Integer> a = heap1.inOrderList();

        int[] b = {63, 24, 26, 14, 32, 21, 31, 13, 19, 16, 68};
        for (int i=0; i<11; i++){
            int ele = a.get(i);
            assertEquals(b[i], ele);
        }
    }

    @Test
    public void removeMin1()
    {
        Heap<Integer> heap1 = new Heap<Integer>(13);
        heap1.insert(21);
        heap1.insert(16);

        int[] a = new int[3];
        int[] b = {13, 16, 21};
        for (int i=0; i<3; i++){
            a[i]=heap1.removeMin();
            assertEquals(b[i], a[i]);
        }
    }

    @Test
    public void removeMin2()
    {
        Heap<Integer> heap1 = new Heap<Integer>(1);

        assertEquals(1, (int)heap1.removeMin());
    }

    @Test
    public void removeMin3()
    {
        Heap<Integer> heap1 = new Heap<Integer>(13);
        heap1.insert(21);
        heap1.insert(12);
        heap1.insert(16);
        heap1.insert(17);
        heap1.insert(18);
        heap1.insert(10);
        heap1.insert(11);

        int[] a = new int[8];
        int[] b = {10, 11, 12, 13, 16, 17, 18, 21};
        for (int i=0; i<8; i++){
            a[i]=heap1.removeMin();
            assertEquals(b[i], a[i]);
        }
    }
}