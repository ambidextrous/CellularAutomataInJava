
import java.util.ArrayList;

/**
 * 
 * Class representing a node with a vertical and horizontal position, an
 * arraylist of neighbouring nodes and an asigned Creature which can be null
 * (if the node is unoccupied) or non-null (if the node is occupied).
 * 
 * Programme to implement and display a command line visualisation of a cellular 
 * automata simulation containing two self-reproducing types of agents with 
 * differing attributes which can exist, reproduce and destroy one another;
 * agents share a common world (stored in heap memory) but each have independent 
 * threads.
 * 
 * 
 * @author Aidan Butler - 2281611B
 */
public class Node {
    
    private final int horiz;
    private final int vert;
    private ArrayList<Node> neighbourNodes;
    private Creature resident;

    /**
     * Constructor which creates a Node, assigns it given horizontal and 
     * vertical co-ordinates, sets its resident to null (unoccupied) and 
     * assigns it an arraylist of neighbouring Nodes.
     * 
     * @param h
     * @param v 
     */
    protected Node(int h, int v) {
        
        this.horiz = h;
        this.vert = v;
        this.resident = null;
        this.neighbourNodes = new ArrayList<>();
    }

    /**
     * Returns the Node's vertical position.
     * 
     * @return int 
     */
    protected int getVerticalPos() {
        
        return this.horiz;
    }

    /**
     * Returns the Node's
     * 
     * @return int
     */
    protected int getHorizontalPos() {
        
        return this.vert;
    }

    /**
     * Assigns a given arraylist of Nodes as the Node's neighbours.
     * 
     * @param neighbourNodes 
     */
    protected void setNeighbourNodes(ArrayList<Node> neighbourNodes) {
        
        this.neighbourNodes = neighbourNodes;
    }
    
    /**
     * If empty, returns a given String representing the empty Node; 
     * otherwise, returns the toString method of Node's resident Creature.
     * 
     * @return 
     */
    @Override
    public String toString() { 
        
        String emptySymbol = "-";
        
        if (resident == null) {

            return emptySymbol;
            
        } 
        
        return resident.toString();
    }
    
    /**
     * Sets the Node's resident to null.
     */
    protected void setEmpty() { 
    
        this.resident = null;
    }
    
    /**
     * Returns true if Node is currently empty (has no resident Creature).
     * 
     * @return whether Node is empty, boolean
     */
    protected boolean isEmpty() { 
    
        return this.resident == null;
    }
    
    /**
     * Sets Node's resident to a given Creaure.
     * 
     * @param c, Creature 
     */
    protected void setResident(Creature c) { 
        
        this.resident = c;
    }

    /**
     * Returns the resident Creature of a node or null, if no resident is 
     * present.
     * 
     * @return resident, Creature (or null)
     */
    protected Creature getResident() {
        
        return resident;
    }
    
    /**
     * Returns a Node arraylist of the Node's residents: thread safe method. 
     * 
     * @return neighbourNodes, Node arraylist
     */
    protected synchronized ArrayList<Node> getNeighbourNodes() { 
        
        return this.neighbourNodes;
    }
}
