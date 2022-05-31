package v2.einwohner;

import java.util.Comparator;

public class GeburtsjahrDescNameAscComparator implements Comparator<Einwohner> {
    @Override
    public int compare(Einwohner o1, Einwohner o2) {
        int result = Integer.compare(o2.getGeburtsjahr(), o1.getGeburtsjahr());
        if(result == 0)
            result = o1.getName().compareTo(o2.getName());
        return result;
    }
}
