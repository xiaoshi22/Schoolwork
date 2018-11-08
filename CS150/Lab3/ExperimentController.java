import java.util.*;
import java.io.PrintWriter; 
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class operates methods in LinkedListIntegerContainer and ArrayListIntegerContainer, 
 * and record the run time for different ways to build up a series of numbers. PrintWriter 
 * are used to write an excel file.
 * 
 * @author Xiaoshi Wang
 * @version Feb 13, 2017
 */
public class ExperimentController
{
    /** the main method for document myConclusion1*/

    //     public static void main(String[] args) {
    //         ExperimentController c = new ExperimentController();
    // 
    //         PrintWriter pw = null;
    //         try{
    //             pw = new PrintWriter(new FileWriter("myConclusion.csv")); // save the output in "myConclusion.csv"
    // 
    //             // print the first line in the file
    //             pw.write(", , timeAddToFront, timeAddToBack, timeAddSorted, timeSortofUnsortedList, timeSortofSortedList,timeLinearSearch, timeBinarySearch" + System.getProperty("line.separator"));
    // 
    //             // for each line, we record six times with size = 1000*i
    //             for (int i = 1; i <= 5; i++){
    //                 int j = 500*i;
    //                 // 7 running time for array lists
    //                 double time1 = c.timeAddToFront(j, 100);
    //                 double time2 = c.timeAddToBack(j, 100);
    //                 double time3 = c.timeAddSorted(j, 100);
    //                 double time4 = c.timeSortofUnsortedList(j, 100);
    //                 double time5 = c.timeSortofSortedList(j, 100);
    //                 double time6 = c.timeLinearSearch(j, 100);
    //                 double time7 = c.timeBinarySearch(j, 100);
    // 
    //                 // 7 running time for linked lists
    //                 double linkedTime1 = c.linkedTimeAddToFront(j, 100);
    //                 double linkedTime2 = c.linkedTimeAddToBack(j, 100);
    //                 double linkedTime3 = c.linkedTimeAddSorted(j, 100);
    //                 double linkedTime4 = c.linkedTimeSortofUnsortedList(j, 100);
    //                 double linkedTime5 = c.linkedTimeSortofSortedList(j, 100);
    //                 double linkedTime6 = c.linkedTimeLinearSearch(j, 100);
    //                 double linkedTime7 = c.linkedTimeBinarySearch(j, 100);
    // 
    //                 // print the 14 running time in two lines
    //                 pw.write(j+","+"Array"+","+time1+","+time2+","+time3+","+time4+","+time5+","+time6+","+time7+System.getProperty("line.separator")
    //                     +j+","+"Linked"+","+linkedTime1+","+linkedTime2+","+linkedTime3+","+linkedTime4+","+linkedTime5+","+linkedTime6+","+linkedTime7+System.getProperty("line.separator"));
    //             
    //                 System.out.println(j+" Done");
    //                 }
    //             System.out.println("Done!"); // print Done! to remind myself I've done my experiment
    //         } catch(IOException e){
    //             e.printStackTrace(); 
    //         } finally {
    //             if(pw != null) // close the printWriter
    //                 pw.close(); 
    //         } 
    //     }
    /** the main method for document myConclusion2*/
    //     public static void main(String[] args) {
    //         ExperimentController c = new ExperimentController();
    // 
    //         PrintWriter pw = null;
    //         try{
    //             pw = new PrintWriter(new FileWriter("myConclusion.csv")); // save the output in "myConclusion.csv"
    // 
    //             // print the first line in the file
    //             pw.write(", , timeAddToFront, timeAddToBack, timeLinearSearch, timeBinarySearch" + System.getProperty("line.separator"));
    // 
    //             // for each line, we record six times with size = 1000*i
    //             for (int i = 1; i <= 5; i++){
    //                 int j = 5000*i;
    //                 // 7 running time for array lists
    //                 double time1 = c.timeAddToFront(j, 100);
    //                 double time2 = c.timeAddToBack(j, 100);
    //                 //double time3 = c.timeAddSorted(j, 100);
    //                 //double time4 = c.timeSortofUnsortedList(j, 100);
    //                 //double time5 = c.timeSortofSortedList(j, 100);
    //                 double time6 = c.timeLinearSearch(j, 100);
    //                 double time7 = c.timeBinarySearch(j, 100);
    // 
    //                 // 7 running time for linked lists
    //                 double linkedTime1 = c.linkedTimeAddToFront(j, 100);
    //                 double linkedTime2 = c.linkedTimeAddToBack(j, 100);
    //                 //double linkedTime3 = c.linkedTimeAddSorted(j, 100);
    //                 //double linkedTime4 = c.linkedTimeSortofUnsortedList(j, 100);
    //                 //double linkedTime5 = c.linkedTimeSortofSortedList(j, 100);
    //                 double linkedTime6 = c.linkedTimeLinearSearch(j, 100);
    //                 double linkedTime7 = c.linkedTimeBinarySearch(j, 100);
    // 
    //                 // print the 14 running time in two lines
    //                 pw.write(j+","+"Array"+","+time1+","+time2+","+time6+","+time7+System.getProperty("line.separator")
    //                     +j+","+"Linked"+","+linkedTime1+","+linkedTime2+","+linkedTime6+","+linkedTime7+System.getProperty("line.separator"));
    // 
    //                 System.out.println(j+" Done");
    //             }
    //             System.out.println("Done!"); // print Done! to remind myself I've done my experiment
    //         } catch(IOException e){
    //             e.printStackTrace(); 
    //         } finally {
    //             if(pw != null) // close the printWriter
    //                 pw.close(); 
    //         } 
    //     }
    /** the main method for document myConclusion3*/
    //     public static void main(String[] args) {
    //         ExperimentController c = new ExperimentController();
    // 
    //         PrintWriter pw = null;
    //         try{
    //             pw = new PrintWriter(new FileWriter("myConclusion.csv")); // save the output in "myConclusion.csv"
    // 
    //             // print the first line in the file
    //             pw.write(", , timeAddToFront, timeAddToBack,  timeBinarySearch" + System.getProperty("line.separator"));
    // 
    //             // for each line, we record six times with size = 1000*i
    //             for (int i = 1; i <= 5; i++){
    //                 int j = 10000*i;
    //                 // 7 running time for array lists
    //                 double time1 = c.timeAddToFront(j, 100);
    //                 double time2 = c.timeAddToBack(j, 100);
    //                 //double time3 = c.timeAddSorted(j, 100);
    //                 //double time4 = c.timeSortofUnsortedList(j, 100);
    //                 //double time5 = c.timeSortofSortedList(j, 100);
    //                 //double time6 = c.timeLinearSearch(j, 100);
    //                 double time7 = c.timeBinarySearch(j, 100);
    // 
    //                 // 7 running time for linked lists
    //                 double linkedTime1 = c.linkedTimeAddToFront(j, 100);
    //                 double linkedTime2 = c.linkedTimeAddToBack(j, 100);
    //                 //double linkedTime3 = c.linkedTimeAddSorted(j, 100);
    //                 //double linkedTime4 = c.linkedTimeSortofUnsortedList(j, 100);
    //                 //double linkedTime5 = c.linkedTimeSortofSortedList(j, 100);
    //                 //double linkedTime6 = c.linkedTimeLinearSearch(j, 100);
    //                 double linkedTime7 = c.linkedTimeBinarySearch(j, 100);
    // 
    //                 // print the 14 running time in two lines
    //                 pw.write(j+","+"Array"+","+time1+","+time2+","+time7+System.getProperty("line.separator")
    //                     +j+","+"Linked"+","+linkedTime1+","+linkedTime2+","+linkedTime7+System.getProperty("line.separator"));
    // 
    //                 System.out.println(j+" Done");
    //             }
    //             System.out.println("Done!"); // print Done! to remind myself I've done my experiment
    //         } catch(IOException e){
    //             e.printStackTrace(); 
    //         } finally {
    //             if(pw != null) // close the printWriter
    //                 pw.close(); 
    //         } 
    //     }
    /** the main method for document myConclusion4*/
    public static void main(String[] args) {
        ExperimentController c = new ExperimentController();

        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileWriter("myConclusion.csv")); // save the output in "myConclusion.csv"

