import java.lang.*;

// This is a main for the Server Programme



public class ChatServer extends Thread {


    public static void main(String[] args) {
        int port = 14001;

        if (args.length == 2) {
            if (args[0].equals("-csp") && args[1].length() == 5) {
                if(isInteger(args[1])){
                    port = Integer.parseInt(args[1]);
                }
                else {
                    System.out.println("The inputted port value is not a recognised integer");
                }
            } else {
                System.out.println("The argument for selecting a port value was not entered correctly");
            }
        }

        EchoServer server = new EchoServer(port);
        //server.start();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

}