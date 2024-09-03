package lucasm.java_functional.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import lucasm.java_functional.models.Property;
import lucasm.java_functional.models.Realtor;

public class RealtorUtils {

  static final Logger logger = LoggerUtils.getLogger();

  public static Realtor createRealtor(String directory) {
    Realtor realtor = new Realtor();
    List<Property> properties = new ArrayList<>();

    FileManager fileManager = new FileManager();
    Stream<String> lines = fileManager.getStreamStringFromDirectory(directory);
    lines
        .map(line -> line.split(", "))
        .toList()
        .forEach(attributes -> {
          try {
            properties.add(new Property(attributes));
          } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Invalid quantity of property parameters: %s".formatted(
                Arrays.stream(attributes).toList()));
          }
        });

    realtor.getProperties().addAll(properties);
    return realtor;
  }
}
