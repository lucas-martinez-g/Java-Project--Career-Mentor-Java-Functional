package lucasm.java_functional.filters.property;

import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;

@AllArgsConstructor
public class FilterPropertyNOT implements FilterProperty {

  private FilterProperty filterProperty;

  @Override
  public boolean pass(Property property) {
    return !filterProperty.pass(property);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ".(%s)".formatted(filterProperty.toString());
  }
}
