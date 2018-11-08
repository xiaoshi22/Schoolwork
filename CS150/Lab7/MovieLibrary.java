import java.util.Scanner;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.PrintWriter; 
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class MovieLibrary. A class includes methods to read a list
 * of flims in a file, put them into a bianry tree and write 
 * each elements in order in another file.
 * 
 * @author Xiaoshi Wang
 * @version March 24, 2017
 */
public class MovieLibrary
{
    // instance variables 
    private String fileName;

    /** Constructor for objects of class MovieLibrary */
    public MovieLibrary(String fileName){
        // initialise the instance variable
        this.fileName = fileName;
    }

    /** a binary search tree is constructed when a filename is provided */
    private BinarySearchTree<Movie> addToTree(){
        BinarySearchTree<Movie> tree = null;
        Scanner fileIn=null; 
        try{
            fileIn = new Scanner(new FileReader(fileName)); // read input from "file name"
            if (fileIn.hasNextLine()== true){
                String title = fileIn.next(); // the frist word is the category

                // read the information for the first movie and put it as the root
                String director = fileIn.next();    
                int year = fileIn.nextInt();
                String genere = fileIn.next();
                Movie root = new Movie(title, director, year, genere);
                tree = new BinarySearchTree<Movie>(root);

                // insert a new movie until we reach the end of the file
                while (fileIn.hasNextLine()){
                    // title, director, year and genere are updated according to the next line
                    title = fileIn.next(); 
                    director = fileIn.next();            
                    year = fileIn.nextInt(); 
                    genere = fileIn.next();

                    Movie mo = new Movie(title, director, year, genere);
                    tree.insert(mo); // new movie is inserted in our tree
                }
            }
        } catch(Exception e){
            System.out.println(e); 
        } finally {
            if(fileIn != null) // close the scanner
                fileIn.close(); 
        }
        return tree;
    }

    /** a file with film names in order is written when a filename is provided */
    public void printOurTree(){
        PrintWriter fileOut = null;
        try{
            fileOut = new PrintWriter(new FileWriter("output.txt")); // save the output in "output.txt"
            
            BinarySearchTree<Movie> tree = addToTree(); // convert the films in our txt into a binary search
            ArrayList<Movie> list = tree.inOrderList(); // print movies in this tree in order
            
            // print the name of each film in order into a file
            for (int i = 0; i < list.size(); i++){
                fileOut.write(list.get(i).getTit()+ System.getProperty("line.separator"));
            }
        } catch(IOException e){ 
            e.printStackTrace(); 
        } finally {
            if(fileOut != null) // close the printWriter
                fileOut.close(); 
        }
    }
}