package lucasm.java_functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lucasm.java_functional.filters.property.FilterProperty;
import lucasm.java_functional.filters.property.FilterPropertyAntiquityHigherThan;
import lucasm.java_functional.filters.property.FilterPropertyHasGarage;
import lucasm.java_functional.filters.property.FilterPropertyMetersHigherThan;
import lucasm.java_functional.filters.property.FilterPropertyNOT;
import lucasm.java_functional.filters.property.FilterPropertyOR;
import lucasm.java_functional.filters.property.FilterPropertyPriceHigherThan;
import lucasm.java_functional.filters.property.FilterPropertyRoomsHigherThan;
import lucasm.java_functional.models.Property;
import lucasm.java_functional.models.Realtor;
import lucasm.java_functional.models.Zones;
import lucasm.java_functional.utils.LoggerUtils;
import lucasm.java_functional.utils.MapUtils;

public class Main {

  static final Logger logger = LoggerUtils.getLogger();
  static final String RESET = "\033[0m";
  static final String BLUE = "\033[0;34m";

  public static void main(String[] args) {

//    useMapUtils();
    usePropertyRealtor();
  }

  private static void useMapUtils() {
    WordsCounter wordsCounter = new WordsCounter("src/main/resources/txt/wordscounter");
    // add '/longtxt' in directory path to longer data

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
    logger.info(getFormattedString("List keys with lowest value: %s", listLowestValue.toString()));

    Double average = MapUtils.getAverageValue(mapSortedByKey);
    logger.info(getFormattedString("Average value: %s", average.toString()));
  }

  private static void usePropertyRealtor() {
    Zones.createZones();

    Realtor realtor = createRealtor();

    logger.info(getFormattedString("All properties:"));

    realtor.getProperties().forEach(Main::logProperty);

    FilterProperty filterProperty1 = new FilterPropertyNOT(
        new FilterPropertyPriceHigherThan(100000.0));

    FilterProperty filterProperty2 = new FilterPropertyNOT(
        new FilterPropertyAntiquityHigherThan(10));

    FilterProperty filterProperty3 = new FilterPropertyHasGarage();

    FilterProperty filterProperty4 = new FilterPropertyOR(new FilterPropertyRoomsHigherThan(2),
        new FilterPropertyNOT(new FilterPropertyMetersHigherThan(100)));

    logFilteredProperties(filterProperty1, filterProperty2, filterProperty3, filterProperty4);

    realtor.getPropertiesByFilters(filterProperty1, filterProperty2, filterProperty3,
            filterProperty4)
        .forEach(Main::logProperty);
  }

  private static Realtor createRealtor() {
    Realtor realtor = new Realtor();
    List<Property> properties = new ArrayList<>();

    FileManager fileManager = new FileManager();
    Stream<String> lines = fileManager.getStreamStringFromDirectory(
        "src/main/resources/txt/realtorproperties");
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

  private static void logProperty(Property property) {
    logger.info(getFormattedString("Details of property: %s", property.toString()));
  }

  private static String getFormattedString(String originalString, Object... stringsArgs) {
    return BLUE + originalString.formatted(stringsArgs) + RESET;
  }

  private static void logFilteredProperties(FilterProperty... filterProperties) {
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
