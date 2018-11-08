/**
 * Class BinarySearchTree.
 * 
 * @author Xiaoshi Wang
 * @version March 19, 2017
 */
public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E>
{
    // instance variables 
    /** Constructor for objects of class BinarySearchTree */
    public BinarySearchTree(E rootItem){
        // initialise instance variables
        super(rootItem);
    }

    /** insert an element in the binary search tree */
    public boolean insert(E e){
        if (contains(e)) return false;
        root = insert(e, root);
        return true;
    }

    /** check whether an element is in the binary search tree or not*/
    public boolean contains(E e){
        return contains(e, root);
    }

    /** remove an element in the binary search tree */
    public boolean remove(E e){
        if (!contains(e)) return false;
        root = remove(e, root);
        return true;
    }

    /** find the maximun element in the binary search tree */
    public E findMax(){
        return findMax(root).element;
    }

    /** find the minimun element in the binary search tree */
    public E findMin(){
        return findMin(root).element; 
    }

    // serveral help methods for better use of recursions 
    /** recursively find a right place to insert an element to a node */
    private BinaryNode<E> insert(E e, BinaryNode<E> t){
        // if the node is null, new node is inserted
        if (t == null) t = new BinaryNode<E>(e, null, null);
        else if (e.compareTo(t.element)<0)  t.left = insert(e, t.left);
        else if (e.compareTo(t.element)>0) t.right = insert(e, t.right);
        return t; // insertion fails if we meet some equivalent elements
    }

    /** recursively find whether a node contains a certain element or not */
    private boolean contains(E e, BinaryNode<E> t){
        // if the node is null, e is definitely not in this node
        if (t == null) return false; 

        int compareResult = e.compareTo(t.element);
        if (compareResult<0) return contains(e, t.left); // recrusively search the left node
        else if (compareResult>0) return contains(e, t.right); // recrusively search the right node
        else return true; 
    }

    /** recursively find a right place of the min for a node and remove it */
    private BinaryNode<E> removeMin(BinaryNode<E> t){
        if (t == null) return t; // empty node has no min to remove
        else if (t.left != null){  
            t.left = removeMin(t.left); 
            return t;
        } else return t.right; // the element is the min if the node has no left node
    }

    /** recursively find a right place to remove an element to a node */
    private BinaryNode<E> remove(E e, BinaryNode<E> t){
        // emety node has no elements to remove
        if (t == null) return t;

        int compareResult = e.compareTo(t.element);
        if (compareResult<0){
            t.left = remove(e, t.left); // recrusively call remove method for the left node
        }else if (compareResult>0){
            t.right = remove(e, t.right); // call for the right node
        }else{ // when we finally find the element
            if(t.left != null && t.right != null){ // if the node has two leaves
                t.element = findMin(t.right).element;
                t.right = removeMin(t.right);
            } else  // if the node has only one leaf
                t = ( t.left != null ) ? t.left : t.right;
        }
        return t;
    }

    /** recursively find the max of a node */
    private BinaryNode<E> findMax(BinaryNode<E> t){
        // empty node has no max
        if (t == null) return t; 

        if(t.right == null) return t; // return the element if it is the right most one
        else return findMax(t.right);
    }

    /** recursively find the min of a node */
    private BinaryNode<E> findMin(BinaryNode<E> t){
        // empty node has no min
        if (t == null) return t;

        if(t.left == null) return t; // return the element if it is the left most one
        else return findMin(t.left);
    }
}