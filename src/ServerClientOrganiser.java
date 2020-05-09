import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClientOrganiser extends Thread {

    private Socket s;
    private String name;

    public ServerClientOrganiser(Socket s, String name){
        this.s = s;
        this.name = name;


    }


    public Socket getSocket(){
        return s;
    }

    public String getClientName(){
        return name;
    }

    @Override
    public void run() {

        try {



            System.out.println(name + " connected on " + s);


            // forms input and output streams from and to the client into lines of text that can be read

            BufferedReader inp = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            String inputLine = inp.readLine();

            while(true) {

                // accept a message, also blocks until it receives something through the input stream of the port

                            System.out.println(name + " has sent this via input stream: " + inputLine);
                            EchoServer.sendtoclients(name + ": " + inputLine);
                            inputLine = inp.readLine();


            }




        } catch (IOException ioe) {

           System.out.println(name + " has been terminated");
                EchoServer.RemoveAClient(getClientName());
        }



    }



}
