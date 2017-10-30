
import java.util.ArrayList;

/**
 * Programme to implement and display a command line visualisation of a cellular 
 * automata simulation containing two self-reproducing types of agents with 
 * differing attributes which can exist, reproduce and destroy one another;
 * agents share a common world (stored in heap memory) but each have independent 
 * threads.
 * 
 * Abstract class to create a world containing a two dimensional array of nodes,
 * call an abstract method in inheriting classes to assign neighbours to each 
 * node and populate the world with Creatures and launches a separate thread
 * for each of them; can be called to provide a string representation
 * of the current world-state.
 * 
 * @author Aidan Butler - 2281611B
 */
public abstract class World {
    
    protected final Node[][] nodes;
    protected final int height;
    protected final int width;
    protected final int[][] oneSpaceMoves;

    /**
     * Constructor to creates a world with initial default dimension.
     */
    protected World() {
        
        this(10, 30);
    }
    
    /**
     * Constructor to create a world with given dimensions, calls methods to 
     * generate nodes, assign neighbours to them and populate the world.
     * 
     * @param h
     * @param w 
     */
    protected World(int h, int w) {
        
        this.height = h;
        this.width = w;
        this.nodes = new Node[h][w];
        this.oneSpaceMoves = generateOneSpaceMoves();
        this.generateNodes();
        this.assignNeighbours();
        this.populate();
    }
    
    /**
     * Creates creatures and assigns them to nodes.
     */
    protected void populate() {
    
        Node n = nodes[0][0];
        Creature c = new Species1(n);
        Thread t = new Thread(c);
        t.start();  
        n.setResident(c);

        Node n2 = nodes[4][14];
        Creature c2 = new Species2(n2);
        Thread t2 = new Thread(c2);
        t2.start();  
        n2.setResident(c2);    
    }
    
    /**
     * Generates a two-dimensional integer array containing the relative
     * (x,y) co-ordinates of all of a nodes surrounding nodes (including 
     * itself).
     * 
     * @return moves, int[][] 
     */
    protected int[][] generateOneSpaceMoves() {
    
        int numDirections = 9;
        int numDimensions = 2;
        int[][] moves = new int[numDirections][numDimensions];
        
        moves[0][0] = -1;
        moves[0][1] = 1;
        
        moves[1][0] = 0;
        moves[1][1] = 1;        
        
        moves[2][0] = 1;
        moves[2][1] = 1;         

        moves[3][0] = -1;
        moves[3][1] = 0; 

        moves[4][0] = 0;
        moves[4][1] = 0;         

        moves[5][0] = 1;
        moves[5][1] = 0;         
        
        moves[6][0] = -1;
        moves[6][1] = -1; 
        
        moves[7][0] = 0;
        moves[7][1] = -1;
        
        moves[8][0] = 1;
        moves[8][1] = -1;
        
        return moves;
    }
    
    /**
     * Generates nodes matching the world's height and width attributes.
     */
    protected final void generateNodes() {
        
        for (int i = 0; i < nodes.length; i++) {
            
            for (int j = 0; j < nodes[0].length; j++) {
                
                Node n = new Node(i, j);
                this.nodes[i][j] = n;
            }            
        }
    }
        
    /**
     * Assigns neighbouring nodes to each of the world's nodes by calling an
     * abstract method which each concrete world instance must implement.
     */
    protected void assignNeighbours() {
        
        for (int i = 0; i < this.height; i++) { 
            
            for (int j = 0; j < this.width; j++) {
            
                Node n = nodes[i][j];
                ArrayList<Node> neighbours = identifyNeighbours(n);
                n.setNeighbourNodes(neighbours);
            }
        }
    }    
    
    /**
     * Identifies the neighbouring nodes of a given node.
     * 
     * @param n, Node
     * @return nodes[][]
     */
    protected abstract ArrayList<Node> identifyNeighbours(Node n);
    
    /**
     * Returns a node with given grid co-ordinates.
     * 
     * @param horiz
     * @param vert
     * @return Node
     */
    protected Node getNodeAt(int horiz, int vert) {
       
        return nodes[horiz][vert];
    }

    /**
     * Returns a String representation of the current world-state.
     * 
     * @return worldState, string
     */
    @Override
    public String toString() {
        
        String worldState = "";
        
        for (int i = 0; i < nodes.length; i++) {
            
            for (int j = 0; j < nodes[0].length; j++) {
                
                worldState += getNodeAt(i, j).toString() + " ";
            }
            
            worldState += "\n";
        }
        
        return worldState;
    }            
}
