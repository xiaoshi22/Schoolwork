import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MyHashTableTest.
 *
 * @author  Xiaoshi Wang
 * @version April 18, 2017
 */
public class MyHashTableTest
{
    /**
     * Default constructor for test class MyHashTableTest
     */
    public MyHashTableTest()
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
        MyHashTable myHashTa1 = new MyHashTable(10);

        assertEquals(true, myHashTa1.insert(new Student("a", "b", 1)));
    }

    @Test
    public void insert2()
    {
        MyHashTable myHashTa1 = new MyHashTable(1);

        assertEquals(true, myHashTa1.insert(new Student("a", "b", 1)));
    }

    @Test
    public void insert3()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        myHashTa1.insert(new Student("a", "b", 1));

        assertEquals(false, myHashTa1.insert(new Student("a", "b", 1)));
    }

    @Test
    public void insert4()
    {
        MyHashTable myHashTa1 = new MyHashTable(1);

        myHashTa1.insert(new Student("a", "b", 1));
        myHashTa1.insert(new Student("a", "b", 1));
        myHashTa1.insert(new Student("a", "b", 2));

        assertEquals(false, myHashTa1.insert(new Student("a", "b", 2)));
    }

    @Test
    public void contain1()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        assertEquals(null, myHashTa1.contain(1));
    }

    @Test
    public void contain2()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        myHashTa1.insert(new Student("a", "b", 2));

        Student stu = new Student("a", "b", 2);

        assertEquals(stu.getFirst(), myHashTa1.contain(2).getFirst());
        assertEquals(stu.getLast(), myHashTa1.contain(2).getLast());
        assertEquals(stu.getID(), myHashTa1.contain(2).getID());
    }

    @Test
    public void contain3()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        myHashTa1.insert(new Student("a", "b", 2));

        assertEquals(null, myHashTa1.contain(1));
    }

    @Test
    public void contain4()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        myHashTa1.insert(new Student("a", "b", 2));
        myHashTa1.insert(new Student("c", "d", 2));

        Student stu = new Student("a", "b", 2);

        assertEquals(stu.getFirst(), myHashTa1.contain(2).getFirst());
        assertEquals(stu.getLast(), myHashTa1.contain(2).getLast());
        assertEquals(stu.getID(), myHashTa1.contain(2).getID());
    }

    @Test
    public void contain5()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        myHashTa1.insert(new Student("a", "b", 2));
        myHashTa1.insert(new Student("c", "d", 12));

        Student stu = new Student("c", "d", 12);

        assertEquals(stu.getFirst(), myHashTa1.contain(12).getFirst());
        assertEquals(stu.getLast(), myHashTa1.contain(12).getLast());
        assertEquals(stu.getID(), myHashTa1.contain(12).getID());
    }

    @Test
    public void remove1()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        assertEquals(null, myHashTa1.remove(1));
    }

    @Test
    public void remove2()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        myHashTa1.insert(new Student("a", "b", 2));

        Student stu = new Student("a", "b", 2);

        Student stu2 = myHashTa1.remove(2);

        assertEquals(stu.getFirst(), stu2.getFirst());
        assertEquals(stu.getLast(), stu2.getLast());
        assertEquals(stu.getID(), stu2.getID());
        assertEquals(null, myHashTa1.contain(2));
    }

    @Test
    public void remove3()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        myHashTa1.insert(new Student("a", "b", 2));

        assertEquals(null, myHashTa1.remove(1));
    }

    @Test
    public void remove5()
    {
        MyHashTable myHashTa1 = new MyHashTable(10);

        myHashTa1.insert(new Student("a", "b", 2));
        myHashTa1.insert(new Student("c", "d", 12));

        Student stu = new Student("a", "b", 2);

        Student stu2 = myHashTa1.remove(2);

        assertEquals(stu.getFirst(), stu2.getFirst());
        assertEquals(stu.getLast(), stu2.getLast());
        assertEquals(stu.getID(), stu2.getID());
        assertEquals(null, myHashTa1.contain(2));
    }
}