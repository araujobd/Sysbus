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

public class AdapterRotas extends ArrayAdapter<Trajeto> {

  private final Context contexto;
  private final List<Trajeto> trajetos;

  public AdapterRotas(Context contexto, List<Trajeto> trajetos) {
    super(contexto, R.layout.item_lista_rotas, trajetos);

    this.contexto = contexto;
    this.trajetos = trajetos;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View linha = inflater.inflate(R.layout.item_lista_rotas, parent, false);

    TextView tvNome = (TextView) linha.findViewById(R.id.tv_rota_nome);
    TextView tvDescricao = (TextView) linha.findViewById(R.id.tv_rota_descricao);

    tvNome.setText(trajetos.get(position).getRota().getNome());
    tvDescricao.setText(trajetos.get(position).getRota().getDescricao());

    return linha;
  }
}
