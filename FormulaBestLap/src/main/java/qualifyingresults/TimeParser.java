package qualifyingresults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeParser implements Parser<Time>{
    @Override
    public Time parse(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        return new Time(str.substring(0,3), LocalDateTime.parse(str.substring(3), formatter));
    }
}
