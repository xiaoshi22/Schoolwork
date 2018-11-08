
/**
 * Class Movie. A class to construct a movie with title, director,
 * year and genere. Return and compareTo methods are included in
 * this class.
 * 
 * @author Xiaoshi Wang
 * @version March 24, 2017
 */
public class Movie implements Comparable<Movie>
{
    // instance variables
    private String title;
    private String director;
    private int year;
    private String genere;

    /**
     * Constructor for objects of class Movie
     */
    public Movie(String tit, String dir, int ye, String gen)
    {
        // initialise instance variables
        title = tit;
        director = dir;
        year = ye;
        genere = gen;
    }

    /** return title */
    public String getTit()
    {
        return title;
    }

    /** return director */
    public String getDir()
    {
        return director;
    }

    /** return year */
    public int getYe()
    {
        return year;
    }

    /** return genere */
    public String getGen()
    {
        return genere;
    }

    /** overwrite compareTo method */
    public int compareTo(Movie mo)
    {
        if (this.year < mo.getYe()) return -1;
        else if (this.year > mo.getYe()) return 1;
        else {
            if (this.title.compareTo(mo.getTit())<0) return -1;
            else if (this.title.compareTo(mo.getTit())>0) return 1;
            else{
                if (this.genere.compareTo(mo.getGen())<0) return -1;
                else if (this.genere.compareTo(mo.getGen())>0) return 1;
                else{
                    if (this.director.compareTo(mo.getDir())<0) return -1;
                    else if (this.director.compareTo(mo.getDir())>0) return 1;
                    else return 0;
                }
            }
        }
    }    
}
