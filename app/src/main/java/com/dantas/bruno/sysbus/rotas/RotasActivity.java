package com.dantas.bruno.sysbus.rotas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dantas.bruno.sysbus.BaseActivity;
import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.adapter.AdapterRotas;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

public class RotasActivity extends BaseActivity implements Contrato.View {

  private Contrato.Presenter presenter;

  private AdapterRotas adapter;
  private ListView list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rotas);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    presenter = new RotasPresenter(this);

    configurarTela();
  }

  @Override
  public void onBackPressed() {
    cancelar();
  }

  private void configurarTela() {
    list = (ListView) findViewById(R.id.listView);

    presenter.buscarTrajetos();
  }

  @Override
  public void configurarLista(List<Trajeto> trajetos) {
    adapter = new AdapterRotas(this, trajetos);

    list.setAdapter(adapter);
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Trajeto trajeto = (Trajeto) adapterView.getItemAtPosition(i);
        presenter.escolher(trajeto);
      }
    });
  }

  @Override
  public void retornarResultado(Trajeto trajeto) {
    Intent result = new Intent();
    result.putExtra("trajeto", trajeto);
    setResult(Activity.RESULT_OK, result);
    finish();
  }

  public void cancelar() {
    setResult(Activity.RESULT_CANCELED);
    finish();
  }

  @Override
  public void finish() {
    presenter.finish();
    super.finish();
  }
}
