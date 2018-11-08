
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ContactTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ContactTest
{
    /**
     * Default constructor for test class ContactTest
     */
    public ContactTest()
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
    public void compareTo1()
    {
        Contact contact1 = new Contact("Xiaoshi", "Wang", "6109061952", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "6109062313", "xuy@laf");

        assertEquals(-1, contact1.compareTo(contact2));
    }

    @Test
    public void compareTo3()
    {
        Contact contact2 = new Contact("Xiaoshi", "Wang", "6109061952", "wangxi@laf");
        Contact contact1 = new Contact("Yifan", "Xu", "6109062313", "xuy@laf");

        assertEquals(1, contact1.compareTo(contact2));
    }

    @Test
    public void compareTo4()
    {
        Contact contact2 = new Contact("Xiaoshi", "Wang", "6109061952", "wangxi@laf");
        Contact contact1 = new Contact("Yifan", "Wang", "6109062313", "xuy@laf");

        assertEquals(1, contact1.compareTo(contact2));
    }

    @Test
    public void compareTo5()
    {
        Contact contact1 = new Contact("Xiaoshi", "Wang", "6109061952", "wangxi@laf");
        Contact contact2 = new Contact("Xiaoshi", "Wang", "6109062313", "xuy@laf");

        assertEquals(0, contact1.compareTo(contact2));
    }
}

