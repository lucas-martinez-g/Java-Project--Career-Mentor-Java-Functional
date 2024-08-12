package lucasm.java_functional;

import java.util.TreeMap;
import java.util.logging.Logger;

public class Main {

  public static void main(String[] args) {
    final Logger logger = LoggerUtils.getLogger();

    WordsCounter wordsCounter = new WordsCounter("src/main/resources/txt");
    wordsCounter.updateWordsCounter();
    TreeMap<String, Integer> treeMap = new TreeMap<>(wordsCounter.getWordsCounter());
    String words = treeMap.toString();
    logger.info(words);
  }
}
