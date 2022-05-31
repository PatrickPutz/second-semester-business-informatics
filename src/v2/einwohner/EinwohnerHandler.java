package v2.einwohner;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class EinwohnerHandler implements Runnable{

    private Socket socket;

    public EinwohnerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String line;
            ArrayList<Einwohner> einwohner = null;
            try {
                einwohner = EinwohnerManager.load();
            } catch (DataFileException e) {
                e.printStackTrace();
            }
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");
                if(parts.length == 2 && einwohner != null){
                    if(parts[0].equalsIgnoreCase("get")){
                        if(parts[1].equalsIgnoreCase("steiermark")
                                || parts[1].equalsIgnoreCase("wien")
                                || parts[1].equalsIgnoreCase("K�rnten")){
                            int count = 0;
                            for (Einwohner e : einwohner) {
                                if(e.getBundesland().equalsIgnoreCase(parts[1])){
                                    count++;
                                    bw.write(e.toString());
                                    bw.newLine();
                                }
                                if(count == 50)
                                    bw.flush();
                            }
                            bw.write("<<< get request fulfilled >>>");
                            bw.newLine();
                            bw.flush();
                        }
                        else{
                            int year = Integer.parseInt(parts[1]);
                            ArrayList<Einwohner> sorted = einwohner;
                            sorted.sort(new GeburtsjahrDescNameAscComparator());
                            int count = 0;
                            for (Einwohner e : sorted) {
                                if(e.getGeburtsjahr() == year){
                                    count++;
                                    bw.write(e.toString());
                                    bw.newLine();
                                }
                                if(count == 50)
                                    bw.flush();
                            }
                            bw.write("<<< get request fulfilled >>>");
                            bw.newLine();
                            bw.flush();
                        }
                    }
                }
                else if(parts.length == 1){
                    if(parts[0].equalsIgnoreCase("exit")){

                        bw.write("bye!");
                        bw.newLine();
                        bw.flush();

                        bw.close();
                        br.close();
                        socket.close();
                        return;

                    }

                    else{
                        bw.write("invalid command");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else{
                    bw.write("invalid command");
                    bw.newLine();
                    bw.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
