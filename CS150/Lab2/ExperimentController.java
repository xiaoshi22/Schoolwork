import java.util.*;
import java.io.PrintWriter; 
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class operates methods in RandomNumbersContainer and record the run time for different
 * ways to build up a series of numbers. PrintWriter are used to write an excel file.
 * 
 * @author Xiaoshi Wang
 * @version 02/06/2017
 */
public class ExperimentController
{
    /** the main method */
    public static void main(String[] args) {
        ExperimentController c = new ExperimentController();

        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileWriter("myConclusion.csv")); // save the output in "myConclusion.csv"

            // print the first line in the file
            pw.write(", timeAddToFront, timeAddToBack, timeAddSorted, timeAddAndSort, timeSortofUnsortedList, timeSortofSortedList" + System.getProperty("line.separator"));

            // for each line, we record six times with size = 10000*i
            for (int i = 1; i <= 5; i++){
                double time1 = c.timeAddToFront(10000*i, 100);
                double time2 = c.timeAddToBack(10000*i, 100);
                double time3 = c.timeAddSorted(10000*i, 100);
                double time4 = c.timeAddAndSort(10000*i, 100);
                double time5 = c.timeSortofUnsortedList(10000*i, 100);
                double time6 = c.timeSortofSortedList(10000*i, 100);
                pw.write(10000*i+","+time1+","+time2+","+time3+","+time4+","+time5+","+time6+System.getProperty("line.separator"));
            }
            System.out.println("Done!"); // print Done! to remind myself I've done my experiment
        } catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(pw != null) // close the printWriter
                pw.close(); 
        } 
    }

    /** run time for adding elements to the front */
    private double timeAddToFront(int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        for (int j = 0; j < 5; j++){ // seed  different in each time
            RandomIntegerContainer c = new RandomIntegerContainer(); // constructor of RandomIntegerContainer
            Random random = new Random(seed); // define radom
            long startTime = System.currentTimeMillis(); // record the start time
            for (int i = 0; i < numberOfItems; i++){
                c.addToFront(random.nextInt()); // add elements to front
            }
            long stopTime = System.currentTimeMillis(); // record the stop time

            sum = sum + (stopTime - startTime); // add the sum by our run time

            seed = seed+100; // seed increased by 100 in each time
        }
        return sum/5; // return the average after five times
    }

    /** run time for adding elements to the back */
    private double timeAddToBack(int numberOfItems, int seed){
        RandomIntegerContainer c = new RandomIntegerContainer();
        double sum = 0;
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt()); // add elements to back
            }
            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    } 

    /** run time for adding elements by sorting */
    private double timeAddSorted( int numberOfItems, int seed){
        RandomIntegerContainer c = new RandomIntegerContainer();
        double sum = 0;
        for (int j = 0; j < 5; j++){

            Random random = new Random(seed);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfItems; i++){
                c.addSorted(random.nextInt()); // add elements by sorting
            }
            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    }

    /** run time for adding elements to the back and sorting */
    private double timeAddAndSort(int numberOfItems, int seed){
        RandomIntegerContainer c = new RandomIntegerContainer();

        double sum = 0;
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt()); // add elements to back
            }
            c.insertionSort(); // insertion sort the numbers

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    }

    /** run time for sorting an unsorted list */
    private double timeSortofUnsortedList(int numberOfItems, int seed){
        RandomIntegerContainer c = new RandomIntegerContainer();

        double sum = 0;
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);

            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt()); // add elements to back
            }

            long startTime = System.currentTimeMillis();
            c.insertionSort(); // insertion sort the numbers

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    }

    /** run time for sorting a sorted list */

    private double timeSortofSortedList(int numberOfItems, int seed){
        RandomIntegerContainer c = new RandomIntegerContainer();

        double sum = 0;
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt());// add elements to back
            }

            c.insertionSort(); // insertion sort the numbers
            long startTime = System.currentTimeMillis();
            c.insertionSort(); // sort the sorted array

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    }
}