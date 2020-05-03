import  java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.lang.*;

public class EchoClient extends Thread {
    private String address;
    private int port;
    private Socket socket;

    // This is the constructor for the client. This assigns the two values passed to it by the argument.

    public EchoClient(String address, int port) {
        try {
            this.address = address;
            this.port = port;
            socket = new Socket(address, port);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        // networking code
        try
        {

            System.out.println("Please insert text:");
            // forms output stream to the server into lines of text that can be read
            PrintWriter clientOutput = new PrintWriter(
            socket.getOutputStream(),true);
            // forms input stream from the server into lines of text that can be read
            BufferedReader clientInput = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
            // forms input that is given to the system's command line into lines of text that can be read
            BufferedReader cmd = new BufferedReader(
                    new InputStreamReader(System.in));

            // naming variables for user input and what the server will send back
            String userInput = cmd.readLine();
            String response;


            while(!userInput.equals("quit")) {

                clientOutput.println(userInput);

                // this is a blocking call waiting on the port's input stream before progressing the code
                response = clientInput.readLine();
                System.out.println(response);
                // this is a blocking call that waits for an input from the command line
                userInput = cmd.readLine();
            }
            System.out.println("Connection with the Server has been terminated");
            socket.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
