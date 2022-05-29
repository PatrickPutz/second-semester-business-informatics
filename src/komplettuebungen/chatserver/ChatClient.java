package komplettuebungen.chatserver;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClient implements Runnable{

    private BufferedReader reader;
    private PrintWriter printWriter;
    private ArrayList<ChatClient> clients;
    private Socket client;
    private String name;
    private ChatLogger logger;

    public ChatClient(ArrayList<ChatClient> clients, Socket client, ChatLogger chatLogger) throws IOException {
        this.clients = clients;
        this.client = client;
        this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.printWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        this.name = "guest";
        clients.add(this);
        this.logger = chatLogger;
    }

    @Override
    public void run() {
        try{
            String line;
            while((line = reader.readLine()) != null){
                System.out.println("received command: " + line);
                logger.logMessage(this.name, line);
                String[] parts = line.split(":");
                if(parts.length == 2){
                    if(parts[0].equalsIgnoreCase("<name>")){
                        this.name = parts[1];
                        printWriter.println("Welcome " + this.name + "!");
                        printWriter.flush();
                    }
                    else if(parts[0].equalsIgnoreCase("<msg>")){
                        for (ChatClient chatClient : clients) {
                            chatClient.sendMessage(this.name, parts[1]);
                        }
                    }
                    else{
                        printWriter.println("UNKOWN COMMAND");
                        printWriter.flush();
                    }
                }
                else if(parts.length == 3){
                    if(parts[0].equalsIgnoreCase("<msgto>")){
                        for (ChatClient chatClient : clients) {
                            if(chatClient.getName().equalsIgnoreCase(parts[1])){
                                chatClient.sendMessage(this.name, parts[2]);
                            }
                        }
                    }
                    else {
                        printWriter.println("UNKOWN COMMAND");
                        printWriter.flush();
                    }
                }
                else if(parts.length == 1){
                    // keeping ifs separated in case more commands with the same length will be added (gonna be easier then)
                    if(parts[0].equalsIgnoreCase("<bye>")){
                        printWriter.println("closing...");
                        for (ChatClient chatClient : clients) {
                            chatClient.sendMessage(this.name, " has left the chat.");
                        }
                        printWriter.flush();
                        close();
                    }
                    else {
                        printWriter.println("UNKOWN COMMAND");
                        printWriter.flush();
                    }
                }
                else {
                    printWriter.println("UNKOWN COMMAND");
                    printWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String name, String message){
        printWriter.println(name + ": " + message);
        printWriter.flush();
    }

    public void close() throws IOException {
        printWriter.close();
        reader.close();
        client.close();
        clients.remove(this);
    }

    public String getName() {
        return name;
    }
}
