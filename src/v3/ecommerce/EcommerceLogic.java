package v3.ecommerce;

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

            String line;
            BasketAnalyzer ba = null;
            while((line = br.readLine()) != null){
                String[] command = line.split(" ");
                if(command.length == 2){
                    if(command[0].equalsIgnoreCase("openfile")){
                        try{
                            ArrayList<BasketData> loaded = BasketDataLoader.load(command[1]);
                            ba = new BasketAnalyzer(loaded);
                            bw.write("<<< basket data loaded with " + loaded.size() + " entries >>>");
                            bw.newLine();
                            bw.flush();
                        } catch (DataFileException e) {
                            e.printStackTrace();
                            bw.write("<<< error while loading " + command[1] + " >>>");
                            bw.newLine();
                            bw.flush();
                        }
                    }
                    else if(command[0].equalsIgnoreCase("geteverynth") && ba != null){
                        List<BasketData> list = ba.getEveryNthBasket(Integer.parseInt(command[1]));
                        int count = 0;
                        for (BasketData bd : list) {
                            count++;
                            bw.write(bd.toString());
                            bw.newLine();
                            if(count % 100 == 0)
                                bw.flush();
                        }
                        bw.write("<<< completed >>>");
                        bw.newLine();
                        bw.flush();
                    }
                    else{
                        bw.write("<<< invalid command >>>");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else if(command.length == 1){
                    if(command[0].equalsIgnoreCase("getstats") && ba != null){
                        HashMap<String, ArrayList<Double>> map = ba.groupByProductCategory();
                        for (String key : map.keySet()) {
                            ArrayList<Double> list = map.get(key);
                            double sum = 0;
                            for (Double d : list) {
                                sum += d;
                            }
                            sum = sum/list.size();
                            bw.write(key + " - " + sum);
                            bw.newLine();
                        }
                        bw.flush();
                    }
                    else if(command[0].equalsIgnoreCase("exit")){
                        bw.write("bye!");
                        bw.newLine();
                        bw.flush();

                        socket.close();
                    }
                    else{
                        bw.write("<<< invalid command >>>");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else{
                    bw.write("<<< invalid command >>>");
                    bw.newLine();
                    bw.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
