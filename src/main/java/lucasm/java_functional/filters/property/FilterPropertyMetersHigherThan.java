package lucasm.java_functional.filters.property;

import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;

@AllArgsConstructor
public class FilterPropertyMetersHigherThan implements FilterProperty {

  private Integer meters;

  @Override
  public boolean pass(Property property) {
    return property.getMeters() > meters;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "." + meters;
  }
}
