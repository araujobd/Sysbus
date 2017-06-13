package com.dantas.bruno.sysbus.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dantas.bruno.sysbus.BaseActivity;
import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.login.LoginActivity;

/**
 * Created by bruno on 12/06/17.
 */

public class PrincipalActivity extends BaseActivity
    implements Contrato.View, NavigationView.OnNavigationItemSelectedListener {

  private Contrato.Presenter presenter;

  private FragmentManager manager;
  private Toolbar toolbar;
  private DrawerLayout drawer;
  private NavigationView navigation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    presenter = new PrincipalPresenter(this);

    configurarTela();
    configurarNavigation();
    configurarFragmento();
  }

  @Override
  protected void onStart() {
    super.onStart();
    presenter.verificarUsuario();
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START))
      drawer.closeDrawer(GravityCompat.START);
    else
      super.onBackPressed();
  }

  private void configurarTela() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    manager = getSupportFragmentManager();

    setSupportActionBar(toolbar);
  }

  private void configurarNavigation() {
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
        toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);

    navigation = (NavigationView) findViewById(R.id.nav_view);
    navigation.setNavigationItemSelectedListener(this);
  }

  private void configurarFragmento() {
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.add(R.id.container, new Fragment(), "");
    transaction.commitAllowingStateLoss();
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    switch (id) {
      default:
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
  }

  @Override
  public void trocarFragmento(Fragment fragmento, String TAG) {
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.container, fragmento, TAG);
    transaction.commitAllowingStateLoss();
  }

  @Override
  public void sair() {
    finish();
  }

  @Override
  public void favoritos() {

  }

  @Override
  public void login() {
    startActivity(new Intent(PrincipalActivity.this, LoginActivity.class));
    finish();
  }

}
