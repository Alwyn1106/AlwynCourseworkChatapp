// This is a main for the Client programme
import java.lang.*;

public class ClientMain extends Thread {

    public static void main(String[] args) {

            EchoClient client = new EchoClient("Localhost", 14001);
            client.start();
        }

}