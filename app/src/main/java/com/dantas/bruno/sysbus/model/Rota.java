package com.dantas.bruno.sysbus.model;

import java.io.Serializable;

/**
 * Created by bruno on 02/06/17.
 */

public class Rota implements Serializable {

  private String nome;
  private String descricao;

  public Rota() {

  }

  public Rota(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
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
}
