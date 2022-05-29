package komplettuebungen.ecommerce;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasketServerST {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)){

            System.out.println("server startet on port 1111");

            while(true){

                System.out.println("waiting for client");
                Socket client = server.accept();
                System.out.println("client connection established");

                EcommerceLogic ec = new EcommerceLogic(client);
                Thread thread = new Thread(ec);
                thread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
