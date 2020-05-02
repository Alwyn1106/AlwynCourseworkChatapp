import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClientOrganiser extends Thread {

    private Socket s;
    private String name;

    public ServerClientOrganiser(Socket s, String name){
        this.s = s;
        this.name = name;
        //run();

    }

    public void run() {
        try {
            System.out.println(name + " connected on " + s);

            // forms input and output streams from and to the client into lines of text that can be read

            BufferedReader inp = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            PrintWriter outp = new PrintWriter(
                    s.getOutputStream(), true);


            while(true) {

                // accept a message, also blocks until it receives something through the input stream of the port
                String inputLine = inp.readLine();
                if(inputLine != null) {

                    System.out.println(name + " has sent this via input stream: " + inputLine);
                    // send the message back
                    outp.println(name + ": " + inputLine);
                    System.out.println(name + " has received this via output stream: " + inputLine);
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

}
