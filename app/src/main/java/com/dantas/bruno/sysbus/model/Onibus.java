package com.dantas.bruno.sysbus.model;

import java.io.Serializable;

/**
 * Created by bruno on 16/06/17.
 */

public class Onibus implements Serializable{

  private String uid;
  private String identificacao;
  private String status;

  public Onibus() {
  }

  public Onibus(String uid, String identificacao, String status) {
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

  public String getIdentificacao() {
    return identificacao;
  }

  public void setIdentificacao(String identificacao) {
    this.identificacao = identificacao;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
