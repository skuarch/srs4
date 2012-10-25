package srs;

import common.SocketProccessor;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Main class that contains main method.
 * @author skuarch
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    //==========================================================================
    /**
     * create a instance
     */
    public Main() {
    } // end Main

    //==========================================================================
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PropertyConfigurator.configure("configuration/log4j.properties");
        Runtime.getRuntime().addShutdownHook(new Main().shutdownHook);

        try {

            logger.info("*** start program ***");            
            new SocketProccessor().startServer(8081);

        } catch (Exception e) {
            logger.error(e);
        }
    } // end main
    
    //==========================================================================
    /**
     * display a message before the program ends
     */
    private Thread shutdownHook = new Thread() {
        @Override
        public void run() {
            System.out.println("*** programm finished ***");
            logger.error("*** programm finished ***");
        }
    }; // end shutdownHook
} // end class
