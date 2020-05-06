import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;


public class EchoServer extends Thread {
    private int port;
    private ServerSocket ss;
    private int ID;




    // This is the constructor for the Server. This assigns the port value passed by the argument and initiates the run function
    public EchoServer(int port) {

        try {
            System.out.println("Waiting for clients...");
            this.port = port;
            // Creates a new Server Socket for the clients to connect to
            ss = new ServerSocket(port);
            ID=0;

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void run() {
        ServerCmdReader serverinp = new ServerCmdReader();
        serverinp.start();

        try
        {
            while(true) {
                // listen for client, blocks waiting for the client to be found and names the socket that is connected with the client before progressing code
                Socket client = ss.accept();
                ID++;
                new ServerClientOrganiser(client, "client-" + ID).start();

            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }


}
