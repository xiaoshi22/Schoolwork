import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MovieTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MovieTest
{
    /**
     * Default constructor for test class MovieTest
     */
    public MovieTest()
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
        Movie mo1 = new Movie("qwe", "qwe", 1997, "qwe");
        Movie mo2 = new Movie("qwe", "qwe", 1998, "qwe");
        assertEquals(-1, mo1.compareTo(mo2));
    }
    
     @Test
    public void compareTo2()
    {
        Movie mo1 = new Movie("qwz", "qwe", 1997, "qwe");
        Movie mo2 = new Movie("qwe", "qwe", 1997, "qwe");
        assertEquals(1, mo1.compareTo(mo2));
    }
    
     @Test
    public void compareTo3()
    {
        Movie mo1 = new Movie("qwe", "qwe", 1997, "qwe");
        Movie mo2 = new Movie("qwe", "qwe", 1997, "qwz");
        assertEquals(-1, mo1.compareTo(mo2));
    }
    
     @Test
    public void compareTo4()
    {
        Movie mo1 = new Movie("qwe", "qwz", 1997, "qwe");
        Movie mo2 = new Movie("qwe", "qwe", 1997, "qwe");
        assertEquals(1, mo1.compareTo(mo2));
    }
    
     @Test
    public void compareTo5()
    {
        Movie mo1 = new Movie("qwe", "qwz", 1997, "qwe");
        Movie mo2 = new Movie("qwe", "qwz", 1997, "qwe");
        assertEquals(0, mo1.compareTo(mo2));
    }
}
