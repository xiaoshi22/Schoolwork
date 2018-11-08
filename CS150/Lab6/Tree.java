/**
 * interface Tree. Operations needed in BinaryTree and 
 * BinarySearchTree are included in this interface.
 * 
 * @author  Xiaoshi Wang 
 * @version March 19, 2017
 */
public interface Tree<E>
{
    boolean insert(E e);

    boolean contains(E e);

    int size();

    boolean remove(E e);

    E findMax();

    E findMin();

    void printPreOrder();

    void printPostOrder();

    void printInOrder();

    void empty();

    boolean isEmpty();
}