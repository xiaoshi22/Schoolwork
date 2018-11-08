
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ContactListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ContactListTest
{
    /**
     * Default constructor for test class ContactListTest
     */
    public ContactListTest()
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
    public void addElement1()
    {
        ContactList contactLi1 = new ContactList();

        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");

        contactLi1.addElement(contact1);

        Contact[] c = {contact1};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void addElement2()
    {
        ContactList contactLi1 = new ContactList();

        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");

        contactLi1.addElement(contact1);
        contactLi1.addElement(contact2);

        Contact[] c = {contact1, contact2};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void mergeSort1()
    {
        ContactList contactLi1 = new ContactList();

        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");

        contactLi1.addElement(contact1);

        contactLi1.mergeSort();

        Contact[] c = {contact1};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void mergeSort2()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");

        contactLi1.addElement(contact1);
        contactLi1.addElement(contact2);

        contactLi1.mergeSort();

        Contact[] c = {contact1,contact2};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void mergeSort3()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");

        contactLi1.addElement(contact2);
        contactLi1.addElement(contact1);

        contactLi1.mergeSort();

        Contact[] c = {contact1,contact2};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void mergeSort4()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");
        Contact contact3 = new Contact("Zara", "Xu", "9043649323", "xuz@laf");

        contactLi1.addElement(contact3);
        contactLi1.addElement(contact1);
        contactLi1.addElement(contact2);

        contactLi1.mergeSort();

        Contact[] c = {contact1,contact2, contact3};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void mergeSort5()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");
        Contact contact3 = new Contact("Zara", "Xu", "9043649323", "xuz@laf");

        contactLi1.addElement(contact3);
        contactLi1.addElement(contact2);
        contactLi1.addElement(contact1);

        contactLi1.mergeSort();

        Contact[] c = {contact1,contact2, contact3};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void mergeSort6()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");
        Contact contact3 = new Contact("Zara", "Xu", "9043649323", "xuz@laf");
        Contact contact4 = new Contact("Ze", "Yu", "9043239323", "yuz@laf");

        contactLi1.addElement(contact3);
        contactLi1.addElement(contact2);
        contactLi1.addElement(contact1);
        contactLi1.addElement(contact4);

        contactLi1.mergeSort();

        Contact[] c = {contact1,contact2, contact3, contact4};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void mergeSort7()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");
        Contact contact3 = new Contact("Zara", "Xu", "9043649323", "xuz@laf");
        Contact contact4 = new Contact("Ze", "Yu", "9043239323", "yuz@laf");

        contactLi1.addElement(contact3);
        contactLi1.addElement(contact2);
        contactLi1.addElement(contact1);
        contactLi1.addElement(contact4);

        contactLi1.mergeSort();

        Contact[] c = {contact1,contact2, contact3, contact4};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void quickSort1()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");

        contactLi1.addElement(contact1);

        contactLi1.quickSort();

        Contact[] c = {contact1};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void quickSort2()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");

        contactLi1.addElement(contact2);
        contactLi1.addElement(contact1);

        contactLi1.quickSort();

        Contact[] c = {contact1,contact2};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void quickSort3()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");

        contactLi1.addElement(contact1);
        contactLi1.addElement(contact2);

        contactLi1.quickSort();

        Contact[] c = {contact1,contact2};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void quickSort4()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");
        Contact contact3 = new Contact("Zara", "Xu", "9043649323", "xuz@laf");

        contactLi1.addElement(contact3);
        contactLi1.addElement(contact1);
        contactLi1.addElement(contact2);

        contactLi1.quickSort();

        Contact[] c = {contact1,contact2, contact3};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void quickSort5()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");
        Contact contact3 = new Contact("Zara", "Xu", "9043649323", "xuz@laf");
        contactLi1.addElement(contact3);
        contactLi1.addElement(contact2);
        contactLi1.addElement(contact1);

        contactLi1.quickSort();

        Contact[] c = {contact1,contact2, contact3};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void quickSort6()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");
        Contact contact3 = new Contact("Zara", "Xu", "9043649323", "xuz@laf");
        Contact contact4 = new Contact("Ze", "Yu", "9043239323", "yuz@laf");

        contactLi1.addElement(contact3);
        contactLi1.addElement(contact2);
        contactLi1.addElement(contact1);
        contactLi1.addElement(contact4);

        contactLi1.quickSort();

        Contact[] c = {contact1,contact2, contact3, contact4};
        assertArrayEquals(c, contactLi1.printData());
    }

    @Test
    public void quickSort7()
    {
        ContactList contactLi1 = new ContactList();
        Contact contact1 = new Contact("Xiaoshi", "Wang", "904364987", "wangxi@laf");
        Contact contact2 = new Contact("Yifan", "Xu", "9043649345", "xuy@laf");
        Contact contact3 = new Contact("Zara", "Xu", "9043649323", "xuz@laf");
        Contact contact4 = new Contact("Ze", "Yu", "9043239323", "yuz@laf");

        contactLi1.addElement(contact3);
        contactLi1.addElement(contact2);
        contactLi1.addElement(contact1);
        contactLi1.addElement(contact4);

        contactLi1.quickSort();

        Contact[] c = {contact1,contact2, contact3, contact4};
        assertArrayEquals(c, contactLi1.printData());
    }
}
