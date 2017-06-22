package com.dantas.bruno.sysbus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;

/**
 * Created by bruno on 20/06/17.
 */

public class AdapterParadas extends ArrayAdapter<Parada> {

  private final Context contexto;
  private final Trajeto trajeto;

  public AdapterParadas(Context contexto, Trajeto trajeto) {
    super(contexto, R.layout.item_lista_rota, trajeto.getParadas());

    this.contexto = contexto;
    this.trajeto = trajeto;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View linha = inflater.inflate(R.layout.item_lista_rota, parent, false);

    TextView tvRota = (TextView) linha.findViewById(R.id.tv_rota);
    tvRota.setText(trajeto.getParadas().get(position).getNome());

    return linha;
  }

  @Override
  public int getCount() {
    if (trajeto != null)
      if (trajeto.getParadas() != null)
        return trajeto.getParadas().size();
    return 0;
  }
}
