package lucasm.java_functional;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import lombok.Getter;

public class LoggerUtils {

  @Getter
  private static final Logger logger = Logger.getLogger(LoggerUtils.class.getName());

  static {
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(new SimpleFormatter());
    logger.addHandler(handler);
    logger.setLevel(Level.ALL);
    handler.setLevel(Level.ALL);
    logger.setUseParentHandlers(false);
  }
}
