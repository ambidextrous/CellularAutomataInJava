
import java.util.Random;

/**
 * Threadable class which extends Creature: contains methods to return values 
 * used to set the the Creature's name, lifetime and fintess and a spawn method
 * returning a Species1 instance used to produce a new Creature. 
 * 
 * Programme to implement and display a command line visualisation of a cellular 
 * automata simulation containing two self-reproducing types of agents with 
 * differing attributes which can exist, reproduce and destroy one another;
 * agents share a common world (stored in heap memory) but each have independent 
 * threads.
 * 
 * @author Aidan Butler - 2281611B
 */
public class Species2 extends Creature {

    protected Species2(Node node) {
        super(node);
    }    
    
    /**
     * Returns a Random integer between 0 and maxLifetime, used to set 
     * Creature lifetime.
     * 
     * @return int
     */
    @Override
    protected int setLifetime() {
        Random r = new Random();
        return r.nextInt(5000);//Advised value 5000
    }

    /**
     * Returns a String used to represent Creature's name. 
     * 
     * @return
     */
    @Override
    protected String setName() {
        
        return "2"; 
    }

    /**
     * Returns a fitness value, used to set Creature's fitness attribute. 
     * 
     * @return fitness, double
     */    
    @Override
    protected double setFitness() {
        return 0.4;
    }

    /**
     * Creates a new Species2 instance using a given Node and returns it.
     * 
     * @param n, Node
     * @return Species2
     */    
    @Override
    protected Creature spawn(Node n) {
        return new Species2(n);
    }
}
