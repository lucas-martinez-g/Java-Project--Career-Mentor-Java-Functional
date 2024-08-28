package lucasm.java_functional.filters.property;

import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;

@AllArgsConstructor
public class FilterPropertyPriceHigherThan implements FilterProperty {

  private Double price;

  @Override
  public boolean pass(Property property) {
    return Double.compare(property.getPrice(), price) > 0;
  }
}
