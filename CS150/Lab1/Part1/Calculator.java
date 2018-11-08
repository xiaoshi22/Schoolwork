import java.util.Scanner;

/**
 * Calculator.java <p>
 * 
 * This program will ask for the numbers and operation in the terminal. The result of the 
 * calculation would be shown after inputs are all typed.  <p>
 * 
 * @author Xiaoshi Wang
 */

public class Calculator{
    /** the main method */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run(); // call run method
    }

    /** the run method */
    public void run() {
        // variables
        Scanner reader = new Scanner(System.in); // read content from System.in
        int numberOfCalculations; // the total number of calculations that would be proceeded
        float firstNumber; // value of the first number
        String operation = null; // type of operation
        float secondNumber; // value of the second number
        float answer; // value of the answer

        try {
            System.out.print("number of calculations:"); // print "number of calculations:"
            numberOfCalculations = reader.nextInt();  // read next integer

            while(numberOfCalculations > 0) { // while there are stil some calculations left 
                System.out.print("First number:");
                firstNumber = reader.nextFloat(); // save the first number 

                System.out.print("Operation:");
                operation = reader.next(); // save the operation

                System.out.print("Second number:");
                secondNumber = reader.nextFloat(); // save the second number 

                if (operation.equals("plus")){ // if the operation is addition
                    answer = firstNumber + secondNumber; // we add two numbers
                    System.out.println(firstNumber+ "+" +secondNumber+ "=" +answer);
                } else if (operation.equals("minus")){ // if the operation is subtraction
                    answer = firstNumber - secondNumber; // we proceed the subtraction
                    System.out.println(firstNumber+ "-" +secondNumber+ "=" +answer);
                } else {
                    System.out.println("undefined operator"); // operations except addtion and subtraction are undefined
                }

                numberOfCalculations = numberOfCalculations - 1; // we reduce our counter by 1 whenever we finish one calculation
            }
        } catch (Exception e) {
            System.out.println( "Exception occured " + e);
        }
    }
}