package srs;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author skuarch
 */
public class Main {

    //==========================================================================
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PropertyConfigurator.configure("configuration/log4j.properties");

        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;

        try {

            serverSocket = new ServerSocket(8081);


            while (true) {
                socket = serverSocket.accept();

                inputStream = socket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);

                System.out.println(objectInputStream.readObject());


                try {
                    inputStream.close();
                    objectInputStream.close();
                    socket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end main
} // end class
