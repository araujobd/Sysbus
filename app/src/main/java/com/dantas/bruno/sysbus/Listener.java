package com.dantas.bruno.sysbus;

import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;
import com.dantas.bruno.sysbus.splash.SplashContrato;
import com.google.android.gms.maps.model.LatLng;

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

  interface Localizacao {
    void onchange(LatLng localizacao);
  }

}
