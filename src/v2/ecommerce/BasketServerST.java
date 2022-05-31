package v2.ecommerce;

import java.io.IOException;
import java.net.ServerSocket;

public class BasketServerST {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)) {

            while(true){

                EcommerceLogic logic = new EcommerceLogic(server.accept());
                logic.run();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
