
import java.util.ArrayList;

/**
 * Class to create a concrete representation of the World class from which it
 * inherits, assigning the following two groups of nodes as neighbours to each
 * node: 1 - the node itself; 2 - any immediately touching nodes (including 
 * diagonals).
 * 
 * Programme to implement and display a command line visualisation of a cellular 
 * automata simulation containing two self-reproducing types of agents with 
 * differing attributes which can exist, reproduce and destroy one another;
 * agents share a common world (stored in heap memory) but each have independent 
 * threads.
 * 
 * @author Aidan Butler - 2281611B
 */
public class FlatWorld extends World {
    
    /**
     * Constructor which calls the constructor of the supper-class, passing
     * it give height and width parameters.
     * 
     * @param h
     * @param w 
     */
    protected FlatWorld(int h, int w) {
        super(h, w);
    }

    /**
     * Constructor which calls the default constructor of the super-class.
     */    
    protected FlatWorld() {
    }
    
    /**
     * Assigning the following two groups of nodes as neighbours to each
     * node: 1 - the node itself; 2 - any immediately touching nodes (including 
     * diagonals).
     * 
     * @param n
     * @return 
     */
    @Override
    protected ArrayList<Node> identifyNeighbours(Node n) {
		
        ArrayList<Node> validNeighbours = new ArrayList<>();
        
        for (int[] oneSpaceMove : this.oneSpaceMoves) {
            
            boolean validNeighbour = true;

            int hCoordinate = 0;
            int neighbouringH = (n.getVerticalPos() + oneSpaceMove[hCoordinate]);		
            int wCoordinate = 1;
            int neighbouringW = (n.getHorizontalPos() + oneSpaceMove[wCoordinate]);			

            if (neighbouringH == -1) {

                validNeighbour = false;

            } else if (neighbouringH == this.height) {

                validNeighbour = false;
            }

            if (neighbouringW == -1) {

                validNeighbour = false;

            } else if (neighbouringW == this.width) {

                validNeighbour = false;
            }                        

            if (validNeighbour) {
            
                Node neighbour = this.getNodeAt(neighbouringH, neighbouringW);
                validNeighbours.add(neighbour);                  
            }          
        }
        
        return validNeighbours;
    }    
}
