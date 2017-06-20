package com.dantas.bruno.sysbus.infoponto;

import android.util.Log;

import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.data.Repositorio;
import com.dantas.bruno.sysbus.data.RepositorioImpl;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 19/06/17.
 */

public class InfoParadaPresenter implements Contrato.Presenter {

  private Contrato.View view;
  private Repositorio repositorio;

  public InfoParadaPresenter(Contrato.View view) {
    this.view = view;
    this.repositorio = RepositorioImpl.getInstance();
  }

  @Override
  public void buscarTrajetos(Parada parada) {
    repositorio.buscarTrajetosNoPonto(parada, new Listener.Trajetos() {
      @Override
      public void onReady(List<Trajeto> trajetos) {
        view.configurarLista(trajetos);
      }
    });
  }

  @Override
  public void escolher(Trajeto trajeto) {
    view.retornarResultado(trajeto);
  }
}
