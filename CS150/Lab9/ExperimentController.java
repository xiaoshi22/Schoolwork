import java.util.Random;
import java.io.PrintWriter; 
import java.io.FileWriter;
import java.io.IOException;

/**
 * ExperimentController Class. This class is used to simulate the
 * running time for insertion and search. 
 * 
 * @author Xiaoshi Wang
 * @version April 18, 2017
 */
public class ExperimentController
{
    // instance variables
    private static final int RUNS = 5; // the number of simulations for each data point

    /** Main Method to perform our experiment. The results are printed in the excel file.
     *  @param args unused.
     */  
    public static void main (String [] args){
        // initialise instance variables
        ExperimentController c = new ExperimentController();

        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileWriter("myData.csv")); // save the output in "myData.csv"

            double loadFactors[] = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1};
            int numberOfItems[] = {200000, 400000, 500000, 600000, 700000, 800000};

            pw.print("timeInsert");
            pw.print(System.getProperty("line.separator"));
            // pw.print(","+"0.2"+","+"0.25"+","+"0.3"+","+"0.35"+","+"0.4"+","+"0.45"+","+"0.5"+","+"0.55"+","+"0.6"+","+"0.65"+","+"0.7"+","+"0.75"+","+"0.8"+","+"0.85"+","+"0.9"+","+"0.95"+","+"1");
            pw.print(","+"0.2"+","+"0.3"+","+"0.4"+","+"0.5"+","+"0.6"+","+"0.7"+","+"0.8"+","+"0.9"+","+"1");
            pw.print(System.getProperty("line.separator"));
            for (int i = 0; i< 6; i++){
                pw.print(numberOfItems[i]+",");
                for (int j = 0; j< 9; j++){
                    pw.print(c.timeInsert(100+i+j, loadFactors[j], numberOfItems[i])+",");
                }
                pw.print(System.getProperty("line.separator"));
            }

            
            pw.print("timeFind(Successful)");
            pw.print(System.getProperty("line.separator"));
            // pw.print(","+"0.2"+","+"0.25"+","+"0.3"+","+"0.35"+","+"0.4"+","+"0.45"+","+"0.5"+","+"0.55"+","+"0.6"+","+"0.65"+","+"0.7"+","+"0.75"+","+"0.8"+","+"0.85"+","+"0.9"+","+"0.95"+","+"1");
            pw.print(","+"0.2"+","+"0.3"+","+"0.4"+","+"0.5"+","+"0.6"+","+"0.7"+","+"0.8"+","+"0.9"+","+"1"); 
            pw.print(System.getProperty("line.separator"));
            for (int i = 0; i< 6; i++){
                pw.print(numberOfItems[i]+",");
                for (int j = 0; j< 9; j++){
                    pw.print(c.timeFind(100+i+j, true, loadFactors[j], numberOfItems[i])+",");
                }
                pw.print(System.getProperty("line.separator"));
            }

            pw.print("timeFind(Unsuccessful)");
            pw.print(System.getProperty("line.separator"));
            // pw.print(","+"0.2"+","+"0.25"+","+"0.3"+","+"0.35"+","+"0.4"+","+"0.45"+","+"0.5"+","+"0.55"+","+"0.6"+","+"0.65"+","+"0.7"+","+"0.75"+","+"0.8"+","+"0.85"+","+"0.9"+","+"0.95"+","+"1");
            pw.print(","+"0.2"+","+"0.3"+","+"0.4"+","+"0.5"+","+"0.6"+","+"0.7"+","+"0.8"+","+"0.9"+","+"1"); 
            pw.print(System.getProperty("line.separator"));
            for (int i = 0; i< 6; i++){
                pw.print(numberOfItems[i]+",");
                for (int j = 0; j< 9; j++){
                    pw.print(c.timeFind(100+i+j, false, loadFactors[j], numberOfItems[i])+",");
                }
                pw.print(System.getProperty("line.separator"));
            }

            System.out.println("Done!"); // print Done! to remind myself I've done my experiment
        } catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(pw != null) // close the printWriter
                pw.close(); 
        } 
    }

    /** record the running time of insertion */
    private long timeInsert(long seed, double loadFactor, int numberOfItems)
    {
        int hashTableSize = (int)(numberOfItems/loadFactor);
        int counter = 0;
        long time = 0;
        while (counter < RUNS){
            Random random = new Random(seed);

            MyHashTable table = new  MyHashTable(hashTableSize);
            Student students[] = generateStudents(random, numberOfItems);

            long startTime = System.currentTimeMillis(); // record the start time     
            for (int i = 0; i<numberOfItems; i++){
                table.insert(students[i]);
            }
            long endTime = System.currentTimeMillis()-startTime; // record the running time
            time += endTime;

            seed = random.nextLong();
            counter ++;
        }

        return time/RUNS;
    }

     /** record the running time of search */
    private long timeFind(long seed, boolean successful, double loadFactor, int numberOfItems)
    {
        int hashTableSize = (int)(numberOfItems/loadFactor);
        long time = 0;
        int counter = 0;
        while (counter < RUNS){
            Random random = new Random(seed);
            MyHashTable hashTable = new  MyHashTable(hashTableSize);
            Student students[] = generateStudents(random, numberOfItems);
            for (int i = 0; i<numberOfItems; i++){
                hashTable.insert(students[i]);
            }

            long startTime = System.currentTimeMillis(); // record the start time   
            if (successful){
                for (int i = 0; i<numberOfItems; i++){
                    hashTable.contain(students[i].getID());
                }
            } else {
                for (int i = 0; i<numberOfItems; i++){
                    hashTable.contain(random.nextInt(99999999));
                }
            }
            long endTime = System.currentTimeMillis()-startTime; // record the running time
            time += endTime;

            seed = random.nextLong();
            counter ++;
        }
        return time/RUNS;
    }

    /** String random generator */
    private String generator(Random rng, String characters, int length){
        char[] text = new char[length];
        for (int i = 0; i < length; i++){
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    /** randomly generate a Worker class */
    private Student[] generateStudents(Random rng, int num){
        Student[] students = new Student[num];

        String firstName;
        String lastName;
        Long iD;
        for (int i=0; i<num; i++){
            firstName = generator(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 6);
            lastName = generator(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" , 6);
            iD = Long.parseLong(generator(rng, "0123456789" , 9));
            students[i] = new Student(firstName, lastName, iD);
        }

        return students;
    }
}