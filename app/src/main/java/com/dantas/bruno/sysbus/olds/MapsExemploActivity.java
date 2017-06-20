package com.dantas.bruno.sysbus.olds;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dantas.bruno.sysbus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MapsExemploActivity extends AppCompatActivity implements SlidingUpPanelLayout.PanelSlideListener {

  private SlidingUpPanelLayout slidingUpPanelLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sliding_layout);

    slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding);
    slidingUpPanelLayout.addPanelSlideListener(this);
  }


  @Override
  public void onPanelSlide(View panel, float slideOffset) {
    Toast.makeText(this, "Sliding " + slideOffset, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

  }
}
