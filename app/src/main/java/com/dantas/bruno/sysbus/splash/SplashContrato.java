package com.dantas.bruno.sysbus.splash;

/**
 * Created by bruno on 03/06/17.
 */

public interface SplashContrato {

  interface View {
    void logar();
    void iniciarPrincipal();
  }

  interface Presenter {
    void verificarLogin();
    void finish();
  }

}
