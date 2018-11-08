/**
 * class BinaryNode. A class to create a binary node with a 
 * element, the left node and the right node. Serveral oprations
 * are included in this class: return the size, print pre order,
 * post order and in order.
 * 
 * @author Xiaoshi Wang
 * @version March 19, 2017
 */
/** a class for Node */
public  class BinaryNode<E>{
    protected E element;
    protected BinaryNode<E> left;
    protected BinaryNode<E> right;

    /** constructor */
    public BinaryNode(){
        this(null, null, null);
    }

    public BinaryNode(E theElement, BinaryNode<E> lt, BinaryNode<E> rt){
        element = theElement;
        left = lt;
        right = rt;
    }

    /**  return the size of the binary node */
    public int size(){
        return size(this);
    }

    /** print elements of the binary node in pre Order */
    public void printPreOrder(){
        System.out.println(element);
        if( left != null ) left.printPreOrder(); 
        if( right != null ) right.printPreOrder(); 
    }

    /** print elements of the binary node in post Order */
    public void printPostOrder(){
        if( left != null ) left.printPostOrder(); 
        if( right != null ) right.printPostOrder();
        System.out.println(element);
    }

    /** print elements of the binary node in Order */
    public void printInOrder(){
        if( left != null ) left.printInOrder(); 
        System.out.println(element);
        if( right != null ) right.printInOrder();
    }

    /**  return the size of a certain binary node */
    private int size(BinaryNode<E> t){
        if (t == null) return 0;
        else return 1+size(t.left)+size(t.right); // add up the size of left node and right node and the element itself
    }
}