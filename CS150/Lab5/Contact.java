
/**
 * an class Contact which extend the Person class and implement 
 * the Comparable interface. Besides firstName and lastName,
 * we also include phoneNumber and email. Methods to get phoneNumber
 * and email are included in this class.
 * 
 * @author Xiaoshi Wang
 * @version Feb 27, 2017
 */
public class Contact extends Person implements Comparable<Contact>
{
    private String phoneNumber;
    private String email;

    /**
     * Constructor for objects of class Worker
     */
    public Contact(String firstName, String lastName, String phoneNumber, String email)
    {
        // initialise instance variables
        super(firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /** return phoneNumber */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /** return email */
    public String getEmail(){
        return email;
    }

    /** overwrite compareTo method */
    public int compareTo(Contact c){
        int i = this.getLastName().compareTo(c.getLastName()); // we first compare the lastName 
        if (i==0){
            i = this.getFirstName().compareTo(c.getFirstName()); // if the last names are the same, we compare first names
        }
        return i;
    }
}