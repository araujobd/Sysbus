package com.dantas.bruno.sysbus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 16/06/17.
 */

public class Trajeto implements Serializable {

  private String uid;
  private List<Parada> paradas;
  private List<Coordenada> coordenadas;
  private Rota rota;

  public Trajeto() {

  }

  public Trajeto(String uid, List<Parada> paradas, List<Coordenada> coordenadas, Rota rota) {
    this.uid = uid;
    this.paradas = paradas;
    this.coordenadas = coordenadas;
    this.rota = rota;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public List<Parada> getParadas() {
    return paradas;
  }

  public void setParadas(List<Parada> paradas) {
    this.paradas = paradas;
  }

  public List<Coordenada> getCoordenadas() {
    return coordenadas;
  }

  public void setCoordenadas(List<Coordenada> coordenadas) {
    this.coordenadas = coordenadas;
  }

  public Rota getRota() {
    return rota;
  }

  public void setRota(Rota rota) {
    this.rota = rota;
  }


  @Override
  public String toString() {
    return "TRAJETO uid:" + uid + " \nRota: " + rota.getNome();
  }
}
