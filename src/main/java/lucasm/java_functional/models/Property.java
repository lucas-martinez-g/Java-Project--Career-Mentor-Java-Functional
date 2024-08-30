package lucasm.java_functional.models;

import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;
import lucasm.java_functional.models.Zones.Zone;

@Data
public class Property {

  @NonNull
  private String name;
  @NonNull
  private Address address;
  @NonNull
  private Integer year;
  @NonNull
  private Integer meters;
  @NonNull
  private Integer rooms;
  @NonNull
  private Boolean garage;
  @NonNull
  private ZoneChar zoneChar;

  public Double getPrice() {
    Zone zone = Zones.getZoneByChar(zoneChar);
    return (meters * zone.getMetersMultiplier() + rooms * zone.getRoomsMultiplier() +
        (Boolean.TRUE.equals(garage) ? zone.getGarageMultiplier() : 0)) * getAntiquityMultiplier()
        * zone.getZoneMultiplier();
  }

  public Integer getAntiquity() {
    return LocalDate.now().getYear() - year;
  }

  private Double getAntiquityMultiplier() {
    return 1.0 - getAntiquity() / 100.0;
  }

  @Override
  public String toString() {
    return "{" + "name='" + name + '\'' +
        ", address=" + address +
        ", year=" + year +
        ", meters=" + meters +
        ", rooms=" + rooms +
        ", garage=" + garage +
        ", zone=" + zoneChar +
        ", price: $" + getPrice() + '}';
  }
}
