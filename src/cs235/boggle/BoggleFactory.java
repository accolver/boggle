

package cs235.boggle;

public class BoggleFactory {

    /* Note to students:
     *  A "factory" in our definition is the class that will return the
     *  implementations of the interfaces that are requred for the project.
     *  Since we have this "factory" class, you are free to name the
     *  implementation files whatever you choose, just so long as you
     *  specify it here in the factory, so that it knows what it is called.
     */

    /**
     * Creates, initializes, and returns a new object that implements the BogglePlayer interface.
     * 
     * @return a reference to the new BogglePlayer object
     */ 
    public static BogglePlayer createBogglePlayer() {
    	String[] dictionary;
    	BogglePlayer bogglePlayer = new BogglePlayerImpl ();
	return bogglePlayer;
    }
    
    
    
    
   /* public static MindReader createMindReader() {
    	Map map = new HashMap();
    	List list = new LinkedList();
    	MindReader mindReader = new MindReaderImpl (map, list, 0, 0);
	return mindReader;
    }
    */
    
    
    
    

}

