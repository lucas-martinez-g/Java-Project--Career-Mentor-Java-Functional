package lucasm.java_functional.models;

import java.text.DecimalFormat;
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

  public Property(String[] attributes) {
    switch (attributes.length) {
      case 11: {
        name = attributes[0];
        address = new Address(attributes[1], attributes[2], attributes[3], attributes[4],
            attributes[5]);
        year = Integer.valueOf(attributes[6]);
        meters = Integer.valueOf(attributes[7]);
        rooms = Integer.valueOf(attributes[8]);
        garage = Boolean.valueOf(attributes[9]);
        zoneChar = ZoneChar.valueOf(attributes[10]);
        break;
      }
      case 13: {
        name = attributes[0];
        address = new Address(attributes[1], attributes[2], attributes[3], attributes[4],
            attributes[5], attributes[6], attributes[7]);
        year = Integer.valueOf(attributes[8]);
        meters = Integer.valueOf(attributes[9]);
        rooms = Integer.valueOf(attributes[10]);
        garage = Boolean.valueOf(attributes[11]);
        zoneChar = ZoneChar.valueOf(attributes[12]);
        break;
      }
      default: {
        throw new IllegalArgumentException();
      }
    }
  }

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
        ", price: $" + new DecimalFormat("#.##").format(getPrice()) + '}';
  }
}
