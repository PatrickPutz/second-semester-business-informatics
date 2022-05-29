package komplettuebungen.chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(1111)) {
            System.out.println("server created...");
            ArrayList<ChatClient> clients = new ArrayList<>();
            ChatLogger logger = new ChatLogger();
            HashMap<String, ChatClient> map = new HashMap<>();
            while(true){
                System.out.println("waiting to connect...");
                Socket socket = server.accept();
                ChatClient client = new ChatClient(clients, socket, logger, map);
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
