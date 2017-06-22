package com.dantas.bruno.sysbus.model;

import java.io.Serializable;

/**
 * Created by bruno on 02/06/17.
 */

public class Coordenada implements Serializable {

  private double lat;
  private double lng;

  public Coordenada() {

  }

  public Coordenada(double lat, double lng) {
    this.lat = lat;
    this.lng = lng;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

}
