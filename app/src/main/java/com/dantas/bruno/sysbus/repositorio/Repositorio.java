package com.dantas.bruno.sysbus.repositorio;

import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.User;


/**
 * Created by bruno on 07/06/17.
 */

public interface Repositorio {

  void salvarUsuario(String uid, User user);
  void buscarParadas(Listener.Paradas listener);
  void buscarTrajetosNoPonto(Parada parada, Listener.Trajetos listener);
  void buscarOnibus(Listener.Localizacao listener);
  void buscarTrajetos(Listener.Trajetos listener);
}
