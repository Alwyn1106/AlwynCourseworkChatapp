import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOutputReader extends Thread {

    private Socket socket;
    private String fromServer;
    private volatile boolean stop = false;

    public ClientOutputReader (Socket socket) {
        this.socket = socket;

    }
    public void run() {

            try {

                BufferedReader clientInput = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                fromServer = clientInput.readLine();
                if (fromServer.equals(null)) {
                }
                else {


                    while (!stop) {
                        System.out.println(fromServer);
                        fromServer = clientInput.readLine();
                        if (fromServer.equals(null)) {
                            break;
                        }
                    }
                }


            }

            catch (IOException ioe) {
                System.out.println("Connection Terminated by Client");
            }

    }

    public void stopRun(){
        stop = true;
    }
}
