package common;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import org.apache.log4j.Logger;
import util.IOUtilities;

/**
 * Dispatch client.
 *
 * @author skuarch
 */
public class RequestDispatcher implements Runnable {
    
    private static final Logger logger = Logger.getLogger(RequestDispatcher.class);
    private static int client = 0;
    private Socket socket = null;
    private InputStream inputStream = null;
    private ObjectInputStream objectInputStream = null;
    private OutputStream outputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    //==========================================================================
    /**
     * create a instance.
     *
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
        
        HashMap hashMap = null;
        String request = null;
        
        try {
            
            logger.info("attending client " + ++client);
            
            hashMap = (HashMap) receiveObject();
            request = (String) hashMap.get("request");

            //attending request
            if (request.equals("connectivity")) {
                sendObject(new Connectivity().connection());
            } else if (request.equalsIgnoreCase("bandwidth overtime bits")) {
                
            }
            
        } catch (Exception e) {
            logger.error(e);
        } finally {
            IOUtilities.closeInputStream(inputStream);
            IOUtilities.closeInputStream(objectInputStream);
            IOUtilities.closeOutputStream(outputStream);
            IOUtilities.closeOutputStream(objectOutputStream);
            IOUtilities.closeSocket(socket);
        }
        
    } // end run    

    //==========================================================================
    private Object receiveObject() {
        
        Object object = null;
        
        try {
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            object = objectInputStream.readObject();
        } catch (Exception e) {
            logger.error(e);
        }
        
        return object;
        
    } // end receiveObject

    //==========================================================================
    private void sendObject(Object object) {
        
        try {
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (Exception e) {
            logger.error(e);
        }
        
    } // end 
} // end class

