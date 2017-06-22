package com.dantas.bruno.sysbus.main.fragmentos.mapa;


import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.repositorio.Repositorio;
import com.dantas.bruno.sysbus.repositorio.RepositorioImpl;
import com.dantas.bruno.sysbus.main.Contrato;
import com.dantas.bruno.sysbus.model.Parada;
import com.google.android.gms.maps.model.LatLng;

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
    setActivity();
  }

  public void setActivity() {
    activity.setFragmento(view);
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
  public void buscarLocalizacaoOnibus() {
    repositorio.buscarOnibus(new Listener.Localizacao() {
      @Override
      public void onchange(LatLng localizacao) {
        view.exibirOnibus(localizacao);
      }
    });
  }

  @Override
  public void exibirInfoPonto(Parada parada) {
    activity.iniciarInfoParada(parada);
  }


}
