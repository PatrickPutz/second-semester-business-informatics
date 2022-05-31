package v2.ecommerce;

import java.io.IOException;
import java.net.ServerSocket;

public class BasketServerMT {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)) {

            while(true){

                EcommerceLogic logic = new EcommerceLogic(server.accept());
                Thread thread = new Thread(logic);
                thread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
