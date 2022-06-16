package qualifyingresults;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.stream.Stream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

public class OutputBuilder {


    private Pilot findLongest(Stream<PilotTime> stream, Function<Pilot, String> functionName) {

            PilotTime pName = stream.max(Comparator.comparing(pilotTime -> functionName.apply(pilotTime.getPilot()).length()))
                    .orElseThrow(() -> new IllegalStateException("Can't get max length for empty stream"));
            return pName.getPilot();
    }

    private int findLength(Pilot pilot, Function<Pilot, String> function) {
        return function.apply(pilot).length();
    }

    public String testMethod(Stream<PilotTime> stream) {

        Function<Pilot, String> functionName = Pilot::getName;
        Function<Pilot, String> functionTeam = Pilot::getTeam;
        List<PilotTime> ptList = stream.toList();
        int longestName = findLength(findLongest(ptList.stream(), functionName), functionName);
        int longestTeam = findLength(findLongest(ptList.stream(), functionTeam), functionTeam);
        int timeLength = ptList.get(0).getDuration().toString().length();

        StringBuilder sb = new StringBuilder();
        List<String> resultsList = ptList.stream().map(s -> stringFormat
                (s,longestName,longestTeam,timeLength)).collect(Collectors.toList());

        int position = 1;
        int totalLength = longestName + longestTeam + timeLength;
        for(String str : resultsList) {
            if (position < 10) {
                resultsList.set(resultsList.indexOf(str), position + ".  " + str );
            } else {
                resultsList.set(resultsList.indexOf(str), position + ". " + str );
            }
            position += 1;
        }

        resultsList.add(15, Utils.repeat('-', totalLength + 9));
        for (String str : resultsList) {
            sb.append(str).append(System.lineSeparator());
        }
       return sb.toString();
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