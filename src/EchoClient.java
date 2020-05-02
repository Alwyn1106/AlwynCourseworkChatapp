import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private String address;
    private int port;
    private Socket socket;

    // This is the constructor for the client. This assigns the two values passed to it by the argument.

    public EchoClient(String address, int port) {
        try {
            this.address = address;
            this.port = port;
            socket = new Socket(address, port);
            process();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void process() {
        // networking code
        try
        {

            System.out.println("Please insert text to be echoed:");
            // forms output stream to the server into lines of text that can be read
            PrintWriter serverOutput = new PrintWriter(
            socket.getOutputStream(),true);
            // forms input stream from the server into lines of text that can be read
            BufferedReader serverInput = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
            // forms input that is given to the system's command line into lines of text that can be read
            BufferedReader cmd = new BufferedReader(
                    new InputStreamReader(System.in));

            // naming variables for user input and what the server will send back
            String userInput = cmd.readLine();
            String response;


            while(!userInput.equals("quit")) {
                serverOutput.println(userInput);
                // this is a blocking call waiting on the port's input stream before progressing the code
                response = serverInput.readLine();
                System.out.println(response);
                // this is a blocking call that waits for an input from the command line
                userInput = cmd.readLine();
            }
            System.out.println("Connection with the Server has been terminated");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
