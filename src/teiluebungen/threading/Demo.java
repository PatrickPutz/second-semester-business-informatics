package teiluebungen.threading;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        TimePrintWorker tpw = new TimePrintWorker("TimePrintWorker");
        FileReadWorker frw = new FileReadWorker("FileReadWorker", ".\\data\\copyfrom.txt");

        Thread th1 = new Thread(tpw);
        Thread th2 = new Thread(frw);

        th1.start();
        th2.start();

        Thread.sleep(2000);
        for(String line : frw.lines){
            System.out.println(line);
        }
        Thread.sleep(2000);
        for(String line : frw.lines){
            System.out.println(line);
        }

    }

}
