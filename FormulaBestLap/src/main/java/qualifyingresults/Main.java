package qualifyingresults;

import java.time.Duration;
import java.util.stream.Stream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@SuppressWarnings("java:S106")
public class Main {

    public static LocalDateTime toLapTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        return LocalDateTime.parse(str, formatter);
    }

    public static Duration getDuration(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end);
    }

    public static String getMillisecond(Duration duration){

        long millis = duration.toMillis();
        DateFormat fmt = new SimpleDateFormat("m:ss.SSS");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return (millis/3600000/*hours*/)+fmt.format(new Date(millis));
    }
    public static void main(String[] args) {

        PilotParser pp = new PilotParser();
        TimeParser tp = new TimeParser();
        FileDataStream streamData = new FileDataStream();
        FinalStream fs = new FinalStream();

        Stream<String> abb = streamData.getFileFromResourceAsStream("timingsdata/abbreviations.txt");
        Stream<Pilot> pilots = abb.map((s) -> pp.parse(s));
        Stream<String> startStrings = streamData.getFileFromResourceAsStream("timingsdata/start.log");
        Stream<Time> startTimes = startStrings.map((s) -> tp.parse(s));
        Stream<String> endStrings = streamData.getFileFromResourceAsStream("timingsdata/end.log");
        Stream<Time> endTimes = endStrings.map((s) -> tp.parse(s));

        Stream<PilotTime> testStream = fs.lapTimesStream(pilots, startTimes, endTimes);
        OutputBuilder oBuilder = new OutputBuilder();
        System.out.println(oBuilder.testMethod(testStream));

//        oBuilder.testMethod(testStream);

    }
}
