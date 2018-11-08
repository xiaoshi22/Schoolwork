import java.util.Stack;

/**
 * Heap class. In this class, we use the binary tree to implement
 * heap. Methods include insert data into the correct location of 
 * the Heap and remove the root of the heap and returns its value.
 * 
 * @author  Xiaoshi Wang
 * @version April 3, 2017
 */
public class Heap<E extends Comparable<? super E>> extends BinaryTree<E>
{
    // instance variables 
    /**
     * Constructor for objects of class Heap
     */
    public Heap(E rootItem)
    {
        // initialise instance variables
        super(rootItem);  
    }
    
    /** insert data into the correct location of the Heap  */
    public void insert(E data){
        // if the tree is empty
        if (root.element == null){
            root.element = data;
            return;
        }
        
        Stack<Boolean> dirs = getDirections(size()+1); // find the path to the new node
        insert(root, dirs, data); // call insert method
    }

    /** remove the root of the heap and returns its value */
    public E removeMin(){
        // first, record the minmum
        E min = root.element;
        
        // consider the extreme case
        if (size() == 1){
            empty();
            return min;
        }
        getTheLast(); // replace the root by our last node
        sink(root); // and sink it

        return min; 
    }
    
    /** return the path from the root to the nth node */
    private Stack<Boolean> getDirections(int n){
        Stack<Boolean> goLeft = new Stack<Boolean>();
        if(n==1)
            goLeft.push(true);
        while(n>=2){
            if(n%2==0)
                goLeft.push(true);
            else
                goLeft.push(false);
            n=n/2;   
        }
        return goLeft;
    }
    
    /** recrusively insert the data into a binary node */
    private void insert(BinaryNode<E> curr, Stack<Boolean> dirs, E data){    
        // stop call insert method when we reach the leaf
        if (dirs.empty()) return; 
        if (dirs.pop()){ // if the path goes left
            // recusively call the insertion to the left child if the left child exists
            if (curr.left == null) curr.left = new BinaryNode<E>(data, null, null); 
            insert(curr.left, dirs, data);

            // swap the elements in these two nodes if we haven't reach the right position
            if (data.compareTo(curr.element)<0) swap(curr, curr.left);
            else return;
        }else{ // if the path goes right
            // recusively call the insertion to the right child if the right child exists
            if (curr.right == null) curr.right = new BinaryNode<E>(data, null, null);
            insert(curr.right, dirs, data);

            // swap the elements if we haven't reach the right position
            if (data.compareTo(curr.element)<0) swap(curr, curr.right);
            else return;
        }
    }
    
    /** swap the values of elements in two node */
    private void swap(BinaryNode<E> a, BinaryNode<E> b){
        E temp = a.element;
        a.element = b.element;
        b.element = temp;
    }
    
    /** replace the root by our last node */
    private void getTheLast(){
        Stack <Boolean> dirs = getDirections(size());
        BinaryNode<E> last = root;
        BinaryNode<E> secondlast = root;

        while (!dirs.empty()){
            secondlast = last;
            if (dirs.pop()) last = last.left;
            else last = last.right;
        }

        swap(root, last);

        if (last == secondlast.left) secondlast.left = null;
        else secondlast.right = null;
    }

    /** sink a node down (recrusively) until the conditions for the heap satisfy */
    private void sink(BinaryNode<E> curr){
        // return if curr is a leaf
        if ( curr.left == null) return; 

        // if curr has only one child
        if (curr.right == null){
            if (curr.left.element.compareTo(curr.element)<0) swap(curr.left, curr); // swap itself with its child if it is smaller
            return;
        }

        // if curr has two children, swap the smaller child with curr and recursively sink that child
        if (curr.left.element.compareTo(curr.right.element)<0){ 
            if (curr.left.element.compareTo(curr.element)>0) return;
            swap(curr.left, curr);
            sink(curr.left);
        }else{
            if (curr.right.element.compareTo(curr.element)>0) return;
            swap(curr.right, curr);
            sink(curr.right);
        }
    }
}