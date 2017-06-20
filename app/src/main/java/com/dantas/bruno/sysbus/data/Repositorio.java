package com.dantas.bruno.sysbus.data;

import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;


/**
 * Created by bruno on 07/06/17.
 */

public interface Repositorio {


  void buscarParadas(Listener.Paradas listener);
  void setPonto(Parada parada);

  void buscarTrajetosNoPonto(Parada parada, Listener.Trajetos listener);
  void buscarOnibus();
  void buscarRotas();

}
