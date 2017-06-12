package com.dantas.bruno.sysbus;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.dantas.bruno.sysbus.data.RepositorioImpl;
import com.google.firebase.auth.FirebaseAuth;


public class MainsActiity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private FragmentManager manager;

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
    switch(item.getItemId()) {
      case R.id.action_settings:
        RepositorioImpl.getInstance().setPontos();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    FragmentTransaction transaction;
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

  private void showFragment(Fragment fragment, String tag) {
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.container, fragment, tag);
    transaction.commitAllowingStateLoss();

  }
}
