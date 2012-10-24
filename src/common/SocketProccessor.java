package common;

import java.net.ServerSocket;
import org.apache.log4j.Logger;
import util.IOUtilities;

/**
 * start a server and relegate the socket to RequestDispatcher
 *
 * @author skuarch
 */
public class SocketProccessor {

    private static final Logger logger = Logger.getLogger(SocketProccessor.class);

    //==========================================================================
    /**
     * create a instance
     */
    public SocketProccessor() {
    }

    //==========================================================================
    /**
     * start to listen a socket.
     */
    public void startServer() {

        ServerSocket serverSocket = null;

        try {

            serverSocket = new ServerSocket(8081);
            logger.info("listening on port " + 8081);

            while (true) {

                //dispatch the client in another thread for continue listening
                new Thread(new RequestDispatcher(serverSocket.accept())).start();

            }

        } catch (Exception e) {
            logger.error(e);
        } finally {
            IOUtilities.closeServerSocket(serverSocket);
        }

    } // end startServer
} // end class
