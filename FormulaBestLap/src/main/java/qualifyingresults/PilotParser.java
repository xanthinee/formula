package qualifyingresults;

public class PilotParser implements Parser<Pilot> {

    @Override
    public Pilot parse(String str) {
        String[] partsOfFullName = str.split("_");
        return new Pilot(partsOfFullName[0], partsOfFullName[1], partsOfFullName[2]);
    }
}
