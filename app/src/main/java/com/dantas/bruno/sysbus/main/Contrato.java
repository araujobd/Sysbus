package com.dantas.bruno.sysbus.main;

import android.support.v4.app.Fragment;

/**
 * Created by bruno on 12/06/17.
 */

public interface Contrato {

  interface View {
    void trocarFragmento(Fragment fragmento, final String TAG);

    void sair();
//    void sobre();
    void favoritos();

    void login();
  }

  interface Presenter {
    void verificarUsuario();
  }
}
