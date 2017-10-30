
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class to create World and process command line visualisation 
 * thereof.
 * 
 * Programme to implement and display a command line visualisation of a cellular 
 * automata simulation containing two self-reproducing types of agents with 
 * differing attributes which can exist, reproduce and destroy one another;
 * agents share a common world (stored in heap memory) but each have independent 
 * threads.
 * 
 * N.B. simulation will run in an infinite loop.
 * 
 * @author Aidan Butler - 2281611B
 */
public class TestWorld {
    /**
     * Main class to create World and process command line visualisation 
     * thereof. 
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        World world = new RoundWorld(); // Comment out for roundworld mode
        //World world = new FlatWorld(); // Uncomment for flatworld mode
        
        while(true) {
        
            try {
                
                Thread.sleep(500);
                System.out.println(world);              
                
            } catch (InterruptedException ex) {
                
                Logger.getLogger(TestWorld.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
