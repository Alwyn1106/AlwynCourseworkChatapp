// This is a main for the Client programme
import java.lang.*;
import java.net.Socket;

public class ChatClient extends Thread {

    public static void main(String[] args) {
        int port = 14001;
        String address = "localhost";

        if (args.length == 2) {
            if (args[0].equals("-ccp") && args[1].length() == 5) {
                if (isInteger(args[1])) {
                    port = Integer.parseInt(args[1]);
                } else {
                    System.out.println("The inputted port value is not a recognised integer");
                }
            } else if (args[0].equals("-cca") && args[1].length() == 14) {
                address = args[1];
            }
        }

            EchoClient client = new EchoClient(address, port);
            client.start();

    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }



}