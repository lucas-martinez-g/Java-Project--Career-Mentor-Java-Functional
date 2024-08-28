package lucasm.java_functional.models;

import lombok.Data;
import lucasm.java_functional.models.Zones.Zone;

@Data
public class Property {

  private Integer years;
  private Integer meters;
  private Integer rooms;
  private Boolean garage;
  private Character zoneChar;

  public double getPrice() {
    Zone zone = Zones.getZoneByChar(zoneChar);
    return (meters * zone.getMetersMultiplier() + rooms * zone.getRoomsMultiplier() +
        (Boolean.TRUE.equals(garage) ? zone.getGarageMultiplier() : 0)) *
        ((double) (1 - years) / 100) * zone.getZoneMultiplier();
  }
}
