package com.dantas.bruno.sysbus.model;

/**
 * Created by bruno on 16/06/17.
 */

public class Onibus {

  private String uid;
  private int identificacao;
  private String status;

  public Onibus() {
  }

  public Onibus(String uid, int identificacao, String status) {
    this.uid = uid;
    this.identificacao = identificacao;
    this.status = status;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public int getIdentificacao() {
    return identificacao;
  }

  public void setIdentificacao(int identificacao) {
    this.identificacao = identificacao;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
