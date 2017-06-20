package com.dantas.bruno.sysbus;

import android.view.View;

import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 07/06/17.
 */

public interface Listener {

  interface Paradas {
    void onReady(List<Parada> paradas);
  }

  interface Trajetos {
    void onReady(List<Trajeto> trajetos);
  }

}
