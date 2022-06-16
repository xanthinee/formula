package qualifyingresults;

import java.time.LocalDateTime;
import java.util.Objects;

public class Time {

    private String abbreviation;
    private LocalDateTime time;

    public Time(String abbreviation, LocalDateTime time) {
        this.abbreviation = abbreviation;
        this.time = time;
    }

    public String getInfo() {
        return abbreviation + " " + time;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time1 = (Time) o;
        return abbreviation.equals(time1.abbreviation) && time.equals(time1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, time);
    }
}
