package com.dantas.bruno.sysbus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 19/06/17.
 */

public class AdapterTrajeto extends ArrayAdapter<Trajeto> {

  private final Context contexto;
  private final List<Trajeto> trajetos;

  public AdapterTrajeto(Context contexto, List<Trajeto> trajetos) {
    super(contexto, R.layout.item_lista_paradas, trajetos);

    this.contexto = contexto;
    this.trajetos = trajetos;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View linha = inflater.inflate(R.layout.item_lista_paradas, parent, false);

    Log.d("PRINCIPAL", trajetos.get(0) + "ssss");
    TextView tvOnibus = (TextView) linha.findViewById(R.id.tv_onibus);
    TextView tvDescricao = (TextView) linha.findViewById(R.id.tv_descricao);
    TextView tvTempo = (TextView) linha.findViewById(R.id.tv_tempo);

    tvOnibus.setText("023");
    tvDescricao.setText(trajetos.get(position).getRota().getNome());
    tvTempo.setText("40 min");

    return linha;
  }
}
