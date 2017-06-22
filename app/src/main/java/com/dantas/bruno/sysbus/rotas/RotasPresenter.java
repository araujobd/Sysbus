package com.dantas.bruno.sysbus.rotas;


import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.repositorio.Repositorio;
import com.dantas.bruno.sysbus.repositorio.RepositorioImpl;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 21/06/17.
 */

public class RotasPresenter implements Contrato.Presenter {

  private Contrato.View view;
  private Repositorio repositorio;


  public RotasPresenter(Contrato.View view) {
    this.view = view;
    repositorio = RepositorioImpl.getInstance();
  }


  @Override
  public void finish() {
    view = null;
  }



  @Override
  public void escolher(Trajeto trajeto) {
    view.retornarResultado(trajeto);
  }

  @Override
  public void buscarTrajetos() {
    repositorio.buscarTrajetos(new Listener.Trajetos() {

      @Override
      public void onReady(List<Trajeto> trajetos) {
        view.configurarLista(trajetos);
      }
    });
  }
}
