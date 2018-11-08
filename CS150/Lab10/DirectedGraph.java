import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Class DirectedGraph. There are two inner classes 
 * DirectedGraphNode and DirectedGraphEdge. The DirectedGraph 
 * class uses a list to keep track of the nodes in the graph and 
 * should support add method, getNeighbors method and breadthFirstClosest
 * method.
 * 
 * @author  Xiaoshi Wang
 * @version April 27, 2017
 */
public class DirectedGraph<K>
{
    private static class DirectGraphNode<K>
    {
        // instance variables 
        K key;    // The data in the node
        ArrayList<DirectGraphEdge<K>> edges; // a list of all edge for this node

        /**
         * Constructor for objects of class DirectedGraphNode
         */
        DirectGraphNode(K k)
        {
            // initialise instance variables
            key = k;
            edges = new ArrayList<DirectGraphEdge<K>>();
        }

        /**
         * Constructor for objects of class DirectedGraphNode
         */
        DirectGraphNode(K k, ArrayList<DirectGraphEdge<K>> e)
        {
            // initialise instance variables
            key = k;
            edges = e;
        }

        /**
         * return the key of closest node.
         * 
         * @return     the key of closest node.
         */
        public K closestNeighbor(){
            // null if this node is not connected with any other nodes
            if (edges.size()==0) return null;
            int weight = 999;
            DirectGraphNode<K> closest = this;
            for (DirectGraphEdge<K> edge: edges){ // find the node with the smallest weight
                if (edge.weight <= weight) {
                    weight = edge.weight;
                    closest = edge.endNode;
                }
            }

            return closest.key;
        }
    }

    private static class DirectGraphEdge<K>
    {
        // instance variables 
        DirectGraphNode<K> startingNode; 
        DirectGraphNode<K> endNode; 
        int weight;

        /**
         * Constructor for objects of class DirectedGraphEdge
         */
        DirectGraphEdge(DirectGraphNode<K> s, DirectGraphNode<K> e, int w)
        {
            // initialise instance variables
            startingNode = s;
            endNode = e;
            weight = w;
        }
    }

    // instance variable
    private ArrayList<DirectGraphNode<K>> nodes;

    /**
     * Constructor for objects of class DirectedGraph
     */
    public DirectedGraph()
    {
        // initialise instance variables
        nodes = new ArrayList<DirectGraphNode<K>>();
    }

    /**
     *  Add a node with key k.
     * 
     * @param  k   the key of our node
     * @return     true if we add successful, and false if not.
     */
    public boolean addNode(K k)
    {
        for (DirectGraphNode<K> node: nodes){
            if (k.equals(node.key)) return false; // return false if the key has already in our graph
        }

        nodes.add(new DirectGraphNode(k));
        return true;
    }

    /**
     * Add an edge from the node with key k1 to the node with 
     * key k2 and weight w. Returns true if the edge is 
     * successfully added. Edge should not be added if either 
     * nodes do not exist. If edge already exists, simply change 
     * its weight.
     * 
     * @param  k1  start node of our edge.
     * @param  k2  end node of our edge.
     * @param  w   the weight of our edge.
     * @return     true if we add successful, and false if not.
     */
    public boolean addEdge(K k1, K k2, int w)
    {
        DirectGraphNode<K> node1 = null;
        DirectGraphNode<K> node2 = null;

        // find the corresponding node with key 1
        for (DirectGraphNode<K> node: nodes){
            if (k1.equals(node.key)){
                node1 = node;
                break;
            }
        }

        // find the corresponding node with key 2
        for (DirectGraphNode<K> node: nodes){
            if (k2.equals(node.key)){
                node2 = node;
                break;
            }
        }

        // we cannot construct a edge if either of the node does not exist
        if (node1 == null || node2 == null) return false; 

        // updata the weight if the edge has been created
        for (DirectGraphEdge<K> edge: node1.edges){
            if (edge.endNode.key.equals(node2.key)){
                edge.weight = w;
                return true;
            }
        }

        node1.edges.add(new DirectGraphEdge(node1, node2, w));
        return true;
    }

    /**
     * returns an Arraylist containing all the neighbors k can 
     * reach in one hop.
     * 
     * @param  k   the key of the node we are interested in.
     * @return     the keys of all neighnors
     */
    public ArrayList<K> getNeighbors(K k) 
    {
        ArrayList<K> neighbors = new ArrayList<K>();

        for (DirectGraphNode<K> node: nodes){
            if (k.equals(node.key)){ // find the corrsponding node for this key
                for (DirectGraphEdge<K> edge: node.edges){
                    neighbors.add(edge.endNode.key); // get the neighbor of this node
                }
            }
        }
        return neighbors;
    }

    /**
     * prints out the closest neighbor each node in the graph 
     * can reach in one hop, using breadth first order.
     * 
     * @param  k1   the node we start with.
     */
    public void breadthFirstClosest(K k1)
    {
        // find the corrsponding node1 of k1
        DirectGraphNode<K> node1 = null;
        for (DirectGraphNode<K> node: nodes){
            if (k1.equals(node.key)){
                node1 = node;
                break;
            }
        }
        Queue<DirectGraphNode<K>> queue = new LinkedList<DirectGraphNode<K>>(); // create a queue to go through all nodes
        int[] distances = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) distances[i] = 999; // set all distance to be infinitely large
        queue.offer(node1);
        distances[nodes.indexOf(node1)] = 0;

        System.out.println(k1 +" " + node1.closestNeighbor());

        DirectGraphNode<K> node = null;
        while (queue.peek()!= null){
            node = queue.poll();
            for (DirectGraphEdge<K> ajacent: node.edges){
                if (distances[nodes.indexOf(ajacent.endNode)] == 999){ // if node has not been visited
                    distances[nodes.indexOf(ajacent.endNode)] = distances[nodes.indexOf(node)]+1;
                    System.out.println(ajacent.endNode.key +" " + ajacent.endNode.closestNeighbor());
                    queue.offer(ajacent.endNode);
                }
            }
        }
    }

    /**
     * return the keys of all nodes in the from of ArrayList in 
     * this graph.
     * 
     * @return     an arrayList containing the keys of all nodes.
     */
    public ArrayList<K> getNodes(){
        ArrayList<K> list = new ArrayList<K>();
        for (DirectGraphNode<K> node: nodes) list.add(node.key); // add all the keys
        return list;
    }
}
