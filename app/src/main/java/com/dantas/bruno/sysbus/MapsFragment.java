package com.dantas.bruno.sysbus;

import android.os.Bundle;
import android.util.Log;

import com.dantas.bruno.sysbus.data.Repositorio;
import com.dantas.bruno.sysbus.data.RepositorioImpl;
import com.dantas.bruno.sysbus.model.Coordenada;
import com.dantas.bruno.sysbus.model.Ponto;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

  private GoogleMap mMap;
  private Repositorio repositorio;
  private Viewss viewss;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    repositorio = RepositorioImpl.getInstance();
    getMapAsync(this);
    viewss = (Viewss) getActivity();
  }


  /**
   * Manipulates the map once available.
   * This callback is triggered when the map is ready to be used.
   * This is where we can add markers or lines, add listeners or move the camera. In this case,
   * we just add a marker near Sydney, Australia.
   * If Google Play services is not installed on the device, the user will be prompted to install
   * it inside the SupportMapFragment. This method will only be triggered once the user has
   * installed Google Play services and returned to the app.
   */
  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    List<Coordenada> coordenadas = Coordenada.getPontos();

    repositorio.getPontos(new Listener() {
      @Override
      public void onready(List<Ponto> pontos) {
        for (Ponto p : pontos) {
          LatLng ponto = new LatLng(p.getLatitude(), p.getLongitude());
          mMap.addMarker(new MarkerOptions()
                  .position(ponto)
                  .title(p.getDescricao())
                  .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bus))
          ).setTag(p);


        }
      }
    });


//    for (Coordenada x : coordenadas) {
//      LatLng ponto = new LatLng(x.getLatitude(), x.getLongitude());
//      mMap.addMarker(new MarkerOptions()
//          .position(ponto)
//          .flat(true)
//          .title(x.getTitulo())
//          .zIndex(-1.0f)
//          .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bus))
//      );
//
//    }
    LatLng ponto = new LatLng(coordenadas.get(0).getLatitude(), coordenadas.get(0).getLongitude());
    float zoom = 16;
    mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( ponto, zoom ));

    // Add a marker in Sydney and move the camera
//    LatLng caico = new LatLng(-6.462429,-37.0950466);
//    mMap.addMarker(new MarkerOptions()
//        .position(caico)
//        .flat(false)
//        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bus))
//    );
//    GroundOverlayOptions newarkMap = new GroundOverlayOptions()
//        .image(BitmapDescriptorFactory.fromResource(R.drawable.icon_bus))
//        .anchor(0,1)
//        .bearing(30)
//        .position(caico, 10f);
//    mMap.addGroundOverlay(newarkMap);
//    mMap.moveCamera(CameraUpdateFactory.newLatLng(caico));

    mMap.setOnMarkerClickListener(this);
  }

  @Override
  public boolean onMarkerClick(Marker marker) {
    Log.d("MARKER", "SSS");
    Ponto ponto = (Ponto) marker.getTag();
    if (ponto != null) {
      viewss.showView(ponto);
      Log.d("MARKER", "des " + ponto.getDescricao() + " | lat " + ponto.getLatitude() + " | long " + ponto.getLongitude());
    }
    return false;
  }
}
