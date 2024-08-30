package lucasm.java_functional.filters.property;

import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;

@AllArgsConstructor
public class FilterPropertyAntiquityHigherThan implements FilterProperty {

  private Integer antiquity;

  @Override
  public boolean pass(Property property) {
    return property.getAntiquity() > antiquity;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "." + antiquity;
  }
}
