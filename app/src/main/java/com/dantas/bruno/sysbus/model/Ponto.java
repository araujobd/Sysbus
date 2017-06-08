package com.dantas.bruno.sysbus.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bruno on 02/06/17.
 */

public class Ponto implements Serializable {

  private String descricao;
  private double latitude;
  private double longitude;

  public Ponto() {

  }

  public Ponto(String descricao, double latitude, double longitude) {
    this.descricao = descricao;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
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
}
