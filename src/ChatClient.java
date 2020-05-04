// This is a main for the Client programme
import java.lang.*;

public class ChatClient extends Thread {

    public static void main(String[] args) {

            EchoClient client = new EchoClient("Localhost", 14001);
            client.start();
        }

}