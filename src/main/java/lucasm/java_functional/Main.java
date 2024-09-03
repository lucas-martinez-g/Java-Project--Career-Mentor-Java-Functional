package lucasm.java_functional;

import static lucasm.java_functional.utils.LoggerUtils.getFormattedString;
import static lucasm.java_functional.utils.LoggerUtils.logFilteredProperties;
import static lucasm.java_functional.utils.RealtorUtils.createRealtor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import lucasm.java_functional.filters.property.FilterProperty;
import lucasm.java_functional.filters.property.FilterPropertyAntiquityHigherThan;
import lucasm.java_functional.filters.property.FilterPropertyHasGarage;
import lucasm.java_functional.filters.property.FilterPropertyMetersHigherThan;
import lucasm.java_functional.filters.property.FilterPropertyNOT;
import lucasm.java_functional.filters.property.FilterPropertyOR;
import lucasm.java_functional.filters.property.FilterPropertyPriceHigherThan;
import lucasm.java_functional.filters.property.FilterPropertyRoomsHigherThan;
import lucasm.java_functional.models.Realtor;
import lucasm.java_functional.models.Student;
import lucasm.java_functional.models.Zones;
import lucasm.java_functional.utils.LoggerUtils;
import lucasm.java_functional.utils.MapUtils;
import lucasm.java_functional.utils.SubjectsUtils;
import lucasm.java_functional.utils.WordsCounter;

public class Main {

  static final Logger logger = LoggerUtils.getLogger();

  public static void main(String[] args) {

//    useMapUtils();
//    usePropertyRealtor();
    useSubjectsUtils();
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

    Realtor realtor = createRealtor("src/main/resources/txt/realtorproperties");

    logger.info(getFormattedString("All properties:"));

    realtor.getProperties().forEach(LoggerUtils::logProperty);

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
        .forEach(LoggerUtils::logProperty);
  }

  private static void useSubjectsUtils() {

    HashMap<String, Double> subjectsGrades1 = new HashMap<>(
        Map.of("Maths", 10.0, "History", 3.0, "Physics", 8.9, "Chemistry", 9.5, "Genetic", 1.5));
    HashMap<String, Double> subjectsGrades2 = new HashMap<>(
        Map.of("Physical Education", 7.8, "Geography", 0.2, "Literature ", 9.5, "Religion", 4.0,
            "Art", 3.3));

    Student student1 = new Student("Lucas", subjectsGrades1);
    Student student2 = new Student("Gabriel", subjectsGrades2);

    SubjectsUtils subjectsUtils = new SubjectsUtils(4.0);

    logger.info(getFormattedString("Student: %s%n\t\t\tPassed subjects: %s".formatted(
        student1.getName(), subjectsUtils.getPassedSubjets(student1.getSubjectsGrades()))));
    logger.info(getFormattedString("Student: %s%n\t\t\tPassed subjects: %s".formatted(
        student2.getName(), subjectsUtils.getPassedSubjets(student2.getSubjectsGrades()))));
  }
}
