import java.io.IOException;
import java.net.Socket;
import java.lang.*;

public class EchoClient extends Thread {
    private String address;
    private int port;
    private Socket socket;

    // This is the constructor for the client. This assigns the two values passed to it by the argument.

    public EchoClient(String address, int port) {
        try {
            this.address = address;
            this.port = port;
            socket = new Socket(address, port);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        // networking code

            System.out.println("Please insert text:");
            // forms output stream to the server into lines of text that can be read

                ClientCmdReader inp = new ClientCmdReader(socket);
                inp.start();

                ClientOutputReader outreader = new ClientOutputReader(socket);
                outreader.start();

        }



            // forms input stream from the server into lines of text that can be read



}
