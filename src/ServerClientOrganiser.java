import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClientOrganiser extends Thread {

    private Socket s;
    private String name;
    private boolean nameinputted = false;

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



                PrintWriter outp = new PrintWriter(
                        getSocket().getOutputStream(), true);

                String propName;
                propName = inp.readLine();

                while(!nameinputted) {
                    if (!ServerLogic.InputtedNameExists(propName) && propName.length() <= 20) {

                        name = propName;
                        nameinputted = true;
                        outp.println("Welcome to the chat: " + name);

                    } else if (ServerLogic.InputtedNameExists(propName)) {

                        outp.println("The name " + propName + " already exists, please type a unique name");
                        System.out.println("Sent error (proposed name exists) to : " + name);
                        propName = inp.readLine();

                    } else if (propName.length() >= 20) {
                        outp.println("Please insert a name under or equal to 20 characters");
                        System.out.println("Sent error (proposed name is too long) to: " + name);
                        propName = inp.readLine();
                    }
                }





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
                                ServerLogic.sendSingleClient(name + ": " + inputLine, i);
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
