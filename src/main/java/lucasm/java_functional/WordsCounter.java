package lucasm.java_functional;

import java.util.HashMap;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordsCounter {

  private HashMap<String, Integer> wordsCounter;
  private String directory;
  private final FileManager fileManager;

  WordsCounter(String directory) {
    wordsCounter = new HashMap<>();
    fileManager = new FileManager();
    this.directory = directory;
  }

  public void updateWordsCounter() {
    try (Stream<String> lines = fileManager.getStreamStringFromDirectory(directory)) {
      lines
          .flatMap(line -> Stream.of(line.split("\\W")))
          .filter(s -> s.matches("\\w+"))
          .forEach(s -> wordsCounter.put(s, wordsCounter.getOrDefault(s, 0) + 1));
    }
  }
}
