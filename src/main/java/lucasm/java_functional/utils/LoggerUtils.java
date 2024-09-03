package lucasm.java_functional.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.Getter;
import lucasm.java_functional.filters.property.FilterProperty;
import lucasm.java_functional.models.Property;

public class LoggerUtils {

  @Getter
  private static final Logger logger = Logger.getLogger(LoggerUtils.class.getName());
  private static final String RESET = "\033[0m";
  private static final String BLUE = "\033[0;34m";

  static {
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(new SimpleFormatter());
    logger.addHandler(handler);
    logger.setLevel(Level.ALL);
    handler.setLevel(Level.ALL);
    logger.setUseParentHandlers(false);
  }

  public static void logProperty(Property property) {
    logger.info(getFormattedString("Details of property: %s", property.toString()));
  }

  public static String getFormattedString(String originalString, Object... stringsArgs) {
    return BLUE + originalString.formatted(stringsArgs) + RESET;
  }

  public static void logFilteredProperties(FilterProperty... filterProperties) {
    String format = generateFormatString("Filtered properties by", filterProperties.length);
    String[] classNames = Stream.of(filterProperties)
        .map(Object::toString)
        .toArray(String[]::new);
    logger.info(getFormattedString(format, (Object[]) classNames));
  }

  private static String generateFormatString(String base, int count) {
    String placeholders = IntStream.range(0, count)
        .mapToObj(i -> "%s")
        .collect(Collectors.joining(", "));
    return base + " " + placeholders + ":";
  }
}
