package com.dantas.bruno.sysbus.main;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by bruno on 12/06/17.
 */

public class PrincipalPresenter implements Contrato.Presenter {

  private Contrato.View view;
  private FirebaseAuth auth;

  public PrincipalPresenter(Contrato.View view) {
    this.view = view;
  }

  @Override
  public void verificarUsuario() {
    auth = FirebaseAuth.getInstance();
    if (auth.getCurrentUser() == null)
      view.login();
  }
}
