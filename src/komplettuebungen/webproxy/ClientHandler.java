package komplettuebungen.webproxy;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket client;
    private WebProxy proxy;

    public ClientHandler(Socket client, WebProxy proxy) {
        this.client = client;
        this.proxy = proxy;
    }

    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            String command;
            while((command = br.readLine()) != null){
                String[] parts = command.split(" ");
                if(parts.length == 1){
                    if(parts[0].equalsIgnoreCase("bye")){
                        br.close();
                        bw.close();
                        client.close();
                        return;
                    }
                    else{
                        bw.write("error: command invalid");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else if(parts.length == 2){
                    if(parts[0].equalsIgnoreCase("fetch")){
                        try {
                            WebPage page = proxy.fetch(parts[1]);
                            bw.write("content of " + parts[1] + ": " + page.getContent());
                        } catch (CacheMissException e) {
                            bw.write("error: loading the url failed");
                            bw.newLine();
                            bw.flush();
                            e.printStackTrace();
                        }
                    }
                    else if(parts[0].equalsIgnoreCase("stats")){
                        if(parts[1].equalsIgnoreCase("hits")){
                            bw.write("hits: " + proxy.statsHits());
                            bw.newLine();
                            bw.flush();
                        }
                        else if(parts[1].equalsIgnoreCase("misses")){
                            bw.write("misses: " + proxy.statsMisses());
                            bw.newLine();
                            bw.flush();
                        }
                        else{
                            bw.write("error: command invalid");
                            bw.newLine();
                            bw.flush();
                        }
                    }
                    else{
                        bw.write("error: command invalid");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else{
                    bw.write("error: command invalid");
                    bw.newLine();
                    bw.flush();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
