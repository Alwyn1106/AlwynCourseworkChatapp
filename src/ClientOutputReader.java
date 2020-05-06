import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientOutputReader extends Thread {

    private Socket socket;
    private String fromServer;


    public ClientOutputReader (Socket socket) {
        this.socket = socket;

    }
    public void run() {

            try {

                BufferedReader clientInput = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                fromServer = clientInput.readLine();
                if (fromServer.equals(null)) {
                }
                else {


                    while (true) {
                        System.out.println(fromServer);
                        fromServer = clientInput.readLine();
                        if (fromServer.equals(null)) {
                            break;
                        }
                    }
                }

            } catch (IOException ioe) {
                System.out.println("Finished Termination - client disconnected");
            }
            catch (NullPointerException ne){
                System.out.println("The Server has been shut down and this client will now be closed");
            }

    }
    public void closeInputStream() {
        try {
            socket.getInputStream().close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

    }


}
