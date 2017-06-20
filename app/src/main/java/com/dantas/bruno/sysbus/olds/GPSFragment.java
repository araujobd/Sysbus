package com.dantas.bruno.sysbus.olds;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
public class GPSFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener {

  private GoogleMap mMap;
  private LocationManager locationManager;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ///// SLide 66
    getMapAsync(this);
  }

  @Override
  public void onResume() {
    super.onResume();

    locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
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
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
  }

  @Override
  public void onPause() {
    super.onPause();
    locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    locationManager.removeUpdates(this);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
    Toast.makeText(getContext(), "Coordenadas: "+ latLng.toString(), Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onLocationChanged(Location location) {
    Toast.makeText(getContext(), "Posição Alterada", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onStatusChanged(String s, int i, Bundle bundle) {
    Toast.makeText(getContext(), "Status Alterada", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onProviderEnabled(String s) {
    Toast.makeText(getContext(), "GPS Habilitado", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onProviderDisabled(String s) {
    Toast.makeText(getContext(), "GPS Desabilitado", Toast.LENGTH_SHORT).show();
  }
}
