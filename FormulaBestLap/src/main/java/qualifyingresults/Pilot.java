package qualifyingresults;

import java.util.Objects;

public class Pilot {

    private String abbreviation;
    private String name;
    private String team;

    public Pilot(String abbreviation, String name, String team) {

        this.abbreviation = abbreviation;
        this.name = name;
        this.team = team;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
    public String getName() {
        return name;
    }
    public String getTeam() {
        return team;
    }

    public String getInfo(){
        return abbreviation + " " + name + " " + team;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilot pilot = (Pilot) o;
        return abbreviation.equals(pilot.abbreviation) && name.equals(pilot.name) && team.equals(pilot.team);
    }
    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, name, team);
    }
}
