package com.dantas.bruno.sysbus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 02/06/17.
 */

public class Coordenada implements Serializable {

  private double latitude;
  private double longitude;

  public Coordenada() {

  }

  public Coordenada(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public static List<Coordenada> getPontos() {
    List<Coordenada> coordenadas = new ArrayList<>();

    coordenadas.add(new Coordenada(-6.4593231, -37.0978831));
    coordenadas.add(new Coordenada(-6.4613393, -37.0962158));
    coordenadas.add(new Coordenada(-6.4696353, -37.0962158));
    coordenadas.add(new Coordenada(-6.4624969, -37.0945871));
    coordenadas.add(new Coordenada(-6.4627405, -37.0938243));
    coordenadas.add(new Coordenada( -6.467900, -37.084776));

    return coordenadas;
  }

}
