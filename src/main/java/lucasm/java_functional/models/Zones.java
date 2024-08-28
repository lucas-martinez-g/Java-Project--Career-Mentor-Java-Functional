package lucasm.java_functional.models;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Zones {

  private static HashMap<Character, Zone> zones;

  public static Zone getZoneByChar(Character character) {
    return zones.get(character);
  }

  @Data
  public class Zone {

    private Character zoneChar;
    private Double metersMultiplier;
    private Double roomsMultiplier;
    private Double garageMultiplier;
    private Double zoneMultiplier;
  }
}