package com.dantas.bruno.sysbus.data;

import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.model.Ponto;


/**
 * Created by bruno on 07/06/17.
 */

public interface Repositorio {


  void getPontos(Listener listener);
  void setPonto(Ponto ponto);

}
