   import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import acm.program.*;
import acm.util.*;
import acm.gui.*;
import acm.io.*;

// a program generate random integers and sort them
public class SorterNew extends Program {

    public static final int 
    APPLICATION_WIDTH = 500,
    APPLICATION_HEIGHT = 600;

    // constants
    private static final int
    MAX = 1000000,
    AREA_ROWS = 20,
    AREA_COLS = 20;

    // instance variables
    private JTextArea numberArea, sortedNumberArea;
    private IntField numField;
    private JButton generateButton, sortButton;
    private JComboBox method;
    private int[] data = new int[MAX];
    private RandomGenerator rand = RandomGenerator.getInstance();
    private int num;

    public void init () {
        drawGUI();   
    }

    private void drawGUI() {
        // number of int to generate
        numField = new IntField();
        numField.setColumns(10);
        add(numField, NORTH);

        // generate button
        generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        add(generateButton, NORTH);

        // sort method
        method = new JComboBox();
        method.addItem("Selection sort");
        method.addItem("Insertion sort");
        method.addItem("Merge sort");
        method.addItem("Quick sort");
        add(method, NORTH);

        // sort button
        sortButton = new JButton("Sort");
        sortButton.addActionListener(this);
        add(sortButton, NORTH);

        // unsorted number area
        numberArea = new JTextArea(AREA_ROWS, AREA_COLS);
        JScrollPane numberScrPane = new JScrollPane(numberArea);
        numberScrPane.setBorder(new TitledBorder("Numbers"));
        add(numberScrPane, CENTER);

        // sorted number area
        sortedNumberArea = new JTextArea(AREA_ROWS, AREA_COLS);
        JScrollPane sortedNumberScrPane = new JScrollPane(sortedNumberArea);
        sortedNumberScrPane.setBorder(new TitledBorder("Sorted Numbers"));
        add(sortedNumberScrPane, CENTER); 
    }

    public void actionPerformed (ActionEvent evt) {
        num = -100000;
        // generate random data
        if (evt.getSource() == generateButton) { 
            for (int i = 0; i < 7 ; i ++) {
                num = num+150000;
                for (int j = 0; j<4; j++){
                    for (int k = 0; k < num; k++) {
                        data[k] = rand.nextInt(num);
                    }
                    if (j == 0) {
                        long currentTime = System.currentTimeMillis();
                        selectionSort(0, num-1);
                    }
                    if (j ==1) {
                        long currentTime = System.currentTimeMillis();
                        insertionSort(0, num-1);
                        long difference = System.currentTimeMillis() - currentTime;
                        System.out.println(num+":" +difference);
                    } 
                    if (j == 2) {
                        long currentTime = System.currentTimeMillis();
                        mergeSort(0, num-1);
                        long difference = System.currentTimeMillis() - currentTime;
                        System.out.println(num+":" +difference);
                    } 
                    if (j == 3) {
                        long currentTime = System.currentTimeMillis();
                        quickSort(0, num-1);
                        long difference = System.currentTimeMillis() - currentTime;
                        System.out.println(num+":" +difference);
                    }
                }
            }
        }
    }

    //         
    //             numberArea.setText("");
    //             num = numField.getValue();
    //             if (num > MAX) num = MAX;
    // 
    //             for (int i = 0; i < num; i++) {
    //                 data[i] = rand.nextInt(MAX);
    //                 numberArea.append(data[i] + "\n");
    //             }
    //         } else if (evt.getSource() == sortButton) { 
    //             sortedNumberArea.setText(""); // clear sortedNumberArea
    // 
    //             long currentTime = System.currentTimeMillis();
    //             // sort the array starting from position 0 to NUM-1
    //             sort(0, num-1);
    //             long difference = System.currentTimeMillis(); - currentTime;
    //             System.out.println("difference = " +difference);
    // 
    //             // add the sorted numbers one by one to sortedNumberArea 
    //             for (int i = 0; i < num; i++) {
    //                 sortedNumberArea.append(data[i] + "\n");
    //             }
    //         } 
    //     } 

    // sort the array data starting from position start
    // to position end using certain sort algorithm
    private void sort(int start, int end) {
        String s = (String)(method.getSelectedItem());

        if (s.equals("Selection sort"))
            selectionSort(start, end);
        else if (s.equals("Insertion sort"))
            insertionSort(start, end);
        else if (s.equals("Merge sort"))
            mergeSort(start, end);
        else if (s.equals("Quick sort"))
            quickSort(start, end);
    }

    /***********************************************************/
    /*     S E L E C T I O N   S O R T                         */
    /***********************************************************/

