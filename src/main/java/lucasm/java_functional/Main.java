package lucasm.java_functional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
import lucasm.java_functional.models.Address;
import lucasm.java_functional.models.Property;
import lucasm.java_functional.models.Realtor;
import lucasm.java_functional.models.ZoneChar;
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
    WordsCounter wordsCounter = new WordsCounter("src/main/resources/txt");
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

    properties.add(new Property("Simpsons's House",
        new Address("Evergreen", "742", "Springfield", "Massachusetts", "United States of America"),
        2023, 50, 3, true, ZoneChar.A));
    properties.add(new Property("Las Vegas Casino Hotel",
        new Address("Fake Street", "1234", "7", "B", "Las Vegas", "Nevada",
            "United States of America"), 2014, 65, 4, false, ZoneChar.B));
    properties.add(new Property("Central Park Apartment",
        new Address("5th Avenue", "350", "12", "A", "New York", "New York",
            "United States of America"), 2020, 80, 2, true, ZoneChar.C));
    properties.add(new Property("Beach House",
        new Address("Ocean Drive", "101", "Santa Monica", "California", "United States of America"),
        2018, 120, 4, true, ZoneChar.D));
    properties.add(new Property("Mountain Cabin",
        new Address("Pine Road", "88", "Aspen", "Colorado", "United States of America"), 2015, 90,
        3, true, ZoneChar.E));
    properties.add(new Property("Desert Villa",
        new Address("Cactus Lane", "23", "Palm Springs", "California", "United States of America"),
        2017, 110, 5, true, ZoneChar.A));
    properties.add(new Property("Downtown Loft",
        new Address("Main St", "450", "15", "C", "Chicago", "Illinois", "United States of America"),
        2019, 70, 1, false, ZoneChar.B));
    properties.add(new Property("Country House",
        new Address("Maple Avenue", "200", "Nashville", "Tennessee", "United States of America"),
        2016, 140, 6, true, ZoneChar.C));
    properties.add(new Property("Suburban Home",
        new Address("Elm Street", "67", "Austin", "Texas", "United States of America"), 2021, 95, 3,
        true, ZoneChar.D));
    properties.add(new Property("Penthouse Suite",
        new Address("Luxury Blvd", "900", "20", "P", "Miami", "Florida",
            "United States of America"), 2022, 150, 4, true, ZoneChar.E));
    properties.add(new Property("Farmhouse",
        new Address("Country Road", "345", "Omaha", "Nebraska", "United States of America"), 2013,
        200, 7, true, ZoneChar.A));
    properties.add(new Property("Lakeside Cottage",
        new Address("Lakeview Drive", "12", "Lake Tahoe", "California", "United States of America"),
        2011, 85, 2, true, ZoneChar.B));
    properties.add(new Property("Historic Mansion",
        new Address("Heritage St", "1", "Boston", "Massachusetts", "United States of America"),
        2010, 300, 10, true, ZoneChar.C));
    properties.add(new Property("Modern Condo",
        new Address("Future Lane", "77", "San Francisco", "California", "United States of America"),
        2016, 60, 2, false, ZoneChar.D));
    properties.add(new Property("Urban Studio",
        new Address("Metro St", "99", "Seattle", "Washington", "United States of America"), 2018,
        40, 1, false, ZoneChar.E));
    properties.add(new Property("Luxury Villa",
        new Address("Gold Coast", "55", "Malibu", "California", "United States of America"), 2021,
        250, 8, true, ZoneChar.A));
    properties.add(new Property("Cottage by the Woods",
        new Address("Forest Path", "5", "Portland", "Oregon", "United States of America"), 2017, 65,
        2, true, ZoneChar.B));
    properties.add(new Property("Riverside Apartment",
        new Address("River Road", "333", "2", "R", "New Orleans", "Louisiana",
            "United States of America"), 2019, 75, 2, false, ZoneChar.C));
    properties.add(new Property("Ski Lodge",
        new Address("Snowy Way", "888", "Park City", "Utah", "United States of America"), 2015, 130,
        5, true, ZoneChar.D));
    properties.add(new Property("Cozy Bungalow",
        new Address("Sunset Blvd", "444", "Santa Barbara", "California",
            "United States of America"), 2020, 55, 2, true, ZoneChar.E));

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
