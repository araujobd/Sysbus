package com.dantas.bruno.sysbus.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dantas.bruno.sysbus.BaseActivity;
import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.adapter.AdapterParadas;
import com.dantas.bruno.sysbus.infoparada.InfoParadaActivity;
import com.dantas.bruno.sysbus.login.LoginActivity;
import com.dantas.bruno.sysbus.main.fragmentos.mapa.MapaFragment;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;
import com.dantas.bruno.sysbus.rotas.RotasActivity;

/**
 * Created by bruno on 12/06/17.
 */

public class PrincipalActivity extends BaseActivity
    implements Contrato.PrincipalView, NavigationView.OnNavigationItemSelectedListener {

  private static final int INFO_PARADA_REQUEST = 1;
  private static final int INFO_ROTAS_REQUEST = 2;

  private Contrato.PrincipalPresenter presenter;

  private FragmentManager manager;
  private Toolbar toolbar;
  private DrawerLayout drawer;
  private NavigationView navigation;
  private BottomSheetBehavior bottomSheet;

  private AdapterParadas adapter;
  private TextView tvRota, tvUserNome, tvUserEmail;
  private ListView list;

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
  protected void onRestart() {
    super.onRestart();
  }

  @Override
  protected void onStart() {
    if (drawer.isDrawerOpen(GravityCompat.START))
      drawer.closeDrawer(GravityCompat.START);
    super.onStart();

    presenter.verificarUsuario();
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START))
      drawer.closeDrawer(GravityCompat.START);
    else if (bottomSheet.getState() == BottomSheetBehavior.STATE_EXPANDED ||
                bottomSheet.getState() == BottomSheetBehavior.STATE_COLLAPSED)
      bottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);
    else
      sair();
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
    View header = navigation.getHeaderView(0);

    tvUserNome = (TextView) header.findViewById(R.id.tv_user_nome);
    tvUserEmail = (TextView) header.findViewById(R.id.tv_user_email);

    presenter.buscarUsuario();
  }

  private void configurarFragmento() {
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.add(R.id.container, new MapaFragment(), "MAPA");
    transaction.commitAllowingStateLoss();
  }

  private void configurarBottomSheet() {
    tvRota = (TextView) findViewById(R.id.tv_descricao_rota);
    list = (ListView) findViewById(R.id.list_view_rota);

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

  private void configurarViewBottom(Trajeto trajeto) {
    Log.d("PRINCIPAL", trajeto.toString());
    tvRota.setText(trajeto.getRota().getNome());

    adapter = new AdapterParadas(this, trajeto);
    list.setAdapter(adapter);

    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Parada parada = (Parada) adapterView.getItemAtPosition(i);
        Toast.makeText(PrincipalActivity.this, parada.getNome(), Toast.LENGTH_SHORT).show();
      }
    });

  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    switch (id) {
      case R.id.sair:
        sair();
        return true;
      case R.id.rotas:
        iniciarRotas();
        return true;
      default:
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
  }

  public void exibirRota(Trajeto trajeto) {
    configurarViewBottom(trajeto);
    if (trajeto.getCoordenadas() != null)
      presenter.desenharRota(trajeto.getCoordenadas(), trajeto.getOnibus());
    bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  @Override
  public void iniciarInfoParada(Parada parada) {
    Intent intent = new Intent(PrincipalActivity.this, InfoParadaActivity.class);
    intent.putExtra("parada", parada);
    startActivityForResult(intent, INFO_PARADA_REQUEST);
  }

  public void iniciarRotas() {
    startActivityForResult(new Intent(PrincipalActivity.this, RotasActivity.class), INFO_ROTAS_REQUEST);
  }
  @Override
  public void sair() {
    new AlertDialog.Builder(this)
        .setMessage("Deseja realmente sair?")
        .setCancelable(false)
        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            PrincipalActivity.this.finish();
          }
        })
        .setNegativeButton("Não", null)
        .show();
  }

  @Override
  public void finish() {
    presenter.finish();
    super.finish();
  }

  @Override
  public void sobre() {

  }

  public void setFragmento(Contrato.MapaView fragmento) {
    if (fragmento != null)
      presenter.setFragmento(fragmento);
  }

  @Override
  public void login() {
    startActivity(new Intent(PrincipalActivity.this, LoginActivity.class));
    finish();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == INFO_PARADA_REQUEST || requestCode == INFO_ROTAS_REQUEST) {
      if (resultCode == Activity.RESULT_OK) {
        Trajeto trajeto = (Trajeto) data.getSerializableExtra("trajeto");
        exibirRota(trajeto);
      }
    }
  }

  public void mostrarDadosUsuario(String nome, String email) {
    tvUserNome.setText(nome);
    tvUserEmail.setText(email);
  }
}
