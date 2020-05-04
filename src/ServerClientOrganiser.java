import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClientOrganiser extends Thread {

    private Socket s;
    private String name;
    private static ArrayList<Socket> clientlist = new ArrayList<>();

    public ServerClientOrganiser(Socket s, String name){
        this.s = s;
        this.name = name;
        clientlist.add(s);


    }

    @Override
    public void run() {
        try {

            System.out.println(name + " connected on " + s);


            // forms input and output streams from and to the client into lines of text that can be read

            BufferedReader inp = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));



            while(true) {

                // accept a message, also blocks until it receives something through the input stream of the port
                String inputLine = inp.readLine();
                if(inputLine != null) {

                    System.out.println(name + " has sent this via input stream: " + inputLine);
                    // send the message back
                    sendtoclients(name + ": " + inputLine);

                }
                else{
                    System.out.println(name + " has disconnected from the server");
                    break;
                }


            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void sendtoclients(String output) {
        try {

            int i;
            for (i = 0; i <= (clientlist.size()-1); i++) {
                PrintWriter outp = new PrintWriter(
                        clientlist.get(i).getOutputStream(), true);
                outp.println(output);
                System.out.println("client-" + (i+1) + " has received this via output stream: " + output);
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }


}
