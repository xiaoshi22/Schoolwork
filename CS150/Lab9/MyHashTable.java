import java.util.LinkedList;
import java.util.Iterator;

/**
 * MyHashTable class. Construct a myHashTable to store students
 * in the form of hashTable. Methods insert, contain and remove
 * are manipulated in this class.
 * 
 * @author Xiaoshi Wang
 * @version April 18, 2017
 */
public class MyHashTable
{
    // instance variables 
    private LinkedList<Student>[] array;

    /**
    * Constructor for objects of class MyHashTable
    */
    @SuppressWarnings("unchecked")
    public MyHashTable(int n) 
    {
        // initialise instance variables  
        array = new LinkedList[n];
        // initialise every linkedList in the array
        for (int i=0; i<n; i++){
            array[i] = new LinkedList<Student>();
        }
    }

    /**
     * A method to insert a student using the student ID as the key.
     * 
     * @param  student   the student we want to insert.
     * @return     return true if insertion was successful or false if the student ID already exists.
     */
    public boolean insert(Student student)
    {
        // determine the target linkedlist we want to add our student into
        Long key = (Long)student.getID();
        LinkedList<Student> list =  array[Math.abs(key.hashCode() % array.length)];

        // use an iterator to track all elements in this linkedList, search wether this student exists or not
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getID() == student.getID()) return false;
        }
        list.add(student); // if not, add this student
        return true;
    }

    /**
     * A method which given an ID returns the student with that ID.
     * 
     * @param  iD  the iD we are looking for.
     * @return the student with that ID or null if no such student exists.    
     */
    public Student contain(long iD)
    {
        // determine the target linkedlist we want to find the given iD
        Long key = (Long)iD;
        LinkedList<Student> list =  array[Math.abs(key.hashCode() % array.length)];

        // use an iterator to track all elements in this linkedList until we find the one we are looking for 
        Iterator<Student> iterator = list.iterator();
        Student student;
        while (iterator.hasNext()){
            student =iterator.next();
            if (student.getID() == iD) return student;
        }
        return null;
    }

    /**
     * A method which given an ID returns and removes the student with that ID 
     * 
     * @param  iD  the iD we want to remove.
     * @return     returns the student with the given ID or null if no such student exists
     */
    public Student remove(long iD)
    {
        // determine the target linkedlist we want to find the given iD
        Long key = (Long)iD;
        LinkedList<Student> list =  array[Math.abs(key.hashCode() % array.length)];

        // use an iterator to track all elements in this linkedList until we find the one we are looking for 
        Iterator<Student> iterator = list.iterator();
        Student student;
        while (iterator.hasNext()){
            student =iterator.next();
            if (student.getID() == iD){
                iterator.remove(); // then delete it
                return student;
            }
        }      
        return null;
    }
}