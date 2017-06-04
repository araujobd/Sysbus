package com.dantas.bruno.sysbus.domain;

import java.io.Serializable;

/**
 * Created by bruno on 02/06/17.
 */

public class Coordenada implements Serializable {

  private long latitude;
  private long longitude;

  public Coordenada() {

  }

  public Coordenada(long latitude, long longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public long getLatitude() {
    return latitude;
  }

  public void setLatitude(long latitude) {
    this.latitude = latitude;
  }

  public long getLongitude() {
    return longitude;
  }

  public void setLongitude(long longitude) {
    this.longitude = longitude;
  }

}
