import java.util.ArrayList;
import java.util.Random;

/**
 *  ExperimentController Class. We insert 100 Integers into a 
 *  Heap and then prints them out.
 * 
 * @author Xiaoshi Wang
 * @version April 3, 2017
 */
public class ExperimentController
{
    public static void main(String[] args) {
        ExperimentController c = new ExperimentController();

        Heap<Integer> heap1 = new Heap<Integer>(null);
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        for (int i = 0; i<100; i++){
            // recreate a random integer if the integer we generate has already in the heap
            int rand = randomGenerator.nextInt(1000);
            while(numbers.contains(rand)) 
                rand = randomGenerator.nextInt(1000);
            numbers.add(rand);
            
            heap1.insert(rand);
        }
        
        // print all the integers
        System.out.println("Here is our list of 100 random integers in order.");
        for (int i = 0; i<100; i++){
            System.out.println(i+1 +") "+heap1.removeMin());
        }
    }
}
