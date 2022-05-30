package komplettuebungen.webproxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(5678)) {
            PageCache cache = new PageCache();
            cache.warmUp("demo_urls.txt");
            WebProxy proxy = new WebProxy(cache);
            while(true){
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client, proxy);
                clientHandler.run();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
