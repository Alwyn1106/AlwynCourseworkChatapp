import java.lang.*;

// This is a main for the Server Programme
// this is a github test 2444


public class ChatServer extends Thread {

    public static void main(String[] args) {
        int port = 14001;
        EchoServer server = new EchoServer(port);
        server.start();
    }

}