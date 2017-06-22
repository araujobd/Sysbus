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
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;

import java.util.List;

/**
 * Created by bruno on 19/06/17.
 */

public class AdapterTrajeto extends ArrayAdapter<Trajeto> {

  private final Context contexto;
  private final List<Trajeto> trajetos;
  private String paradaUID;

  public AdapterTrajeto(Context contexto, List<Trajeto> trajetos, String paradaUID) {
    super(contexto, R.layout.item_lista_paradas, trajetos);

    this.contexto = contexto;
    this.trajetos = trajetos;
    this.paradaUID = paradaUID;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View linha = inflater.inflate(R.layout.item_lista_paradas, parent, false);

    TextView tvOnibus = (TextView) linha.findViewById(R.id.tv_onibus);
    TextView tvDescricao = (TextView) linha.findViewById(R.id.tv_descricao);
    TextView tvTempo = (TextView) linha.findViewById(R.id.tv_tempo);

    tvOnibus.setText(trajetos.get(position).getOnibus().getIdentificacao());
    tvDescricao.setText(trajetos.get(position).getRota().getNome());

    for (Parada parada : trajetos.get(position).getParadas())
      if (parada.getUid().contentEquals(paradaUID))
        tvTempo.setText(parada.getTempoPadrao());

    return linha;
  }

}
