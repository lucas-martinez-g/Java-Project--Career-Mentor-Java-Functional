package lucasm.java_functional.filters.property;

import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;

@AllArgsConstructor
public class FilterPropertyOR implements FilterProperty {

  private FilterProperty filterProperty1;
  private FilterProperty filterProperty2;

  @Override
  public boolean pass(Property property) {
    return Boolean.logicalOr(filterProperty1.pass(property), filterProperty2.pass(property));
  }
}
