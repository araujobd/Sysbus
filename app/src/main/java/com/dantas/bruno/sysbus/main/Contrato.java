package com.dantas.bruno.sysbus.main;

import android.support.v4.app.Fragment;

import com.dantas.bruno.sysbus.model.Onibus;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Rota;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 12/06/17.
 */

public interface Contrato {

  interface PrincipalView {
    void trocarFragmento(Fragment fragmento, final String TAG);

    void exibirInfoPonto(List<Trajeto> trajetos);
    void sair();
//    void sobre();
    void favoritos();

    void login();

    void iniciarInfoParada(Parada parada);
  }

  interface PrincipalPresenter {
    void verificarUsuario();
  }

  interface BottomView {

  }

  interface BottomPresenter {

  }

  interface MapaView {

    void exibirPontos(List<Parada> paradas);

    void exibirOnibus(Onibus onibus);
    void exibirRota(Rota rota);
  }

  interface MapaPresenter {

    void exibirInfoPonto(Parada parada);

    void onMapReady();
  }
}
