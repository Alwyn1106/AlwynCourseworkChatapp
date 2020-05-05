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

                fromServer = EchoClient.readin(socket);

                while (true) {
                    System.out.println(fromServer);
                    fromServer = EchoClient.readin(socket);
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
    }
}
