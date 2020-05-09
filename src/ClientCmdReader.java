import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientCmdReader extends Thread{

    private Socket socket;
    private boolean stop = false;

    // This is the constructor - passed Socket value by EchoClient for each thread initiated

    public ClientCmdReader(Socket socket) {

        this.socket = socket;

    }

    public void run(){


            try {
                // forms input that is given to the system's command line into lines of text that can be read

                BufferedReader cmd = new BufferedReader(
                        new InputStreamReader(System.in));

                PrintWriter clientOutput = new PrintWriter(
                        socket.getOutputStream(), true);

                // naming variables for user input and what the server will send back
                String userInput = cmd.readLine();


                if (!userInput.equals("quit")) {


                    while(!stop) {

                        clientOutput.println(userInput);
                        // this is a blocking call that waits for an input from the command line
                        userInput = cmd.readLine();

                        if (userInput.equals("quit")) {
                            stop = true;
                        }
                    }

                }

                try {
                    socket.close();
                } catch (SocketException se) {
                    se.printStackTrace();
                }

                System.out.println("The client has opted to terminate connection with the server...");


            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            finally {
                System.exit(0);
            }
    }



}
