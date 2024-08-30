package lucasm.java_functional.filters.property;

import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;

@AllArgsConstructor
public class FilterPropertyRoomsHigherThan implements FilterProperty {

  private Integer rooms;

  @Override
  public boolean pass(Property property) {
    return property.getRooms() > rooms;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "." + rooms;
  }
}
