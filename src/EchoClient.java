import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.lang.*;


public class EchoClient extends Thread {
    //private String address;
    // int port;
    private Socket socket;
    private volatile boolean stop = false;
    private String fromServer;




    // This is the constructor for the client. This assigns the two values passed to it by the argument.

    public EchoClient(String address, int port) {
        try {

            socket = new Socket(address, port);
            start();

        } catch (IOException e) {
            System.out.print("Please ensure there is a corresponding server for the client before attempting to run the programme");
            System.out.print("\n");
            System.out.println("Either the server has not been started or the arguments given the client (for port and or address) are invalid");
            System.exit(-1);
        }

        System.out.println("Please Insert Text:");

        try {
            // forms input that is given to the system's command line into lines of text that can be read

            BufferedReader cmd = new BufferedReader(
                    new InputStreamReader(System.in));

            PrintWriter clientOutput = new PrintWriter(
                    socket.getOutputStream(), true);

            // naming variables for user input and what the server will send back
            String userInput = cmd.readLine();


            if (userInput.equals("quit")) {
                stop = true;
            }

            else{


                while (!stop) {

                    clientOutput.println(userInput);
                    // this is a blocking call that waits for an input from the command line
                    userInput = cmd.readLine();

                    if (userInput.equals("quit")) {
                        stop = true;
                        break;
                    }
                }

            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {

           try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("The client has opted to terminate connection with the server...");


        }

    }

        public void run() {

            try {

                BufferedReader clientInput = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                fromServer = clientInput.readLine();

                while (!fromServer.equals(null)) {
                    System.out.println(fromServer);

                        fromServer = clientInput.readLine();

                    if (fromServer.equals(null)) {
                        break;
                    }
                }

            } catch (IOException ioe) {
                System.out.println("Socket disconnected...");
            }

            catch (NullPointerException ne){
                System.out.println("The Server has been shut down, disconnecting");
                System.exit(0);
            }

        }




}
