import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.lang.*;
import java.net.SocketException;

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


    public Socket retrieveSocket(){
        return socket;
    }

    @Override
    public void run() {
        // networking code

        System.out.println("Please insert text:");

        ClientCmdReader inp = new ClientCmdReader(socket);
        ClientOutputReader outreader = new ClientOutputReader(socket);
            // forms output stream to the server into lines of text that can be read



            inp.start();
            outreader.start();

        try {
            inp.join();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        }



}
