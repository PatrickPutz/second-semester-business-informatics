package teiluebungen.networking.timeserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class TimeServer {

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(1111)) {
            System.out.println("serversocket created...");
            while(true){
                System.out.println("waiting for clients...");
                try(Socket client = serverSocket.accept();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))){
                    System.out.println("connection established");

                    LocalDateTime dateTime = LocalDateTime.now();
                    bw.write(String.valueOf(dateTime));
                    bw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
