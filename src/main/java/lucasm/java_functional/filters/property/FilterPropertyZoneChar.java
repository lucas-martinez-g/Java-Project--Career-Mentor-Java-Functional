package lucasm.java_functional.filters.property;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lucasm.java_functional.models.Property;
import lucasm.java_functional.models.ZoneChar;

@AllArgsConstructor
public class FilterPropertyZoneChar implements FilterProperty {

  private ZoneChar zoneChar;

  @Override
  public boolean pass(Property property) {
    return Objects.equals(zoneChar, property.getZoneChar());
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "." + zoneChar;
  }
}
