import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClientOrganiser extends Thread {

    private Socket s;
    private String name;
    private static ArrayList<ServerClientOrganiser> clientlist = new ArrayList<>();
    private static boolean exit = false;

    public ServerClientOrganiser(Socket s, String name){
        this.s = s;
        this.name = name;
        clientlist.add(this);


    }

    public static void setExit() { exit = false; }

    public Socket getSocket(){
        return s;
    }

    public String getClientName(){
        return name;
    }

    @Override
    public void run() {
        try {



            System.out.println(name + " connected on " + s);


            // forms input and output streams from and to the client into lines of text that can be read

            BufferedReader inp = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            String inputLine = inp.readLine();

            while(!exit) {

                // accept a message, also blocks until it receives something through the input stream of the port

                    if(inputLine == null){
                        break;
                    }
                    System.out.println(name + " has sent this via input stream: " + inputLine);
                    // send the message back
                    sendtoclients(name + ": " + inputLine);
                    inputLine = inp.readLine();

            }

            clientlist.remove(this);
            System.out.println(name + " has disconnected from the server");



        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void sendtoclients(String output) {
        try {

            int i;
            for (i = 0; i <= (clientlist.size()-1); i++) {
                PrintWriter outp = new PrintWriter(
                        clientlist.get(i).getSocket().getOutputStream(), true);
                outp.println(output);
                System.out.println(clientlist.get(i).getClientName() + " has received this via output stream: " + output);
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }


}
