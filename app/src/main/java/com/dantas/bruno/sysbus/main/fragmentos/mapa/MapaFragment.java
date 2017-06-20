package com.dantas.bruno.sysbus.main.fragmentos.mapa;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dantas.bruno.sysbus.main.Contrato;
import com.dantas.bruno.sysbus.model.Onibus;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Rota;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends SupportMapFragment
    implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, Contrato.MapaView {

  private GoogleMap map;
  private Contrato.MapaPresenter presenter;


  @Override
  public void onCreate( Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    presenter = new MapaPresenter(this, (Contrato.PrincipalView) getActivity());

    getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    map = googleMap;
    map.setOnMarkerClickListener(this);

    configurarControles();
    presenter.onMapReady();
  }

  @Override
  public boolean onMarkerClick(Marker marker) {
    Parada parada = (Parada) marker.getTag();

    if (parada != null)
      presenter.exibirInfoPonto(parada);

    return false;
  }

  @Override
  public void exibirPontos(List<Parada> paradas) {
    for (Parada p : paradas) {
      LatLng ponto = new LatLng(p.getLatitude(), p.getLongitude());

      map.addMarker(new MarkerOptions()
          .position(ponto)
          .title(p.getDescricao())
      ).setTag(p);
    }
  }

  @Override
  public void exibirOnibus(Onibus onibus) {

  }

  @Override
  public void exibirRota(Rota rota) {

  }

  private void configurarControles() {
    UiSettings ui = map.getUiSettings();

    ui.setCompassEnabled(true);
    ui.setMyLocationButtonEnabled(true);
    ui.setMapToolbarEnabled(false);
  }

}
