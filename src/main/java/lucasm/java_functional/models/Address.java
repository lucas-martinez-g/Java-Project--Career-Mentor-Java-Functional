package lucasm.java_functional.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Address {

  @NonNull
  private String street;
  @NonNull
  private String number;
  private String floor;
  private String apartment;
  @NonNull
  private String city;
  @NonNull
  private String state;
  @NonNull
  private String country;

  @Override
  public String toString() {
    return "{" +
        "street='" + street + '\'' +
        ", number=" + number +
        ", floor=" + floor +
        ", apartment=" + apartment +
        ", city=" + city +
        ", state=" + state +
        ", country=" + country +
        '}';
  }
}
