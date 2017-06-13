package com.dantas.bruno.sysbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dantas.bruno.sysbus.data.Repositorio;
import com.dantas.bruno.sysbus.data.RepositorioImpl;
import com.dantas.bruno.sysbus.model.Coordenada;
import com.dantas.bruno.sysbus.model.Ponto;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainsActiity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, Viewss {

  private FragmentManager manager;
  private BottomSheetBehavior sheet;
  private TextView view;
  private Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    transaction.add(R.id.container, new MapsFragment(),"MAPSACTIVITY");

    transaction.commitAllowingStateLoss();

    View bottomSheet = findViewById(R.id.design_bottom_sheet);
    sheet = BottomSheetBehavior.from(bottomSheet);
    view = (TextView) findViewById(R.id.ss);
    button = (Button) findViewById(R.id.button);

    configurarBottom();
  }

  @Override
  protected void onResume() {
    super.onResume();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    if (auth.getCurrentUser() != null)
      Toast.makeText(this, auth.getCurrentUser().getDisplayName(), Toast.LENGTH_LONG).show();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      RepositorioImpl.getInstance().setPonto(new Ponto());
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
   switch (item.getItemId()) {
     case R.id.mapa_exemplo:
       showFragment(new MapsFragment(), "Mapa EXEMPLO");
       break;
     case R.id.mapa_provider:
       showFragment(new ProviderFragment(), "PROVIDER");
       break;
     case R.id.gps_provider:
       showFragment(new GPSFragment(), "GPS");
       break;
     case R.id.sliding_view:
       startActivity(new Intent(MainsActiity.this, MapsExemploActivity.class));
       break;
   }
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }




  public void configurarBottom() {
    sheet.setPeekHeight(150);
    sheet.setState(BottomSheetBehavior.STATE_HIDDEN);
    sheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
      @Override
      public void onStateChanged(@NonNull View bottomSheet, int newState) {
        if (newState == BottomSheetBehavior.STATE_COLLAPSED)
          sheet.setPeekHeight(150);
        else if (newState == BottomSheetBehavior.STATE_SETTLING)
          sheet.setPeekHeight(0);
      }

      @Override
      public void onSlide(@NonNull View bottomSheet, float slideOffset) {

      }
    });

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        sheet.setState(BottomSheetBehavior.STATE_SETTLING);
      }
    });
  }

  private void showFragment(Fragment fragment, String tag) {
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.container, fragment, tag);
    transaction.commitAllowingStateLoss();

  }

  @Override
  public void showView(Ponto ponto) {
    view.setText(ponto.getDescricao());
    sheet.setState(BottomSheetBehavior.STATE_EXPANDED);
  }
}
