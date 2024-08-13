package lucasm.java_functional;

import java.util.HashMap;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordsCounter {

  private HashMap<String, Integer> wordsCounterMap;
  private String directory;
  private final FileManager fileManager;

  WordsCounter(String directory) {
    wordsCounterMap = new HashMap<>();
    fileManager = new FileManager();
    this.directory = directory;
  }

  public void updateWordsCounterMap() {
    try (Stream<String> lines = fileManager.getStreamStringFromDirectory(directory)) {
      lines
          .flatMap(line -> Stream.of(line.split("\\W")))
          .filter(s -> s.matches("\\w+"))
          .forEach(s -> wordsCounterMap.put(s, wordsCounterMap.getOrDefault(s, 0) + 1));
    }
  }
}
