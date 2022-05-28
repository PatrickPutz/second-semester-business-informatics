package komplettuebungen.einwohner;

import java.util.Objects;

public class Einwohner implements Comparable<Einwohner>{

    private int id;
    private String name;
    private String bundesland;
    private int geburtsjahr;

    public Einwohner(int id, String name, String bundesland, int geburtsjahr) {
        this.id = id;
        this.name = name;
        this.bundesland = bundesland;
        this.geburtsjahr = geburtsjahr;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBundesland() {
        return bundesland;
    }

    public int getGeburtsjahr() {
        return geburtsjahr;
    }

    @Override
    public String toString() {
        return "Einwohner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bundesland='" + bundesland + '\'' +
                ", geburtsjahr=" + geburtsjahr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Einwohner einwohner = (Einwohner) o;
        return id == einwohner.id && geburtsjahr == einwohner.geburtsjahr && Objects.equals(name, einwohner.name) && Objects.equals(bundesland, einwohner.bundesland);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bundesland, geburtsjahr);
    }

    @Override
    public int compareTo(Einwohner o) {
        int result = this.getName().compareTo(o.getName());
        if(result == 0)
            Integer.compare(this.getId(), o.getId());
        return result;
    }
}
