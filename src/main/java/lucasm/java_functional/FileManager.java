package lucasm.java_functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileManager {

  private static final Logger logger = LoggerUtils.getLogger();

  public Stream<String> getStreamStringFromDirectory(String directory) {
    try {
      return Files.list(Paths.get(directory))
          .filter(Files::isRegularFile)
          .flatMap(path -> {
            try {
              return Files.lines(path);
            } catch (IOException e) {
              logger.log(Level.SEVERE,
                  "Error reading file %s. %n%s".formatted(path, e.fillInStackTrace()));
              return Stream.empty();
            }
          });
    } catch (IOException e) {
      logger.log(Level.SEVERE,
          "Error processing directory %s. %n%s".formatted(directory, e.fillInStackTrace()));
      return Stream.empty();
    }
  }
}
