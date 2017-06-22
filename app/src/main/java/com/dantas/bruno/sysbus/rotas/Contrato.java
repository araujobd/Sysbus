package com.dantas.bruno.sysbus.rotas;

import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 21/06/17.
 */

public interface Contrato {

  interface View {
    void retornarResultado(Trajeto trajeto);
    void configurarLista(List<Trajeto> trajetos);
  }

  interface Presenter {
    void finish();
    void escolher(Trajeto trajeto);
    void buscarTrajetos();
  }
}
