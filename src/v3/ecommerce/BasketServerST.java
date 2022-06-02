package v3.ecommerce;

import java.io.IOException;
import java.net.ServerSocket;

public class BasketServerST {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)) {

            while(true){
                EcommerceLogic ec = new EcommerceLogic(server.accept());
                ec.run();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
