package srs;

import common.SocketProccessor;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
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
            logger.error("mocos wey");
            new SocketProccessor().startServer();

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
            logger.error("*** programm finished ***");
        }
    }; // end shutdownHook
} // end class
