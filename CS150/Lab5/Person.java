
/**
 * an abstract class Person which has instance variables for the 
 * first and last name. Methods to get firstName and lastName 
 * are included in this class.
 * 
 * @author Xiaoshi Wang
 * @version Feb 27, 2017
 */

public abstract class Person {
    // instance variables 
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName){
        // initialise instance variables
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** return the firstName */
    public String getFirstName(){
        return firstName;
    }

    /** return the lastName */
    public String getLastName(){
        return lastName;
    }
}