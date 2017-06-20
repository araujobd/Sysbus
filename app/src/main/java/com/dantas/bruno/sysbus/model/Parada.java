package com.dantas.bruno.sysbus.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bruno on 02/06/17.
 */

public class Parada implements Serializable {

  private String uid;
  private String nome;
  private String descricao;
  private double latitude;
  private double longitude;

  public Parada() {

  }

  public Parada(String uid, String nome, String descricao, double latitude, double longitude) {
    this.uid = uid;
    this.nome = nome;
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

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
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
