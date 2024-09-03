package lucasm.java_functional.models;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Student {

  private String name;
  private HashMap<String, Double> subjectsGrades;
}
