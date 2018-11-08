/**
 * Student Class. A class to build a constructor for a Student
 * including his/her first name, last name and iD.
 * 
 * @author Xiaoshi Wang
 * @version April 18, 2017
 */
public class Student
{
    // instance variables 
    private String firstName;
    private String lastName;
    private long iD;

    /**
     * Constructor for objects of class Student
     */
    public Student(String firstName, String lastName, long iD)
    {
        // initialise instance variables
        this.firstName = firstName;
        this.lastName = lastName;
        this.iD = iD;
    }

    /**
     * return the first name
     * 
     * @return     the first name of this student
     */
    public String getFirst()
    {
        return firstName;
    } 

    /**
     * return the last name
     * 
     * @return     the last name of this student
     */
    public String getLast()
    {
        return lastName;
    }

    /**
     * return the iD
     * 
     * @return     the iD of this student
     */
    public long getID()
    {
        return iD;
    }
}