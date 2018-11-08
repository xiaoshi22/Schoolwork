
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WorkerListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WorkerListTest
{
    /**
     * Default constructor for test class WorkerListTest
     */
    public WorkerListTest()
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
        WorkerList workerLi1 = new WorkerList();

        Worker worker1 = new Worker("Xiaoshi", "Wang", 904364987);

        workerLi1.addElement(worker1);

        Worker[] w = {worker1};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void addElement2()
    {
        WorkerList workerLi1 = new WorkerList();

        Worker worker1 = new Worker("Xiaoshi", "Wang", 904364987);
        Worker worker2 = new Worker("Biquang", "Wang", 904364765);

        workerLi1.addElement(worker1);
        workerLi1.addElement(worker2);

        Worker[] w = {worker1, worker2};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void mergeSort1()
    {
        WorkerList workerLi1 = new WorkerList();

        Worker worker1 = new Worker("Xiaoshi", "Wang", 904364987);

        workerLi1.addElement(worker1);

        workerLi1.mergeSort();

        Worker[] w = {worker1};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void mergeSort2()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);

        workerLi1.addElement(worker2);
        workerLi1.addElement(worker1);

        workerLi1.mergeSort();

        Worker[] w = {worker1, worker2};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void mergeSort3()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);

        workerLi1.addElement(worker1);
        workerLi1.addElement(worker2);

        workerLi1.mergeSort();

        Worker[] w = {worker1, worker2};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void mergeSort4()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);
        Worker worker3 = new Worker("Yifan", "Xu", 1003);

        workerLi1.addElement(worker3);
        workerLi1.addElement(worker1);
        workerLi1.addElement(worker2);

        workerLi1.mergeSort();

        Worker[] w = {worker1, worker2, worker3};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void mergeSort5()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);
        Worker worker3 = new Worker("Yifan", "Xu", 1003);

        workerLi1.addElement(worker3);
        workerLi1.addElement(worker2);
        workerLi1.addElement(worker1);

        workerLi1.mergeSort();

        Worker[] w = {worker1, worker2, worker3};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void mergeSort6()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);
        Worker worker3 = new Worker("Yifan", "Xu", 1003);
        Worker worker4 = new Worker("haha", "Bu", 1004);

        workerLi1.addElement(worker3);
        workerLi1.addElement(worker2);
        workerLi1.addElement(worker1);
        workerLi1.addElement(worker4);

        workerLi1.mergeSort();

        Worker[] w = {worker1, worker2, worker3, worker4};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void mergeSort7()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1004);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);
        Worker worker3 = new Worker("Yifan", "Xu", 1003);
        Worker worker4 = new Worker("haha", "Bu", 1004);

        workerLi1.addElement(worker3);
        workerLi1.addElement(worker2);
        workerLi1.addElement(worker1);
        workerLi1.addElement(worker4);

        workerLi1.mergeSort();

        Worker[] w = {worker2, worker3, worker1, worker4};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void quickSort1()
    {
        WorkerList workerLi1 = new WorkerList();

        Worker worker1 = new Worker("Xiaoshi", "Wang", 904364987);

        workerLi1.addElement(worker1);

        workerLi1.quickSort();

        Worker[] w = {worker1};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void quickSort2()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);

        workerLi1.addElement(worker2);
        workerLi1.addElement(worker1);

        workerLi1.quickSort();

        Worker[] w = {worker1, worker2};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void quickSort3()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);

        workerLi1.addElement(worker1);
        workerLi1.addElement(worker2);

        workerLi1.quickSort();

        Worker[] w = {worker1, worker2};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void quickSort4()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);
        Worker worker3 = new Worker("Yifan", "Xu", 1003);

        workerLi1.addElement(worker3);
        workerLi1.addElement(worker1);
        workerLi1.addElement(worker2);

        workerLi1.quickSort();

        Worker[] w = {worker1, worker2, worker3};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void quickSort5()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);
        Worker worker3 = new Worker("Yifan", "Xu", 1003);

        workerLi1.addElement(worker3);
        workerLi1.addElement(worker2);
        workerLi1.addElement(worker1);

        workerLi1.quickSort();

        Worker[] w = {worker1, worker2, worker3};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void quickSort6()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1001);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);
        Worker worker3 = new Worker("Yifan", "Xu", 1003);
        Worker worker4 = new Worker("haha", "Bu", 1004);

        workerLi1.addElement(worker3);
        workerLi1.addElement(worker2);
        workerLi1.addElement(worker1);
        workerLi1.addElement(worker4);

        workerLi1.quickSort();

        Worker[] w = {worker1, worker2, worker3, worker4};
        assertArrayEquals(w, workerLi1.printData());
    }

    @Test
    public void quickSort7()
    {
        WorkerList workerLi1 = new WorkerList();
        Worker worker1 = new Worker("Biquang", "Wang", 1004);
        Worker worker2 = new Worker("Xiaoshi", "Wang", 1002);
        Worker worker3 = new Worker("Yifan", "Xu", 1003);
        Worker worker4 = new Worker("haha", "Bu", 1004);

        workerLi1.addElement(worker3);
        workerLi1.addElement(worker2);
        workerLi1.addElement(worker1);
        workerLi1.addElement(worker4);

        workerLi1.quickSort();

        Worker[] w = {worker2, worker3, worker1, worker4};
        assertArrayEquals(w, workerLi1.printData());
    }
}