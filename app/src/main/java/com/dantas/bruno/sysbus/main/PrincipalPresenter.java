package com.dantas.bruno.sysbus.main;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by bruno on 12/06/17.
 */

public class PrincipalPresenter implements Contrato.PrincipalPresenter {

  private Contrato.PrincipalView view;
  private FirebaseAuth auth;

  public PrincipalPresenter(Contrato.PrincipalView view) {
    this.view = view;
  }

  @Override
  public void verificarUsuario() {
    auth = FirebaseAuth.getInstance();
    if (auth.getCurrentUser() == null)
      view.login();
  }
}
