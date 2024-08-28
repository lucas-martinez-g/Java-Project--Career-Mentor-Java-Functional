package lucasm.java_functional.filters.property;

import lucasm.java_functional.models.Property;

public interface FilterProperty {

  boolean pass(Property property);
}
