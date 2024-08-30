package lucasm.java_functional.filters.property;

import lucasm.java_functional.models.Property;

public class FilterPropertyHasGarage implements FilterProperty {

  @Override
  public boolean pass(Property property) {
    return property.getGarage();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
