import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class ServerCmdReader extends Thread {
    public ServerCmdReader() {

    }



    public void run(){

        BufferedReader cmd = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            String userInput = cmd.readLine();

                while (true) {

                    if (userInput.equals("EXIT")) {
                        System.out.println("Closing Server...");
                        EchoServer.CloseClients();
                        break;
                    }
                    userInput = cmd.readLine();
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);


    }



}
