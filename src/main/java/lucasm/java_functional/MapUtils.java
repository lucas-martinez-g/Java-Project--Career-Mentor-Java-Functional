package lucasm.java_functional;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
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

  private static <K, V> Map<K, V> collectMap(Stream<Map.Entry<K, V>> stream) {
    return stream.collect(Collectors.toMap(
        Map.Entry::getKey,
        Map.Entry::getValue,
        (e1, e2) -> e1,
        LinkedHashMap::new
    ));
  }
}
