package komplettuebungen.einwohner;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class EinwohnerHandler implements Runnable{

    private Socket client;
    private EinwohnerManager em;

    public EinwohnerHandler(Socket client) {
        this.client = client;
        this.em = new EinwohnerManager();
    }

    @Override
    public void run() {
        try {
            start();
        } catch (DataFileException e) {
            e.printStackTrace();
        }
    }

    public void start() throws DataFileException {

        ArrayList<Einwohner> einwohner = em.load();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            System.out.println("waiting for command...");
            String command;
            while((command = br.readLine()) != null){
                System.out.println("received command: " + command);
                String[] parts = command.split(" ");

                if(parts.length == 2){
                    if(parts[0].equalsIgnoreCase("GET")){
                        if(parts[1].equalsIgnoreCase("wien")
                        || parts[1].equalsIgnoreCase("steiermark")
                        || parts[1].equalsIgnoreCase("kärnten")) {
                            searchBundesland(einwohner, parts[1], bw);
                            bw.write("GET request fulfilled.");
                            bw.newLine();
                            bw.flush();
                        }
                        else{
                            searchGeburtsjahr(einwohner, parts[1], bw);
                            bw.write("GET request fulfilled.");
                            bw.newLine();
                            bw.flush();
                        }
                    }
                }
                else if(parts.length == 1 && parts[0].equalsIgnoreCase("EXIT")){
                    client.close();
                }
                else{
                    bw.write("UNKOWN COMMAND");
                    bw.newLine();
                    bw.flush();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void searchBundesland(ArrayList<Einwohner> einwohner, String bundesland, BufferedWriter bw) throws IOException {
        for (Einwohner ew : einwohner) {
            if(ew.getBundesland().equalsIgnoreCase(bundesland)){
                bw.write(ew.toString());
                bw.newLine();
                bw.flush();
            }
        }
    }
    private void searchGeburtsjahr(ArrayList<Einwohner> einwohner, String jahr, BufferedWriter bw) throws IOException {
        int geburtsjahr = Integer.parseInt(jahr);
        ArrayList<Einwohner> sorted = new ArrayList<>(einwohner);
        Collections.sort(sorted);
        for (Einwohner ew : sorted) {
            if(ew.getGeburtsjahr() == geburtsjahr){
                bw.write(ew.toString());
                bw.newLine();
                bw.flush();
            }
        }
    }

}