    // Performs the selection sort algorithm.  It finds the
    // smallest value in the unsorted part of the array and moves
    // it to the end of the sorted part of the array.  It does
    // this repeatedly until the entire array is sorted.
    public void selectionSort(int start, int end) {
        for (int i = start; i <= end; i++) {
            int smallest = this.findSmallest(i, end);
            swap(i, smallest);
        }
    }

    // Return the smallest element between start and end
    private int findSmallest(int start, int end) {
        int smallest = start;
        for (int i = start; i <= end; i++) {
            if (data[i] < data[smallest]) {
                smallest = i;
            }
        }
        return smallest;
    }

    // Swap the elements in indices x and y
    private void swap(int x, int y) {
        int temp = data[x];
        data[x] = data[y];
        data[y] = temp;
    }

    /***********************************************************/
    /*     I N S E R T I O N   S O R T                         */
    /***********************************************************/

    // Performs the insert sort algorithm.  In each iteration, 
    // it insert an element in a sorted list.
    public void insertionSort(int start, int end) {    
        for (int i = start + 1; i <= end; i++) {
            // assume data[0..i-1] are in order, insert i-th element
            insert(i);
        }
    }

    // Assuming data[0..i-1] are in order, insert i-th element
    // in the right place.
    private void insert(int i) {
        int temp = data[i]; // temp holder of i-th element
        int p = i-1; // start from the rear of the sorted array

        // search for the right place in reverse order
        while (p >= 0 && data[p] > temp) {
            data[p+1] = data[p]; // move over by one position
            p--;
        } 
        data[++p] = temp;  // insert in the right place  
    }

    /***********************************************************/
    /*     M E R G E   S O R T                                 */
    /***********************************************************/

    // Use merge sort to sort the array between indices start
    // and end. The first half and the second half of the 
    // array are sorted recursively. These sorted arrays
    // are then merged into one long sorted array.
    private void mergeSort(int start, int end) {
        if (start >= end) return; // base case, array has 1 element

        int mid = (start + end)/2; // the midpoint

        // recursively sort two halves
        mergeSort(start, mid);       
        mergeSort(mid+1, end);

        merge(start, mid, end); // merge sorted halves 
    }

    // merge two sorted subarrays
    // array 1 is from start to mid
    // array 2 is from mid+1 to end
    private void merge(int start, int mid, int end) {
        int num = mid - start + 1; // # of elements in array 1
        // create a temporary array to hold array 1
        int[] tmp = new int[num];

        // copy array 1 to a temporary array
        for (int i = start; i <= mid; i++) {
            tmp[i-start] = data[i];
        }

        int leftIndex = 0; // the start of array 1
        int rightIndex = mid + 1; // the start of array 2

        // The merge operation. Each iteration takes the next 
        // smallest value from either array 1 or array 2
        for (int i = start; i <= end; i++) {  
            if (leftIndex > num - 1) { // array 1 is used up
                data[i] = data[rightIndex]; 
                rightIndex++; // take an element from array 2
            } else if (rightIndex > end) { // array 2 is used up
                data[i] = tmp[leftIndex];
                leftIndex++; // take an element from array 1
            } else if (data[rightIndex] < tmp[leftIndex]) {
                data[i] = data[rightIndex];
                rightIndex++; // take an element from array 2
            } else {
                data[i] = tmp[leftIndex];
                leftIndex++; // take an element from array 1
            }
        } 
    }

    /***********************************************************/
    /*     Q U I C K   S O R T                                 */
    /***********************************************************/

    // quick sort the array data starting from position start
    // to position end   
    private void quickSort(int start, int end) {
        if (start >= end) return; // base case, array has 1 element

        // use median of start, midpoint, end as the pivot
        int mid = (start + end)/2;
        int medianIndex = median(start, mid, end);
        int pivot = data[medianIndex];

        swap(medianIndex, end); // move the pivot value to the end

        // storeindex is number of elements smaller than pivot
        int storeIndex = start;

        // if an element is smaller than pivot,
        // swap it with the element at index storeindex
        for (int i = start; i <= end-1; i++) { // partition the array
            if (data[i] <= pivot) {
                swap(storeIndex, i);
                storeIndex++;
            }
        }
        swap(storeIndex, end); // move the pivot to partition point

        // recursively sort the subarrays in place
        quickSort(start, storeIndex - 1);
        quickSort(storeIndex+1, end);
    }

    // find the index of the median from three given indices
    private int median(int i, int j, int k) {
        if (data[j] <= data[i] && data[i] <= data[k] || 
        data[k] <= data[i] && data[i] <= data[j])
            return i;
        else if (data[i] <= data[j] && data[j] <= data[k] || 
        data[k] <= data[j] && data[j] <= data[i])
            return j;
        else 
            return k;
    } 
}