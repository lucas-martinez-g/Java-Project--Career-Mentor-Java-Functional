package lucasm.java_functional;

import java.util.Comparator;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

  public static void main(String[] args) {
    final Logger logger = LoggerUtils.getLogger();

    WordsCounter wordsCounter = new WordsCounter("src/main/resources/txt");

    wordsCounter.updateWordsCounterMap();

    Map<String, Integer> map = wordsCounter.getWordsCounterMap();
    logger.info("Map: %s".formatted(map.toString()));

    Map<String, Integer> mapSortedByKey = MapUtils.sortMapByKeyComparator(map,
        Comparator.naturalOrder());
    logger.info("Ordered by keys: %s".formatted(mapSortedByKey.toString()));

    Map<String, Integer> mapSortedByKeyReversed = MapUtils.sortMapByKeyComparator(mapSortedByKey,
        Comparator.reverseOrder());
    logger.info("Ordered reversed by keys: %s".formatted(mapSortedByKeyReversed.toString()));

    Map<String, Integer> mapSortedByValues = MapUtils.sortMapByValueComparator(mapSortedByKey,
        Map.Entry.comparingByValue());
    logger.info("Ordered by values: %s".formatted(mapSortedByValues.toString()));

    Map<String, Integer> mapSortedByValuesReversed = MapUtils.sortMapByValueComparatorReversed(
        mapSortedByKey, Map.Entry.comparingByValue());
    logger.info("Ordered reversed by values: %s".formatted(mapSortedByValuesReversed.toString()));
  }
}
