package teiluebungen.networking.timeclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) {

        try (Socket client = new Socket("localhost", 1111);
             BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()))){

            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
