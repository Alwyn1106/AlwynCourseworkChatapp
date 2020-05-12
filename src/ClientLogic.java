import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.lang.*;


public class ClientLogic extends Thread {
    //private String address;
    // int port;
    private Socket socket;
    private volatile boolean stop = false;
    private String fromServer;
    private boolean namevalid = true;




    // This is the constructor for the client. This assigns the two values passed to it by the argument.

    public ClientLogic(String address, int port)  {
        try {

            socket = new Socket(address, port);


        } catch (IOException e) {
            System.out.println("Please ensure there is a corresponding server for the client before attempting to run the programme");
            System.out.println("Either the server has not been started or the arguments given the client (for port and or address) are invalid");
            System.exit(-1);
        }

        System.out.println("Welcome to the Chat");
        System.out.println("Before we begin, note that inputting the string \"quit\" will terminate the programme");
        System.out.println("Also, inputting the string PRIVATE followed by a space and another client's username will send your message privately to only the client who's username you have given");
        System.out.println("Please insert your client name: ");



        try {
            // forms input that is given to the system's command line into lines of text that can be read

            BufferedReader cmd = new BufferedReader(
                    new InputStreamReader(System.in));

            PrintWriter clientOutput = new PrintWriter(
                    socket.getOutputStream(), true);

            // naming variables for user input and what the server will send back


            start();
            String userInput;

            userInput = cmd.readLine();
            //clientOutput.println(userInput);


                    while (!stop) {

                        if (userInput.equals("quit")) {
                            stop = true;
                            break;
                        }

                        clientOutput.println(userInput);
                        // this is a blocking call that waits for an input from the command line
                        userInput = cmd.readLine();

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

                }

            } catch (IOException ioe) {
                System.out.println("Socket disconnected...");
                //System.exit(0);
            }

            catch (NullPointerException ne){
                System.out.println("The Server has been shut down, disconnecting");
                System.exit(0);
            }

        }



}
