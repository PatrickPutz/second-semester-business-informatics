package komplettuebungen.chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)) {
            System.out.println("server created...");
            ArrayList<ChatClient> clients = new ArrayList<>();
            while(true){
                System.out.println("waiting to connect...");
                Socket socket = server.accept();
                ChatClient client = new ChatClient(clients, socket);
                System.out.println("connection established");
                Thread thread = new Thread(client);
                thread.start();
                System.out.println("thread started");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
