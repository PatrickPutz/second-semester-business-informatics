package v2.ecommerce;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EcommerceLogic implements Runnable {

    private Socket socket;

    public EcommerceLogic(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String line;
            BasketAnalyzer ba = null;
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");

                if(parts.length == 2){
                    if(parts[0].equalsIgnoreCase("openfile")){
                        ba = new BasketAnalyzer(BasketDataLoader.load(parts[1]));
                        bw.write("<<< basket data loaded with " + ba.getBaskets().size() + " entries >>>");
                        bw.newLine();
                        bw.flush();
                    }
                    else if(parts[0].equalsIgnoreCase("geteverynth") && ba != null){
                        List<BasketData> list = ba.getEveryNthBasket(Integer.parseInt(parts[1]));
                        int count = 0;
                        for (BasketData basketData : list) {
                            count++;
                            bw.write(basketData.toString());
                            bw.newLine();
                            if(count == 50)
                                bw.flush();
                        }
                        bw.write("get request fulfilled");
                        bw.newLine();
                        bw.flush();
                    }
                    else{
                        bw.write("invalid command");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else if(parts.length == 1){
                    if(parts[0].equalsIgnoreCase("getstats") && ba != null){
                        HashMap<String, ArrayList<Double>> map = ba.groupByProductCategory();
                        for (String key : map.keySet()) {
                            double sum = 0.0;
                            ArrayList<Double> values = map.get(key);
                            for (Double value : values) {
                                sum += value;
                            }
                            sum = sum / values.size();
                            bw.write(key + " - " + sum);
                            bw.newLine();
                            bw.flush();
                        }
                        bw.write("get request fulfilled");
                        bw.newLine();
                        bw.flush();
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
        } catch (DataFileException e) {
            e.printStackTrace();
        }
    }
}
