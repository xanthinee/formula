package qualifyingresults;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.stream.Stream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

public class OutputBuilder {


    private int findLongest(Stream<PilotTime> stream, Function<Pilot, String> function) {
            return stream.map(pilotTime -> function.apply(pilotTime.getPilot()).length()).
                    max(Comparator.comparing(integer -> integer))
                    .orElseThrow(() -> new IllegalStateException("Can't get max length for empty stream"));
    }

    public String makeTable(Stream<PilotTime> stream) {

        Function<Pilot, String> nameGetter = Pilot::getName;
        Function<Pilot, String> teamGetter = Pilot::getTeam;
        List<PilotTime> pilotsList = stream.toList();
        int longestName = findLongest(pilotsList.stream(), nameGetter);
        int longestTeam = findLongest(pilotsList.stream(), teamGetter);
        int timeLength = pilotsList.get(0).getDuration().toString().length();

        AtomicInteger index = new AtomicInteger(0);
        List<String> formattedList = pilotsList.stream().map(s -> stringFormat
                        (s, index.incrementAndGet(), longestName, longestTeam, timeLength)).collect(Collectors.toList());
        int qualifiedToNextQ = 15;
        formattedList.add(qualifiedToNextQ,Utils.repeat('-', formattedList.get(0).length()));
        return formattedList.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private static String stringFormat(PilotTime pilotTime, int nameLength, int teamLength, int timeLength) {
        return String.format("%-" + nameLength + "s | %-" + teamLength + "s | %-" + timeLength + "s", pilotTime.getPilot().getName(), pilotTime.getPilot().getTeam(),
                durationFormatter(pilotTime.getDuration()));
    }

    public static String durationFormatter(Duration duration){

        long millis = duration.toMillis();
        DateFormat fmt = new SimpleDateFormat("m:ss.SSS");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return (millis/3600000/*hours*/)+fmt.format(new Date(millis));
    }
}
