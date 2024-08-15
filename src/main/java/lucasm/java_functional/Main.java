package lucasm.java_functional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

  static final Logger logger = LoggerUtils.getLogger();
  static final String RESET = "\033[0m";
  static final String BLUE = "\033[0;34m";

  public static void main(String[] args) {

    WordsCounter wordsCounter = new WordsCounter("src/main/resources/txt");

    wordsCounter.updateWordsCounterMap();

    Map<String, Integer> map = wordsCounter.getWordsCounterMap();
    logger.info(getFormattedString("Map: %s", map.toString()));

    Map<String, Integer> mapSortedByKey = MapUtils.sortMapByKeyComparator(map,
        Comparator.naturalOrder());
    logger.info(getFormattedString("Ordered by keys: %s", mapSortedByKey.toString()));

    Map<String, Integer> mapSortedByKeyReversed = MapUtils.sortMapByKeyComparator(mapSortedByKey,
        Comparator.reverseOrder());
    logger.info(
        getFormattedString("Ordered reversed by keys: %s", mapSortedByKeyReversed.toString()));

    Map<String, Integer> mapSortedByValues = MapUtils.sortMapByValueComparator(mapSortedByKey,
        Map.Entry.comparingByValue());
    logger.info(getFormattedString("Ordered by values: %s", mapSortedByValues.toString()));

    Map<String, Integer> mapSortedByValuesReversed = MapUtils.sortMapByValueComparatorReversed(
        mapSortedByKey, Map.Entry.comparingByValue());
    logger.info(
        getFormattedString("Ordered reversed by values: %s", mapSortedByValuesReversed.toString()));

    List<String> listHigherValue = MapUtils.getKeysWithHigherValue(mapSortedByKey);
    logger.info(getFormattedString("List keys with highest value: %s", listHigherValue.toString()));

    List<String> listLowestValue = MapUtils.getKeysWithLowestValue(mapSortedByKey);
    logger.info(getFormattedString("List keys with highest value: %s", listLowestValue.toString()));

    Double average = MapUtils.getAverageValue(mapSortedByKey);
    logger.info(getFormattedString("Average value: %s", average.toString()));
  }

  private static String getFormattedString(String s1, String s2) {
    return BLUE + s1.formatted(s2) + RESET;
  }
}
