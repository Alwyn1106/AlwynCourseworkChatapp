// This is a main for the Client programme
import java.lang.*;
import java.net.ConnectException;


public class ChatClient extends Thread {


    public static void main(String[] args) {
        int port = 14001;
        String address = "localhost";

        if (args.length > 0) {
            if (args.length == 2) {
                if (args[0].equals("-ccp") && args[1].length() == 5) {
                    if (isInteger(args[1])) {
                        port = Integer.parseInt(args[1]);
                    } else {
                        System.out.println("The inputted port value is not a recognised integer, initialising with default port");
                    }
                } else if (args[0].equals("-cca") && args[1].length() == 14) {
                    address = args[1];
                } else {
                    System.out.println("The arguments inputted were not recognised, initialising with default values");
                }
            }
            else if (args.length == 4) {
                if (args[0].equals("−cca") && args[2].equals("-ccp")) {
                    port = Integer.parseInt(args[3]);
                    address = args[1];
                } else {
                    System.out.println("The inputted arguments were not recognised, initialising with default values");
                }
            }
            else {
                System.out.println ("The arguments given have not been recognised. The programme will initiate with default values");
            }
        }

        try {
            EchoClient client = new EchoClient(address, port);
        }
        catch (NullPointerException ne) {
            System.out.println("There is not an activated corresponding server running. Please run the server before trying to connect a client");
        }

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