/**
 * Class Controller. A class to read in a movie library file 
 * and then write it sorted to another file.
 * 
 * @author Xiaoshi Wang
 * @version March 24, 2017
 */
public class Controller
{
    public static void main (String [] args){
        if( args.length == 0 ) // if no files names is typed          
            System.out.println( "No files specified" );
        for(String fileName : args){
            MovieLibrary library = new MovieLibrary(fileName); // construct a new library based on my file
            library.printOurTree(); // print the film names in order
        }
    }
}