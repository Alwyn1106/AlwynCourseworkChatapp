import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOutputReader extends Thread {

    private Socket socket;
    private String fromServer;

    public ClientOutputReader (Socket socket) {
        this.socket = socket;

    }
    public void run() {

        try {

            BufferedReader clientInput = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));


            // naming variables for user input and what the server will send back

            fromServer = clientInput.readLine();

            while(true) {
                System.out.println(fromServer);
                fromServer = clientInput.readLine();

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
