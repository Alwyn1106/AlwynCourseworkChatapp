import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientInp extends Thread{

    private Socket socket;

    public ClientInp (Socket socket) {

        this.socket = socket;

    }

    public void run(){

        try{

            BufferedReader clientInput = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // forms input that is given to the system's command line into lines of text that can be read
            BufferedReader cmd = new BufferedReader(
                    new InputStreamReader(System.in));

            PrintWriter clientOutput = new PrintWriter(
                    socket.getOutputStream(),true);

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
