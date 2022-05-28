package teiluebungen.networking.guessnumberserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class GuessNumberServer {

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(1111)) {
            System.out.println("server created...");
            while(true){
                System.out.println("waiting for client...");
                try(Socket client = serverSocket.accept();
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))){
                    System.out.println("client connection established...");

                    int random = new Random().nextInt(32) + 1;
                    System.out.println("Random number generated: " + random);
                    bw.write("Guess the number!");
                    bw.newLine();
                    bw.flush();
                    System.out.println("reading from client...");

                    String line;
                    int parsed;
                    while((line = br.readLine()) != null){
                        System.out.println("Client input: " + line);
                        parsed = Integer.parseInt(line);
                        if(parsed > random){
                            bw.write("Number too big!");
                            bw.newLine();
                            bw.flush();
                        }
                        else if(parsed < random){
                            bw.write("Number too small!");
                            bw.newLine();
                            bw.flush();
                        }
                        else{
                            random = new Random().nextInt(32) + 1;
                            System.out.println("Random number generated: " + random);
                            bw.write("Congrats, you guessed right! New number generated, guess again!");
                            bw.newLine();
                            bw.flush();
                        }
                        System.out.println("reading from client...");
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
