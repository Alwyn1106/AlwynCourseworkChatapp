import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerCmdReader extends Thread {
    public ServerCmdReader() {

    }



    public void run(){

        BufferedReader cmd = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            String userInput = cmd.readLine();
            if (userInput.equals("EXIT")) {
                System.out.println("Closing Server...");
                ServerClientOrganiser.CloseClients();
                //System.exit(0);
            }
            else {
                while (true) {
                    userInput = cmd.readLine();
                    if (userInput.equals("EXIT")) {


                        System.out.println("Closing Server...");
                        ServerClientOrganiser.CloseClients();


                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);


    }



}
