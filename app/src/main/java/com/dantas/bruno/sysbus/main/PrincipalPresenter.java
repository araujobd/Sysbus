package com.dantas.bruno.sysbus.main;


import com.dantas.bruno.sysbus.model.Coordenada;
import com.dantas.bruno.sysbus.model.Onibus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by bruno on 12/06/17.
 */

public class PrincipalPresenter implements Contrato.PrincipalPresenter {

  private Contrato.PrincipalView view;
  private Contrato.MapaView fragmento;
  private FirebaseAuth auth;

  public PrincipalPresenter(Contrato.PrincipalView view) {
    this.view = view;
    auth = FirebaseAuth.getInstance();
  }

  @Override
  public void setFragmento(Contrato.MapaView fragmento) {
    this.fragmento = fragmento;
  }

  @Override
  public void desenharRota(List<Coordenada> coordenadas, Onibus onibus) {
    if (fragmento != null)
      fragmento.desenharRota(coordenadas, onibus);
  }

  @Override
  public void finish() {
    this.fragmento = null;
    this.view = null;
    this.auth = null;
  }

  @Override
  public void buscarUsuario() {
    FirebaseUser user = auth.getCurrentUser();
    String nome, email;
    nome = user.getDisplayName();
    email = user.getEmail();
    view.mostrarDadosUsuario(nome, email);
  }

  @Override
  public void verificarUsuario() {
    if (auth.getCurrentUser() == null)
      view.login();
  }
}
