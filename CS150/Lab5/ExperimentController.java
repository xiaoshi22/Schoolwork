import java.util.*;
import java.io.PrintWriter; 
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class operates methods in WorkerList and ContactList, 
 * and record the run time for mergeSort and wquickSort to 
 * sort a series of workers or contact. PrintWriter are used to 
 * write an excel file.
 * 
 * @author Xiaoshi Wang
 * @version Feb 27, 2017
 */
public class ExperimentController
{
    /** the main method for document myConclusion*/
    public static void main(String[] args) {
        ExperimentController c = new ExperimentController();

        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileWriter("myConclusion.csv")); // save the output in "myConclusion.csv"

            // print the first line in the file
            pw.write(", ,  timeMergeSort,  timeQuickSort" + System.getProperty("line.separator"));

            // for each line, we record six times with size = 10000*i
            for (int i = 1; i <= 5; i++){
                int j = 10000*i;
                // 2 running time for sorting worker lists
                double time1 = c.workerTimeMergeSort(j, 100);
                double time2 = c.workerTimeQuickSort(j, 100);

                // 2 running time for sorting contact lists
                double contactTime1 = c.contactTimeMergeSort(j, 100);
                double contactTime2 = c.contactTimeQuickSort(j, 100);

                // print the 4 running time in two lines
                pw.write(j+","+"Worker"+","+time1+","+time2+System.getProperty("line.separator")
                    +j+","+"Contact"+","+contactTime1+","+contactTime2+System.getProperty("line.separator"));

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

    // the following 2 sortings are all for worker class
    /** run time for mergeSort a workerList */
    private double workerTimeMergeSort(int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        for (int j = 0; j < 5; j++){ // seed  different in each time
            WorkerList c = new WorkerList(); // constructor of WorkerList

            Random random = new Random(seed); // define radom

            for (int i = 0; i < numberOfItems; i++){
                Worker worker = generateWorker(random);
                c.addElement(worker);
            }

            long startTime = System.currentTimeMillis(); // record the start time
            c.mergeSort();
            long stopTime = System.currentTimeMillis(); // record the stop time

            sum = sum + (stopTime - startTime); // add the sum by our run time

            seed = seed+100; // seed increased by 100 in each time
        }
        return sum/5; // return the average after five times
    }

    /** run time for quickSort a WorkerList */
    private double workerTimeQuickSort(int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        for (int j = 0; j < 5; j++){ // seed  different in each time
            WorkerList c = new WorkerList(); // constructor of WorkerList

            Random random = new Random(seed); // define radom

            for (int i = 0; i < numberOfItems; i++){
                Worker worker = generateWorker(random);
                c.addElement(worker);
            }

            long startTime = System.currentTimeMillis(); // record the start time
            c.quickSort();
            long stopTime = System.currentTimeMillis(); // record the stop time

            sum = sum + (stopTime - startTime); // add the sum by our run time

            seed = seed+100; // seed increased by 100 in each time
        }
        return sum/5; // return the average after five times
    } 

    // the following 2 sortings are all for worker class
    /** run time for mergeSort a ContactList */
    private double contactTimeMergeSort( int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        for (int j = 0; j < 5; j++){ // seed  different in each time
            ContactList c = new ContactList(); // constructor of contactList

            Random random = new Random(seed); // define radom

            for (int i = 0; i < numberOfItems; i++){
                Contact contact = generateContact(random);
                c.addElement(contact);
            }

            long startTime = System.currentTimeMillis(); // record the start time
            c.mergeSort();
            long stopTime = System.currentTimeMillis(); // record the stop time

            sum = sum + (stopTime - startTime); // add the sum by our run time

            seed = seed+100; // seed increased by 100 in each time
        }
        return sum/5; // return the average after five times
    }

    /** run time for quickSort a ContactList*/
    private double contactTimeQuickSort(int numberOfItems, int seed){
        double sum = 0; // track the sum to calculate the average later
        for (int j = 0; j < 5; j++){ // seed  different in each time
            ContactList c = new ContactList(); // constructor of ContactList
            Random random = new Random(seed); // define radom

            for (int i = 0; i < numberOfItems; i++){
                Contact contact = generateContact(random);
                c.addElement(contact);
            }

            long startTime = System.currentTimeMillis(); // record the start time
            c.quickSort();
            long stopTime = System.currentTimeMillis(); // record the stop time

            sum = sum + (stopTime - startTime); // add the sum by our run time

            seed = seed+100; // seed increased by 100 in each time
        }
        return sum/5; // return the average after five times
    }

    /** String random generator */
    private String generateString(Random rng, String characters, int length){
        char[] text = new char[length];
        for (int i = 0; i < length; i++){
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    /** randomly generate a Worker class */
    private Worker generateWorker(Random rng){
        String firstName = new String(generateString(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 6));
        String lastName = new String(generateString(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 6));
        Long iD = new Long(generateString(rng, "0123456789" , 9));
        Worker worker = new Worker(firstName, lastName, iD);
        return worker;
    }

    /** randomly generate a Contact class */
    private Contact generateContact(Random rng){
        String firstName = new String(generateString(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 6));
        String lastName = new String(generateString(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 6));
        String phoneNumber = new String(generateString(rng, "0123456789" , 10));
        String email = new String(generateString(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@" , 15));
        Contact contact = new Contact(firstName, lastName, phoneNumber, email);
        return contact;
    }
}