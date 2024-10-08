package lucasm.java_functional.filters.property;

import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;

@AllArgsConstructor
public class FilterPropertyPriceHigherThan implements FilterProperty {

  private Double price;

  @Override
  public boolean pass(Property property) {
    return property.getPrice() > price;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "." + price;
  }
}
