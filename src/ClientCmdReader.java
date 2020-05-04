import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCmdReader extends Thread{

    private Socket socket;
    private volatile boolean exit = false;

    public ClientCmdReader(Socket socket) {

        this.socket = socket;

    }

    public void run(){

        try{


            // forms input that is given to the system's command line into lines of text that can be read
            BufferedReader cmd = new BufferedReader(
                    new InputStreamReader(System.in));

            PrintWriter clientOutput = new PrintWriter(
                    socket.getOutputStream(),true);

            // naming variables for user input and what the server will send back
            String userInput = cmd.readLine();
            //
           // String response;


                if(userInput.equals("quit")) {
                    System.out.println("Connection with the Server has been terminated");
                    socket.close();


                }
                else {


                    clientOutput.println(userInput);

                    // this is a blocking call waiting on the port's input stream before progressing the code

                    System.out.println(EchoClient.readin(socket));
                    // this is a blocking call that waits for an input from the command line
                    userInput = cmd.readLine();


                }




        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void setExit(){
        exit = true;
    }

}