            // print the first line in the file
            pw.write(", ,  timeAddToBack,  timeBinarySearch" + System.getProperty("line.separator"));

            // for each line, we record six times with size = 1000*i
            for (int i = 1; i <= 5; i++){
                int j = 100000*i;
                // 7 running time for array lists
                //double time1 = c.timeAddToFront(j, 100);
                double time2 = c.timeAddToBack(j, 100);
                //double time3 = c.timeAddSorted(j, 100);
                //double time4 = c.timeSortofUnsortedList(j, 100);
                //double time5 = c.timeSortofSortedList(j, 100);
                //double time6 = c.timeLinearSearch(j, 100);
                double time7 = c.timeBinarySearch(j, 100);

                // 7 running time for linked lists
                //double linkedTime1 = c.linkedTimeAddToFront(j, 100);
                double linkedTime2 = c.linkedTimeAddToBack(j, 100);
                //double linkedTime3 = c.linkedTimeAddSorted(j, 100);
                //double linkedTime4 = c.linkedTimeSortofUnsortedList(j, 100);
                //double linkedTime5 = c.linkedTimeSortofSortedList(j, 100);
                //double linkedTime6 = c.linkedTimeLinearSearch(j, 100);
                double linkedTime7 = c.linkedTimeBinarySearch(j, 100);

                // print the 14 running time in two lines
                pw.write(j+","+"Array"+","+time2+","+time7+System.getProperty("line.separator")
                    +j+","+"Linked"+","+linkedTime2+","+linkedTime7+System.getProperty("line.separator"));

                System.out.println(j+" Done");
            }
            System.out.println("Done!"); // print Done! to remind myself I've done my experiment
        } catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(pw != null) // close the printWriter
                pw.close(); 
        } 
    }
    // the following 7 mewthods are all for arrayList
    /** run time for adding elements to the front */
    private double timeAddToFront(int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        for (int j = 0; j < 5; j++){ // seed  different in each time
            ArrayListIntegerContainer c = new ArrayListIntegerContainer(); // constructor of LinkedListIntegerContainer

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
        ArrayListIntegerContainer c = new ArrayListIntegerContainer();
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
        ArrayListIntegerContainer c = new ArrayListIntegerContainer();
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

    /** run time for sorting an unsorted list */
    private double timeSortofUnsortedList(int numberOfItems, int seed){
        ArrayListIntegerContainer c = new ArrayListIntegerContainer();

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
        ArrayListIntegerContainer c = new ArrayListIntegerContainer();

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

    /** the timr used for linear search */
    private double timeLinearSearch(int numberOfItems, int seed){
        ArrayListIntegerContainer c = new ArrayListIntegerContainer();
        double sum = 0;
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt());// add elements to back
            }

            long startTime = System.currentTimeMillis();
            c.linearSearch(random.nextInt());

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    }

    /** the running time used for binary search */
    private double timeBinarySearch(int numberOfItems, int seed){
        ArrayListIntegerContainer c = new ArrayListIntegerContainer();
        double sum = 0;
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt());// add elements to back
            }

            long startTime = System.currentTimeMillis();
            c.binarySearch(random.nextInt());

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    }   

    // the following 7 mewthods are all for LinkedList
    /** run time for adding elements to the front */
    private double linkedTimeAddToFront(int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        for (int j = 0; j < 5; j++){ // seed  different in each time
            LinkedListIntegerContainer c = new LinkedListIntegerContainer(); // constructor of LinkedListIntegerContainer

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
    private double linkedTimeAddToBack(int numberOfItems, int seed){
        LinkedListIntegerContainer c = new LinkedListIntegerContainer();
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
    private double linkedTimeAddSorted( int numberOfItems, int seed){
        LinkedListIntegerContainer c = new LinkedListIntegerContainer();
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

    /** run time for sorting an unsorted list */
    private double linkedTimeSortofUnsortedList(int numberOfItems, int seed){
        LinkedListIntegerContainer c = new LinkedListIntegerContainer();

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
    private double linkedTimeSortofSortedList(int numberOfItems, int seed){
        LinkedListIntegerContainer c = new LinkedListIntegerContainer();

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

    /** time used for linear search */ 
    private double linkedTimeLinearSearch(int numberOfItems, int seed){
        LinkedListIntegerContainer c = new LinkedListIntegerContainer();
        double sum = 0;
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt());// add elements to back
            }

            long startTime = System.currentTimeMillis();
            c.linearSearch(random.nextInt());

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    }

    /** time used for binary search */
    private double linkedTimeBinarySearch(int numberOfItems, int seed){
        LinkedListIntegerContainer c = new LinkedListIntegerContainer();
        double sum = 0;
        for (int j = 0; j < 5; j++){
            Random random = new Random(seed);
            for (int i = 0; i < numberOfItems; i++){
                c.addToBack(random.nextInt());// add elements to back
            }

            long startTime = System.currentTimeMillis();
            c.binarySearch(random.nextInt());

            long stopTime = System.currentTimeMillis();

            sum = sum + (stopTime - startTime);

            seed = seed+100; 
        }
        return sum/5;
    }
}