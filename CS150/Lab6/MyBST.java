import java.util.Scanner;

/**
 * Class MyBST. Follow the instruction shown on the terminal. 
 * Exit MyBST anytime after constructing the tree by typing 999.
 * 
 * @author Xiaoshi Wang
 * @version March 19, 2017
 */
public class MyBST
{
    // instance variables - replace the example below with your own
    public static void main(String[] args) {
        MyBST m = new MyBST();
        m.run();
    }

    public void run(){
        Scanner reader = new Scanner(System.in); // read content from System.in

        int numberOfElements;
        int operation;
        try {
            System.out.println("Create Your Own Binary Search Tree in MyBST!"+ System.getProperty("line.separator")+ System.getProperty("line.separator"));
            
            System.out.println("How many nodes are in your tree? (type an integer and press Enter) ");
            numberOfElements = reader.nextInt()-1;
            System.out.println("Come up an integer for your root: ");
            BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(reader.nextInt());
            while(numberOfElements > 0){
                System.out.print("One more node reqired: ");
                tree.insert(reader.nextInt());

                numberOfElements--;
            }
            System.out.println(System.getProperty("line.separator")+"Here is your tree printed using preorder traversal: ");
            tree.printInOrder();

            System.out.println( System.getProperty("line.separator")+"Notice that you can exit myBST anytime by typing 999."
                + System.getProperty("line.separator")+ System.getProperty("line.separator")+
                "Which capabilities would you like to test? Type its number." + System.getProperty("line.separator")+
                "1)insert, 2)contains, 3)size, 4)remove, 5)findMax, 6)findMin, " + System.getProperty("line.separator")+
                "7)printPreOrder, 8)printPostOrder, 9)printInOrder, "+ System.getProperty("line.separator")+
                "10)empty, 11)isEmpty, 999)exit MyBST.");

            outer:
            while (reader.hasNextInt()){
                operation = reader.nextInt();

                switch (operation){
                    case 1:
                    System.out.println("The number you want to insert is: ");
                    System.out.println("The result is: " + tree.insert(reader.nextInt()));
                    break;

                    case 2:
                    System.out.println("The number you want to check is: ");
                    System.out.println("The result is: " + tree.contains(reader.nextInt()));
                    break;

                    case 3:
                    System.out.println("The size is: " + tree.size());
                    break;

                    case 4:
                    System.out.println("The number you want to remove is: ");
                    System.out.println("The result is: " + tree.remove(reader.nextInt()));
                    break;

                    case 5:
                    System.out.println("The max is: " + tree.findMax());
                    break;

                    case 6:
                    System.out.println("The min is: " + tree.findMin());
                    break;

                    case 7:
                    System.out.println("Here is your tree printed using preorder traversal: ");
                    tree.printPreOrder();
                    break;

                    case 8:
                    System.out.println("Here is your tree printed using postorder traversal: ");
                    tree.printPostOrder();
                    break;

                    case 9:
                    System.out.println("Here is your tree printed using inorder traversal: ");
                    tree.printInOrder();
                    break;

                    case 10:
                    tree.empty();
                    System.out.println("You have already empty your tree.");
                    break;

                    case 11:
                    System.out.println("The answer is: " + tree.isEmpty());
                    break;

                    case 999:
                    System.out.println("Bye~");
                    break outer;

                    default:
                    System.out.println("Invalid Operation");
                }

                System.out.println(System.getProperty("line.separator")+System.getProperty("line.separator")+"Which capabilities would you like to test? Type its number." + System.getProperty("line.separator")+
                    "1)insert, 2)contains, 3)size, 4)remove, 5)findMax, 6)findMin, " + System.getProperty("line.separator")+
                    "7)printPreOrder, 8)printPostOrder, 9)printInOrder, "+ System.getProperty("line.separator")+
                    "10)empty, 11)isEmpty, 999)exit MyBST");
            }
        } catch (Exception e) {
            System.out.println( "Exception occured " + e);
        }
    }
}
