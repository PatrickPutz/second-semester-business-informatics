package teiluebungen.exceptions.raumschiff;

public class Starship {

    private String name;
    private Transporter transporter;

    public Starship(String name) {
        this.name = name;
        this.transporter = new Transporter();
    }

    public void beamUp(String person, String from){
        try {
            transporter.beam(person, from, this.name, true);
        } catch (TransporterMalfunctionException e) {
            e.printStackTrace();
            System.out.println("beaming failed!");
        } finally {
            this.transporter.shutdown();
        }
    }

}
