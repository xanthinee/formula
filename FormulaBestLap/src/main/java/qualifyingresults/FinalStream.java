package qualifyingresults;

import java.util.function.Function;
import java.util.stream.Stream;
import java.util.*;
import java.util.stream.Collectors;
import java.time.Duration;

public class FinalStream {
    private PilotTime classConverter(Pilot pilot, Map<String, Time> startTimes, Map<String, Time> endTimes) {

        String abbr = pilot.getAbbreviation();
        Time startTime = startTimes.get(abbr);
        Time endTime = endTimes.get(abbr);
        if (startTime == null) {
            throw new IllegalStateException("Can't find start time for " + abbr);
        }
        if (endTime == null) {
            throw new IllegalStateException("Can't find end time for " + abbr);
        }
        return new PilotTime(pilot, Duration.between(startTime.getTime(),endTime.getTime()));
    }
    public Stream<PilotTime> lapTimesStream(Stream<Pilot> pilots, Stream<Time> startTimes, Stream<Time> endTimes) {
        Map<String, Time> startTimesMap = startTimes.collect(Collectors.toMap(Time::getAbbreviation, Function.identity()));
        Map<String, Time> endTimesMap = endTimes.collect(Collectors.toMap(Time::getAbbreviation, Function.identity()));
        return pilots.map((s) -> classConverter(s, startTimesMap, endTimesMap)).sorted(Comparator.comparing(PilotTime::getDuration));
    }
}
