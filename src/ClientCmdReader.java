import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCmdReader extends Thread{

    private Socket socket;
    private volatile boolean stop = false;

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


                if (userInput.equals("quit")) {
                    EchoClient.setExit();
                    //break;
                }
                else {

                    while(!stop) {
                        clientOutput.println(userInput);
                        // this is a blocking call that waits for an input from the command line
                        userInput = cmd.readLine();

                        if (userInput.equals("quit")) {
                            EchoClient.setExit();
                            stopRun();
                        }
                    }

                }
                System.out.println("The client has opted to terminate connection with the server...");

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
    }


    public void stopRun(){
        stop = true;

    }

    public void closeCmdReader() {
        try {
            socket.getOutputStream().close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

}
