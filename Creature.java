/**
 * Abstract, threadable class which implements the Runnable interface 
 * representing a Creature agent which has name, lifetime and fitness 
 * attributes (which are set by inheriting classes) and a node attribute: 
 * when run the Creature will wait for the period of its lifetime before
 * reproducing in surrounding nodes and interrupting (murdering) Creatures in
 * those nodes in a randomised process based on the Creatures' respective 
 * fitness attribute; if interrupted (murdered) during its lifetime the 
 * Creature not reproduce. 
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
public abstract class Creature implements Runnable {
    
    protected final String name;
    protected final int lifeTime;
    protected final double fitness;
    protected final Node node;
    
    /**
     * Constructor which creates a new Creature at given Node and calls 
     * abstract methods to set the name, lifeTime and fitness attributes of 
     * inheriting concrete Creature object instances.
     * 
     * @param node, Node 
     */
    protected Creature(Node node) {
    
        this.name = setName();
        this.lifeTime = setLifetime();
        this.fitness = setFitness();
        this.node = node;
    }
    
    /**
     * Abstract method to return Creature lifetime, implemented by inheriting
     * classes.
     * 
     * @return lifetime, int
     */
    protected abstract int setLifetime();
    
    /**
     * Abstract method to return Creature name, implemented by inheriting
     * classes.
     * 
     * @return name, String
     */
    protected abstract String setName();
    
    /**
     * Abstract method to return Creature fitness, implemented by inheriting
     * classes.
     * @return 
     */
    protected abstract double setFitness();
    
    /**
     * Abstract method to return Creature name, implemented by inheriting
     * classes.
     * 
     * @param n, Node
     * @return Creature
     */
    protected abstract Creature spawn(Node n);

    /**
     * Accessor method to return Creature name.
     * 
     * @return name, String
     */
    protected String getName() {
        
        return this.name;
    }
    
    /**
     * Accessor method to return Creature fitness.
     * 
     * @return fitness, double
     */
    private double getFitness() {
        
        return this.fitness;
    }    
    
    /**
     * Threadable run method which waits for duration of Creature's lifetime
     * before reproducing; if interrupted Thread will terminate and 
     * Creature will not reproduce.
     */
    @Override
    public void run() {    

        try {
            
            Thread.sleep((long) this.lifeTime);
            reproduce();

        } catch (InterruptedException ex) {

            Thread.currentThread().interrupt(); 
        }
    }
    
    /**
     * Sets creature's node as empty; calls method to reproduce in own and 
     * neighbouring nodes; terminates Creature Thread.
     */
    protected void reproduce() {
        
        this.node.setEmpty();
        reproduceInIndividualNodes();
        this.getThread().interrupt();
    } 
    
    /**
     * Calls spawn method to get a Creature and passes a given Node to it as a
     * parameter; starts a new Thread and passes that Creature to it as a 
     * parameter; starts thread; sets Creature as Node resident. 
     * 
     * @param n, Node 
     */
    protected void populateNode(Node n) {
            
        Creature c = spawn(n);
        Thread t = new Thread(c);
        t.start();  
        n.setResident(c);                
    }
    
    /**
     * Cycles through Node occupied by Creature and those surrounding it, 
     * then locks each Node before either (1) calling a method to 
     * populate the Node if a randomised value is lower than the Creature's 
     * fitness level, if the Node is unoccupied or (2) murdering the
     * Node's resident creature and calling a method to populate the Node if
     * a random value is lower than this Creature's fitness score minus the
     * other Creature's fitness score.
     */
    private void reproduceInIndividualNodes() {
            
        for (Node neighbourNode : this.node.getNeighbourNodes()) {

            synchronized (neighbourNode) {

                if (neighbourNode.isEmpty()) {

                    if (Math.random() <= fitness) {

                        populateNode(neighbourNode);

                    } 

                } else {

                    Creature other = neighbourNode.getResident();

                    if (Math.random() <= fitness - other.getFitness()) {

                        Creature c = neighbourNode.getResident();
                        murder(c);
                        populateNode(neighbourNode);                                

                    }
                }
            }
        }        
    }
    
    /**
     * Interrupts the Thread of a given Creature, terminating it.
     * 
     * @param c, Creature
     */
    private void murder(Creature c) {

        c.getThread().interrupt();
    }
    
    /**
     * Returns the Creature's Thread.
     * 
     * @return Thread
     */
    protected Thread getThread() {
        
        return Thread.currentThread();
    }
    
    /**
     * Returns Creature's name, a String.
     * 
     * @return name, String 
     */
    @Override
    public String toString() {
        
        return this.name;
    }
}
