package lucasm.java_functional.filters.property;

import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;

@AllArgsConstructor
public class FilterPropertyAND implements FilterProperty {

  private FilterProperty filterProperty1;
  private FilterProperty filterProperty2;

  @Override
  public boolean pass(Property property) {
    return Boolean.logicalAnd(filterProperty1.pass(property), filterProperty2.pass(property));
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ".(%s&%s)".formatted(filterProperty1.toString(),
        filterProperty2.toString());
  }
}
