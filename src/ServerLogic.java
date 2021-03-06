import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerLogic extends Thread {
    private int port;
    private ServerSocket ss;
    private int ID;
    private static ArrayList<ServerClientOrganiser> clientlist = new ArrayList<>();
    private Socket client;



    // This is the constructor for the Server. This assigns the port value passed by the argument and initiates the run function
    public ServerLogic(int port) {

        try {

            this.port = port;
            // Creates a new Server Socket for the clients to connect to
            ss = new ServerSocket(port);
            System.out.println("Waiting for clients...");
            ID=0;
            start();

            while(true) {
                // listen for client, blocks waiting for the client to be found and names the socket that is connected with the client before progressing code
                client = ss.accept();
                ID++;
                ServerClientOrganiser sco = new ServerClientOrganiser(client, "client-" + ID);
                getClientList().add(sco);
                sco.start();

            }

        }
        catch (IllegalArgumentException iae) {
            System.out.println("The port value given to the server is not valid. The programme will now terminate");
            System.exit(1);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void run() {

        BufferedReader cmd = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            String userInput = cmd.readLine();


            while (true) {

                if (userInput.equals("EXIT")) {
                    System.out.println("Closing Server...");
                    CloseClients();
                    break;
                }
                userInput = cmd.readLine();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }

    public static synchronized ArrayList<ServerClientOrganiser> getClientList() {
        return clientlist;

    }

    public static void sendSingleClient(String message, int ID) {

        try {
            PrintWriter outp = new PrintWriter(
                    getClientList().get(ID).getSocket().getOutputStream(), true);
            outp.println(message);
            System.out.println(getClientList().get(ID).getClientName() + " has received this via output stream: " + message);
        }
        catch(IOException ioe) {
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
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static synchronized void RemoveAClient(String name) {

        try {

            int i;

            for (i = 0; i <= (getClientList().size() - 1); i++) {
                if (getClientList().get(i).getClientName().equals(name)) {

                    sleep(100);
                    System.out.println(name + " on " + getClientList().get(i).getSocket() + " has been disconnected");
                    getClientList().get(i).getSocket().close();
                    getClientList().remove(i);

                }
            }

        }
        catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();

        }

    }

    public static void CloseClients() {

            int i;


            for (i = 0; i <= (getClientList().size()-1); i++) {

                RemoveAClient(getClientList().get(i).getClientName());
                i--;

            }
    }

    public static boolean InputtedNameExists(String name) {

        int i;
        for (i = 0; i <= (ServerLogic.getClientList().size()-1); i++) {
            if (ServerLogic.getClientList().get(i).getClientName().equals(name)) {
                return true;
            }
        }
        return false;
    }



}
