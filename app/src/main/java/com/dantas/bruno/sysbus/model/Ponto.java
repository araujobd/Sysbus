package com.dantas.bruno.sysbus.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bruno on 02/06/17.
 */

public class Ponto implements Serializable {

  private String uid;
  private String descricao;
  private double latitude;
  private double longitude;

  public Ponto() {

  }

  public Ponto(String uid, String descricao, double latitude, double longitude) {
    this.uid = uid;
    this.descricao = descricao;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
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
