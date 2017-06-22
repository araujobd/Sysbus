package com.dantas.bruno.sysbus.main;

import com.dantas.bruno.sysbus.model.Coordenada;
import com.dantas.bruno.sysbus.model.Onibus;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by bruno on 12/06/17.
 */

public interface Contrato {

  interface PrincipalView {
    void sair();
    void sobre();
    void login();
    void exibirRota(Trajeto trajeto);
    void iniciarInfoParada(Parada parada);
    void setFragmento(MapaView view);
    void mostrarDadosUsuario(String nome, String email);
  }

  interface PrincipalPresenter {
    void verificarUsuario();
    void setFragmento(MapaView fragmento);
    void desenharRota(List<Coordenada> coordenadas, Onibus onibus);
    void finish();
    void buscarUsuario();
  }

  interface MapaView {
    void exibirPontos(List<Parada> paradas);
    void exibirOnibus(LatLng ponto);
    void desenharRota(List<Coordenada> coordenadas, Onibus onibus);
  }

  interface MapaPresenter {
    void exibirInfoPonto(Parada parada);
    void onMapReady();
    void buscarLocalizacaoOnibus();
  }

}
