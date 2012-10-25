package common;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import org.apache.log4j.Logger;
import util.IOUtilities;

/**
 * Dispatch client.
 * @author skuarch
 */
public class RequestDispatcher implements Runnable {

    private static final Logger logger = Logger.getLogger(RequestDispatcher.class);
    private Socket socket = null;
    private InputStream inputStream = null;
    private ObjectInputStream objectInputStream = null;

    //==========================================================================
    /**
     * create a instance.
     * @param socket Socket
     */
    public RequestDispatcher(Socket socket) {
        this.socket = socket;
    }

    //==========================================================================
    @Override
    public void run() {

        if (socket == null || socket.isClosed()) {
            logger.error("socket is null or close");
            return;
        }

        try {

            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            
            System.out.println(objectInputStream.readObject());

        } catch (Exception e) {
            logger.error(e);
        }finally{
            IOUtilities.closeSocket(socket);
            IOUtilities.closeInputStream(inputStream);
            IOUtilities.closeInputStream(objectInputStream);
        }

    } // end run    
} // end class
