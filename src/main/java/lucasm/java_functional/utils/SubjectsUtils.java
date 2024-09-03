package lucasm.java_functional.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SubjectsUtils {

  private Double passGrade;

  public Map<String, Double> getPassedSubjets(HashMap<String, Double> subjects) {
    return subjects.entrySet().stream()
        .filter(entry -> entry.getValue() >= passGrade)
        .collect(Collectors.toMap(k -> k.getKey().toUpperCase(), Entry::getValue, (m1, m2) -> m1,
            HashMap::new));
  }
}
