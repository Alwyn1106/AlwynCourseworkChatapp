import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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

    public void setClientName(String name) {this.name = name; }

    @Override
    public void run() {

        try {



            System.out.println(name + " connected on " + s);


            // forms input and output streams from and to the client into lines of text that can be read

            BufferedReader inp = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));


            name = inp.readLine();


            System.out.println("Client name defined as: " + name);

            String inputLine = inp.readLine();

            //inputLine = inp.readLine();
            try {

                while (!inputLine.equals(null)) {

                    // accept a message, also blocks until it receives something through the input stream of the port

                    System.out.println(name + " has sent this via input stream: " + inputLine);
                    if (inputLine.length() > 7 && inputLine.substring(0,7).equals("PRIVATE")){
                        int i;

                        for (i = 0; i <= (ServerLogic.getClientList().size()-1); i++) {
                            if (inputLine.contains(ServerLogic.getClientList().get(i).getClientName())) {
                                ServerLogic.sendSingleClient(name + " " + inputLine, i);
                                inputLine = inp.readLine();

                            }
                        }
                    }
                    else {
                        ServerLogic.sendtoclients(name + ": " + inputLine);
                        inputLine = inp.readLine();
                    }

                }

            }
            catch(NullPointerException ne){
                System.out.println(name + " has been terminated");
                ServerLogic.RemoveAClient(name);

            }

            //System.out.println(name + " has been terminated");




        } catch (IOException ioe) {

           System.out.println(name + " has been terminated");
                ServerLogic.RemoveAClient(getClientName());
        }


    }

}
