import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;


public class EchoServer extends Thread {
    private int port;
    private ServerSocket ss;
    private int ID;
    private static ArrayList<ServerClientOrganiser> clientlist = new ArrayList<>();



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
                ServerClientOrganiser sco = new ServerClientOrganiser(client, "client-" + ID);
                getClientList().add(sco);
                sco.start();

            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static synchronized ArrayList<ServerClientOrganiser> getClientList() {
        return clientlist;

    }

    public static void CloseClients() {
        try {

            int i;


            for (i = 0; i <= (getClientList().size()-1); i++) {


                getClientList().get(i).getSocket().close();
                System.out.println(getClientList().get(i).getClientName() + " on " + getClientList().get(i).getSocket() + " has been disconnected as part of controlled shut down");

            }


        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }

    public static void sendtoclients(String output) {
        try {

            int i;

            for (i = 0; i <= (getClientList().size()-1); i++) {
                PrintWriter outp = new PrintWriter(
                        getClientList().get(i).getSocket().getOutputStream(), true);
                outp.println(output);
                System.out.println(getClientList().get(i).getClientName() + " has received this via output stream: " + output);
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }

    public static void RemoveAClient(String name) throws IOException {


            int i;

            for (i = 0; i <= (getClientList().size() - 1); i++) {
                if (getClientList().get(i).getClientName() == name) {
                    System.out.println(getClientList().get(i).getClientName());
                    getClientList().get(i).getSocket().close();
                    getClientList().remove(i);

                }
            }

    }

}
