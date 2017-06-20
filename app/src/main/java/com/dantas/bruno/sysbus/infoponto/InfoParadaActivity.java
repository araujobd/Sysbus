package com.dantas.bruno.sysbus.infoponto;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dantas.bruno.sysbus.BaseActivity;
import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.adapter.AdapterTrajeto;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

public class InfoParadaActivity extends BaseActivity implements Contrato.View {

  private Contrato.Presenter presenter;

  private AdapterTrajeto adapter;
  private ListView list;
  private TextView tvInfo;

  private Parada parada;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_info_parada);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    presenter = new InfoParadaPresenter(this);

    configurarTela();
    recuperarDados();
  }

  @Override
  public void onBackPressed() {
    cancelar();
  }


  private void configurarTela() {
    list = (ListView) findViewById(R.id.list_view_trajetos);
    tvInfo = (TextView) findViewById(R.id.tv_info_parada);
  }

  private void recuperarDados() {
    Intent intent = getIntent();
    this.parada = (Parada) intent.getSerializableExtra("parada");

    getSupportActionBar().setTitle("Parada");
    tvInfo.setText(parada.getDescricao());

    presenter.buscarTrajetos(parada);
  }

  @Override
  public void configurarLista(List<Trajeto> trajetos) {
    adapter = new AdapterTrajeto(this, trajetos);

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
    Intent result  = new Intent();
    result.putExtra("trajeto", trajeto);
    setResult(Activity.RESULT_OK, result);
    finish();
  }

  public void cancelar() {
    Intent result = new Intent();
    setResult(Activity.RESULT_CANCELED, result);
    finish();
  }
}
