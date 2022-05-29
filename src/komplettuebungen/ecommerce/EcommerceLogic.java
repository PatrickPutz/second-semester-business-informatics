package komplettuebungen.ecommerce;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EcommerceLogic implements Runnable{

    private Socket socket;

    public EcommerceLogic(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String command;
            BasketAnalyzer ba = null;
            while((command = br.readLine()) != null){

                String[] parts = command.split(" ");
                if(parts.length == 2){
                    if(parts[0].equalsIgnoreCase("openfile")){
                        String path = parts[1];
                        try {
                            ArrayList<BasketData> list = BasketDataLoader.load(path);
                            ba = new BasketAnalyzer(list);
                            bw.write("<<< basket data loaded with " + list.size() + " entries >>>");
                            bw.newLine();
                            bw.flush();
                        } catch (DataFileException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(parts[0].equalsIgnoreCase("geteverynth")){
                        if(ba != null){
                            int number = Integer.parseInt(parts[1]);
                            List<BasketData> list = ba.getEveryNthBasket(number);
                            for (BasketData basketData : list) {
                                bw.write(basketData.toString());
                                bw.newLine();
                            }
                            bw.flush();
                            bw.write("Size of every " + number + "th entries");
                            bw.newLine();
                            bw.flush();
                        }
                        else{
                            bw.write("no data loaded, use OPENFILE command first!");
                            bw.newLine();
                            bw.flush();
                        }
                    }
                    else{
                        bw.write("UNKNOWN COMMAND");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else if(parts.length == 1){
                    if(parts[0].equalsIgnoreCase("getstats")){
                        if(ba != null){
                            HashMap<String, ArrayList<Double>> map = ba.groupByProductCategory();
                            for (String s : map.keySet()) {
                                ArrayList<Double> list = map.get(s);
                                double sum = 0.0;
                                for (Double d : list) {
                                    sum += d;
                                }
                                sum = sum / list.size();
                                bw.write(s + " - " + sum);
                                bw.newLine();
                                bw.flush();
                            }
                        }
                        else{
                            bw.write("no data loaded, use OPENFILE command first!");
                            bw.newLine();
                            bw.flush();
                        }
                    }
                    else if(parts[0].equalsIgnoreCase("exit")){
                        bw.write("bye!");
                        bw.newLine();
                        bw.flush();

                        bw.close();
                        br.close();
                        socket.close();
                        return;
                    }
                    else{
                        bw.write("UNKNOWN COMMAND");
                        bw.newLine();
                        bw.flush();
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
