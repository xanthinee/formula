package qualifyingresults;

import java.time.Duration;
import java.util.Objects;

public class PilotTime {

    private Pilot pilot;
    private Duration duration;


    public PilotTime(Pilot pilot, Duration duration) {
        this.pilot = pilot;
        this.duration = duration;
    }

    public String getInfo() {
        return pilot.getName() + " | " + pilot.getTeam() + " | " + " " + duration;
    }
    public Pilot getPilot() {
        return pilot;
    }
    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PilotTime pilotTime = (PilotTime) o;
        return Objects.equals(pilot, pilotTime.pilot) && Objects.equals(duration, pilotTime.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pilot, duration);
    }
}
