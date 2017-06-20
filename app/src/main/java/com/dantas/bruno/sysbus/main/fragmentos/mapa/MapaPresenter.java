package com.dantas.bruno.sysbus.main.fragmentos.mapa;

import android.util.Log;

import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.data.Repositorio;
import com.dantas.bruno.sysbus.data.RepositorioImpl;
import com.dantas.bruno.sysbus.main.Contrato;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 16/06/17.
 */

public class MapaPresenter implements Contrato.MapaPresenter{

  private Contrato.MapaView view;
  private Contrato.PrincipalView activity;
  private Repositorio repositorio;

  public MapaPresenter(Contrato.MapaView view, Contrato.PrincipalView activity) {
    repositorio = RepositorioImpl.getInstance();
    this.view = view;
    this.activity = activity;
  }

  @Override
  public void onMapReady() {
    repositorio.buscarParadas(new Listener.Paradas(){

      @Override
      public void onReady(List<Parada> paradas) {
        view.exibirPontos(paradas);
      }
    });
  }

  @Override
  public void exibirInfoPonto(Parada parada) {
    activity.iniciarInfoParada(parada);
    repositorio.buscarTrajetosNoPonto(parada, new Listener.Trajetos() {
      @Override
      public void onReady(List<Trajeto> trajetos) {
        Log.d("AAA", trajetos.size() + "ass");
        activity.exibirInfoPonto(trajetos);
      }
    });
  }
}
