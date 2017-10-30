
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
public class Species1 extends Creature {
    
    protected Species1(Node node) {
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
        
        int maxLifetime = 10000; //Recommended value 10000
        Random r = new Random();
        
        return r.nextInt(maxLifetime);
    }

    /**
     * Returns a String used to represent Creature's name. 
     * 
     * @return
     */
    @Override
    protected String setName() {
        
        return "1"; 
    }

    /**
     * Returns a fitness value, used to set Creature's fitness attribute. 
     * 
     * @return fitness, double
     */
    @Override
    protected double setFitness() {
        
        double fitness = 0.8;
        
        return fitness;
    }

    /**
     * Creates a new Species1 instance using a given Node and returns it.
     * 
     * @param n, Node
     * @return Species1
     */
    @Override
    protected Creature spawn(Node n) {
        
        return new Species1(n);
    }
}
