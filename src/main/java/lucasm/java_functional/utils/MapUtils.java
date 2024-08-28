package lucasm.java_functional.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapUtils {

  public static <K, V> Map<K, V> sortMapByKeyComparator(Map<K, V> map, Comparator comparator) {
    Map<K, V> sortedMap = new TreeMap<>(comparator);
    sortedMap.putAll(map);
    return sortedMap;
  }

  public static <K, V> Map<K, V> sortMapByValueComparator(Map<K, V> map,
      Comparator<Map.Entry<K, V>> comparator) {
    return collectMap(map.entrySet().stream().sorted(comparator));
  }

  public static <K, V> Map<K, V> sortMapByValueComparatorReversed(Map<K, V> map,
      Comparator<Map.Entry<K, V>> comparator) {
    return collectMap(map.entrySet().stream().sorted(comparator.reversed()));
  }

  public static <K, V extends Comparable<V>> List<K> getKeysWithHigherValue(Map<K, V> map) {
    return getKeysWithRequiredValues(map, Collections.max(map.values()));
  }

  public static <K, V extends Comparable<V>> List<K> getKeysWithLowestValue(Map<K, V> map) {
    return getKeysWithRequiredValues(map, Collections.min(map.values()));
  }

  public static <K, V extends Number> Double getAverageValue(Map<K, V> map) {
    return map.values().stream()
        .filter(Objects::nonNull)
        .mapToDouble(Number::doubleValue)
        .sum() / map.size();
  }

  private static <K, V extends Comparable<V>> List<K> getKeysWithRequiredValues(Map<K, V> map,
      V valueToCompare) {
    if (map.isEmpty()) {
      return List.of();
    }
    return map.entrySet().stream()
        .filter(entry -> entry.getValue().compareTo(valueToCompare) == 0)
        .map(Entry::getKey)
        .toList();
  }

  private static <K, V> Map<K, V> collectMap(Stream<Map.Entry<K, V>> stream) {
    return stream.collect(Collectors.toMap(
        Map.Entry::getKey,
        Map.Entry::getValue,
        (e1, e2) -> e1,
        LinkedHashMap::new
    ));
  }
}
