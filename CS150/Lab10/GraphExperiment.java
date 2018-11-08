import java.util.Scanner;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.PrintWriter; 
import java.io.IOException;

/**
 * class GraphExperiment. In this class, we can use a file as the 
 * input construct a DrictedGraph based on it. Then, the closest
 * neighbor that each node can reach using breadth first 
 * traversal can be printed out.
 * 
 * @author  Xiaoshi Wang
 * @version April 27, 2017
 */
public class GraphExperiment
{
    /**
     * main method
     */
    public static void main(String[] args) {
        for(String fileName : args)          
            experiment( fileName ); 
    }

    /**
     * Read in a file with a graph description and print out the
     * closest neighbor that each node can reach using breadth 
     * first traversal starting at the first node in the graph 
     * (the first one in the file).
     * 
     * @param  fileName   the name of the file as input
     */
    public static void experiment(String fileName) {
        Scanner fileIn=null; 
        try{
            fileIn = new Scanner(new FileReader(fileName)); // read input from "file name"

            DirectedGraph<String> graph = new DirectedGraph(); // construct a new graph
            String firstNodeKey = null;
            if (fileIn.hasNextLine()){ // while there are some lines left, create all nodes by the first line
                Scanner firstLine  = new Scanner(fileIn.nextLine()); 
                firstNodeKey = firstLine.next();
                graph.addNode(firstNodeKey);
                while (firstLine.hasNext()) graph.addNode(firstLine.next());
            }
            
            // add edges by the rest of lines
            while (fileIn.hasNext()){ 
                graph.addEdge(fileIn.next(), fileIn.next(), fileIn.nextInt());
            }

            // call the breadthFirstClosest method
            graph.breadthFirstClosest(firstNodeKey);
        } catch(IOException e){
            e.printStackTrace(); 
        } finally {
            if(fileIn != null) // close the scanner
                fileIn.close(); 
        }
    }
}