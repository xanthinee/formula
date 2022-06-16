package qualifyingresults;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.net.URL;
import java.net.URISyntaxException;

public class FileDataStream {

    public Stream<String> getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);

        try {
            return Files.lines(Paths.get(url.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}
