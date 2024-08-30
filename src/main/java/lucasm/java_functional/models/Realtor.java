package lucasm.java_functional.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.Data;
import lucasm.java_functional.filters.property.FilterProperty;

@Data
public class Realtor {

  private List<Property> properties;

  public Realtor() {
    this.properties = new ArrayList<>();
  }

  public List<Property> getPropertiesByFilters(FilterProperty... filterProperties) {
    return properties.stream()
        .filter(property -> Stream.of(filterProperties)
            .allMatch(filterProperty -> filterProperty.pass(property)))
        .toList();
  }
}
