package com.dantas.bruno.sysbus;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProviderFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

  private GoogleMap mMap;
  private LocationManager locationManager;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ///// SLide 66
    getMapAsync(this);
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


    locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

    Criteria criteria = new Criteria();

    String provider = locationManager.getBestProvider(criteria, true);

    Toast.makeText(getContext(), "Provider: " + provider, Toast.LENGTH_SHORT).show();

    if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      return;
    }
    mMap.setMyLocationEnabled(true);

    LatLng ponto = new LatLng(-6.467900, -37.084776);
    MarkerOptions ufrn = new MarkerOptions().position(ponto).title("UFRN");
    mMap.addMarker(ufrn);
    float zoom = 16;
    mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( ponto, zoom ));


  }

  @Override
  public void onMapClick(LatLng latLng) {
    Log.d("123", "ASJDJASD + " + latLng.toString());
    Toast.makeText(getActivity(), "Coordenadas: "+ latLng.toString(), Toast.LENGTH_SHORT).show();
  }
}
