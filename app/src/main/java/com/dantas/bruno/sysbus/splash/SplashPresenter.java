package com.dantas.bruno.sysbus.splash;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by bruno on 03/06/17.
 */

public class SplashPresenter implements SplashContrato.Presenter{

  private FirebaseAuth auth;
  private SplashContrato.View view;

  public SplashPresenter(SplashContrato.View view) {
    this.view = view;
    auth = FirebaseAuth.getInstance();
  }

  @Override
  public void verificarLogin() {
    if (auth.getCurrentUser() != null)
      view.iniciarPrincipal();
    else
      view.logar();
  }

  @Override
  public void finish() {
    auth = null;
    view = null;
  }
}
