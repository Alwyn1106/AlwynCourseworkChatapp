import java.lang.*;

// This is a main for the Server Programme
// this is a github test 2444


public class ChatServer extends Thread {


    public static void main(String[] args) {
        int port = 14001;

        if (args.length == 2) {
            if (args[0].equals("-csp") && args[1].length() == 5) {
                port = Integer.parseInt(args[1]);
            } else {
                System.out.println("The command line argument for selecting a port value was not entered correctly");
            }
        }

        EchoServer server = new EchoServer(port);
        server.start();
    }

}