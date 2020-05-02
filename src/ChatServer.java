import java.lang.*;

// This is a main for the Server Programme


public class ChatServer extends Thread {

    public static void main(String[] args) {
        int port = 14001;
        EchoServer server = new EchoServer(port);
    }

}