package com.dantas.bruno.sysbus.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 02/06/17.
 */

public class Coordenada implements Serializable {

  private double latitude;
  private double longitude;
  private String titulo;

  public Coordenada() {

  }

  public Coordenada(String titulo, double latitude, double longitude) {
    this.titulo = titulo;
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

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }


  public static List<Coordenada> getPontos() {
    List<Coordenada> coordenadas = new ArrayList<>();

    coordenadas.add(new Coordenada("P1", -6.4593231, -37.0978831));
    coordenadas.add(new Coordenada("P2", -6.4613393, -37.0962158));
    coordenadas.add(new Coordenada("P3", -6.4696353, -37.0962158));
    coordenadas.add(new Coordenada("P4", -6.4624969, -37.0945871));
    coordenadas.add(new Coordenada("P5", -6.4627405, -37.0938243));

    return coordenadas;
  }

}
