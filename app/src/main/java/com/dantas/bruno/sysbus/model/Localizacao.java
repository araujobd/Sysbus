package com.dantas.bruno.sysbus.model;

/**
 * Created by bruno on 21/06/17.
 */

public class Localizacao {

  private String hora;
  private double latitude;
  private double longitude;
  private Onibus onibusatual;

  public Localizacao() {
  }

  public Localizacao(String hora, double latitude, double longitude, Onibus onibusatual) {
    this.hora = hora;
    this.latitude = latitude;
    this.longitude = longitude;
    this.onibusatual = onibusatual;
  }

  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
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

  public Onibus getOnibusatual() {
    return onibusatual;
  }

  public void setOnibusatual(Onibus onibusatual) {
    this.onibusatual = onibusatual;
  }
}
