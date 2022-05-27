package teiluebungen.exceptions.raumschiff;

public class Transporter {

    public void beam(String person, String from, String to, boolean urgent) throws TransporterMalfunctionException {

        System.out.println("starting beam engines...");

        if(urgent && Math.random() < 0.5)
            throw new TransporterMalfunctionException();
        else
            System.out.println(person + " has been beamed from " + from + " to " + to);

    }

    public void shutdown(){
        System.out.println("shutting down...");
    }

}
