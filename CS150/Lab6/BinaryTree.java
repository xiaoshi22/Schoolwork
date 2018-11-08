
/**
 * Abstract class BinaryTree. A class for a general binary tree
 * that can only implement some of the methods.
 * 
 * @author Xiaoshi Wang
 * @version March 19, 2017
 */
public abstract class BinaryTree<E> implements Tree<E>
{
    // instance variables 
    protected BinaryNode<E> root;

    /** constructor */
    public BinaryTree(){
        root = null;
    }

    public BinaryTree(E rootItem){
        root = new BinaryNode<E>(rootItem, null, null);
    }

    /** return the size of the binary tree */
    public int size(){
        return root.size();
    }

    /** print elements of the binary tree in pre Order */
    public void printPreOrder(){
        if (root!=null) root.printPreOrder();
    }

    /** print elements of the binary tree in post Order */
    public void printPostOrder(){
        if (root!=null) root.printPostOrder();
    }

    /** print elements of the binary tree in Order */
    public void printInOrder(){
        if (root!=null) root.printInOrder();
    }

    /** make the binary tree empty */
    public void empty(){
        root = null;
    }

    /** check wether the binary tree is empty or not */
    public boolean isEmpty(){
        return root == null;
    }
}