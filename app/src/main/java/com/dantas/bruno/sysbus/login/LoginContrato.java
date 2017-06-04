package com.dantas.bruno.sysbus.login;

/**
 * Created by bruno on 03/06/17.
 */

public interface LoginContrato {

  interface View {
    void mostrarErroLogin();
    void iniciarPrincipal();
  }

  interface Presenter {
    void loginComFirebase(String email, String senha);
  }

}
