package teiluebungen.networking.guessnumberclient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class GuessNumberClient {

    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost", 1111);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            while(true){

                int number = console.read();
                String line;
                int parsed;

                bw.write("(" + number + ")" + ", Happy Guessing!");
                bw.newLine();
                bw.flush();

                while((line = br.readLine()) != null){

                    parsed = Integer.parseInt(line);
                    System.out.println("Client input: " + line);
                    parsed = Integer.parseInt(line);
                    if(parsed > number){
                        bw.write("Number too big!");
                        bw.newLine();
                        bw.flush();
                    }
                    else if(parsed < number){
                        bw.write("Number too small!");
                        bw.newLine();
                        bw.flush();
                    }
                    else{
                        System.out.println("Random number generated: " + number);
                        bw.write("Congrats, you guessed right! New number generated, guess again!");
                        bw.newLine();
                        bw.flush();
                    }
                    System.out.println("reading from client...");

                }

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
