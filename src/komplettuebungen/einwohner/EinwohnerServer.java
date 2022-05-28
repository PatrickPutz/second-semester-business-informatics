package komplettuebungen.einwohner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EinwohnerServer {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)) {
            System.out.println("server open...");
            while(true){
                System.out.println("waiting for client...");
                Socket client = server.accept();

                EinwohnerHandler einwohnerHandler = new EinwohnerHandler(client);
                Thread thread = new Thread(einwohnerHandler);

                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
