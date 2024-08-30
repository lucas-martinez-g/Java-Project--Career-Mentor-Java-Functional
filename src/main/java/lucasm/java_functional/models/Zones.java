package lucasm.java_functional.models;

import java.util.EnumMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public final class Zones {

  private Zones() {
  }

  private static EnumMap<ZoneChar, Zone> zoneHashMap;

  public static void createZones() {
    zoneHashMap = new EnumMap<>(ZoneChar.class);

    Zone zoneA = new Zone(ZoneChar.A, 1100.0, 5000.0, 15000.0, 1.0);
    Zone zoneB = new Zone(ZoneChar.B, 1000.0, 4500.0, 20000.0, 1.2);
    Zone zoneC = new Zone(ZoneChar.C, 900.0, 4000.0, 25000.0, 0.85);
    Zone zoneD = new Zone(ZoneChar.D, 800.0, 3500.0, 30000.0, 1.1);
    Zone zoneE = new Zone(ZoneChar.E, 700.0, 3000.0, 35000.0, 0.95);

    zoneHashMap.putAll(Map.of(
        zoneA.getZoneChar(), zoneA,
        zoneB.getZoneChar(), zoneB,
        zoneC.getZoneChar(), zoneC,
        zoneD.getZoneChar(), zoneD,
        zoneE.getZoneChar(), zoneE
    ));
  }

  public static Zone getZoneByChar(ZoneChar zoneChar) {
    return zoneHashMap.get(zoneChar);
  }

  @Data
  @AllArgsConstructor
  public static class Zone {

    private ZoneChar zoneChar;
    private Double metersMultiplier;
    private Double roomsMultiplier;
    private Double garageMultiplier;
    private Double zoneMultiplier;
  }
}