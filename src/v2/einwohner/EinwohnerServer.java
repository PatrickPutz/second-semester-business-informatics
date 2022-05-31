package v2.einwohner;

import java.io.IOException;
import java.net.ServerSocket;

public class EinwohnerServer {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)) {

            while(true){

                EinwohnerHandler eh = new EinwohnerHandler(server.accept());
                Thread thread = new Thread(eh);
                thread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
