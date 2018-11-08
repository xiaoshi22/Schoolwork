import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StudentTest.
 *
 * @author  Xiaoshi Wang
 * @version April 18, 2017
 */
public class StudentTest
{
    /**
     * Default constructor for test class StudentTest
     */
    public StudentTest()
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
    public void getFirst1()
    {
        Student student1 = new Student("Xiaoshi", "Wang", 123);

        assertEquals("Xiaoshi", student1.getFirst());
    }

    @Test
    public void getFirst2()
    {
        Student student1 = new Student("Yifan", "Xu", 456);

        assertEquals("Yifan", student1.getFirst());
    }

    @Test
    public void getLast1()
    {
        Student student1 = new Student("Xiaoshi", "Wang", 123);

        assertEquals("Wang", student1.getLast());
    }

    @Test
    public void getLast2()
    {
        Student student1 = new Student("Yifan", "Xu", 456);

        assertEquals("Xu", student1.getLast());
    }

    @Test
    public void getID1()
    {
        Student student1 = new Student("Xiaoshi", "Wang", 123);

        assertEquals(123, student1.getID());
    }

    @Test
    public void getID2()
    {
        Student student1 = new Student("Yifan", "Xu", 456);

        assertEquals(456, student1.getID());
    }
}