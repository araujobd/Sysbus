package com.dantas.bruno.sysbus.model;

/**
 * Created by bruno on 01/06/17.
 */

public class User {

  private String nome;
  private String email;

  public User() {
  }

  public User(String nome, String email) {
    this.nome = nome;
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
