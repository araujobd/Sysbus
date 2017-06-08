package com.dantas.bruno.sysbus;

import com.dantas.bruno.sysbus.model.Ponto;

import java.util.List;

/**
 * Created by bruno on 07/06/17.
 */

public interface Listener {
  void onready(List<Ponto> pontos);
}
