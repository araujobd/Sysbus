package com.dantas.bruno.sysbus.main;

import android.app.Activity;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dantas.bruno.sysbus.BaseActivity;
import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.infoponto.InfoParadaActivity;
import com.dantas.bruno.sysbus.login.LoginActivity;
import com.dantas.bruno.sysbus.main.fragmentos.mapa.MapaFragment;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 12/06/17.
 */

public class PrincipalActivity extends BaseActivity
    implements Contrato.PrincipalView, NavigationView.OnNavigationItemSelectedListener {

  private static final int INFO_PARADA_REQUEST = 1;

  private Contrato.PrincipalPresenter presenter;

  private FragmentManager manager;
  private Toolbar toolbar;
  private DrawerLayout drawer;
  private NavigationView navigation;
  private BottomSheetBehavior bottomSheet;

  private TextView tvInfoParada;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_principal);

    presenter = new PrincipalPresenter(this);

    configurarTela();
    configurarNavigation();
    configurarFragmento();
    configurarBottomSheet();
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


    View bottomSheetView = findViewById(R.id.bottom_sheet);
    bottomSheet = BottomSheetBehavior.from(bottomSheetView);

    setSupportActionBar(toolbar);
  }

  private void configurarNavigation() {
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
        toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    navigation = (NavigationView) findViewById(R.id.nav_view);
    navigation.setNavigationItemSelectedListener(this);
  }

  private void configurarFragmento() {
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.add(R.id.container, new MapaFragment(), "MAPA");
    transaction.commitAllowingStateLoss();
  }

  private void configurarBottomSheet() {
    tvInfoParada = (TextView) findViewById(R.id.tv_info_parada);

    bottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);

    bottomSheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
      @Override
      public void onStateChanged(@NonNull View bottomSheet, int newState) {
      }

      @Override
      public void onSlide(@NonNull View bottomSheet, float slideOffset) {

      }
    });
  }

  private void configurarViewBottom() {
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
  public void exibirInfoPonto(List<Trajeto> trajetos) {
//    Log.d("ADAPTER", trajetos.size() + "343434");
//    if (!listaParadas.isInLayout())
//      configurarViewBottom();
//    adapter = new TrajetosAdapter(this, trajetos);
//    listaParadas.setAdapter(adapter);
//    bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
//

  }

  @Override
  public void iniciarInfoParada(Parada parada) {
    Intent intent = new Intent(PrincipalActivity.this, InfoParadaActivity.class);
    intent.putExtra("parada", parada);
    startActivityForResult(intent, INFO_PARADA_REQUEST);
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

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == INFO_PARADA_REQUEST) {
      if (resultCode == Activity.RESULT_OK) {
        Trajeto trajeto = (Trajeto) data.getSerializableExtra("trajeto");
        Log.d("PRINCIPAL", "trajeto ok: " +trajeto.getRota().getDescricao());
      }

      if (resultCode == Activity.RESULT_CANCELED) {
        Log.d("PRINCIPAL", "trajeto cancelado");
      }
    }
  }
}
