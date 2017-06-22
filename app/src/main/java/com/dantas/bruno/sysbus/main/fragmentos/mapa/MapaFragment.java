package com.dantas.bruno.sysbus.main.fragmentos.mapa;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.main.Contrato;
import com.dantas.bruno.sysbus.model.Coordenada;
import com.dantas.bruno.sysbus.model.Onibus;
import com.dantas.bruno.sysbus.model.Parada;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends SupportMapFragment
    implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, Contrato.MapaView {

  private GoogleMap map;
  private Contrato.MapaPresenter presenter;
  private Polyline polyline;
  private Marker onibus;

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
    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.4661865,-37.092784), (float) 14.5));

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
          .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_24dp))
      ).setTag(p);
    }
  }

  @Override
  public void exibirOnibus(LatLng ponto) {
    if (onibus == null)
      onibus = map.addMarker(new MarkerOptions()
        .position(ponto)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bus)));
    else
      onibus.setPosition(ponto);
  }

  @Override
  public void desenharRota(List<Coordenada> coordenadas, Onibus onibus) {
     ArrayList<LatLng> waypoints = new ArrayList();
    for (Coordenada c : coordenadas) {
      waypoints.add(new LatLng(c.getLat(), c.getLng()));
    }

    PolylineOptions polylines = new PolylineOptions();
    polylines.addAll(waypoints).color(Color.BLUE).width(10);

    if (polyline != null)
      polyline.remove();
    polyline = map.addPolyline(polylines);

    map.moveCamera(CameraUpdateFactory.newLatLng(waypoints.get(0)));

    presenter.buscarLocalizacaoOnibus();
  }

  private void configurarControles() {
    UiSettings ui = map.getUiSettings();

    ui.setCompassEnabled(true);
    ui.setMyLocationButtonEnabled(true);
    ui.setMapToolbarEnabled(false);
    ui.setZoomControlsEnabled(true);
  }
}