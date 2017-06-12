package com.dantas.bruno.sysbus.data;

import android.util.Log;

import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.model.Ponto;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 07/06/17.
 */

public class RepositorioImpl implements Repositorio {

  private static RepositorioImpl INSTANCE;
  private FirebaseDatabase database;
  private DatabaseReference reference;

  private RepositorioImpl() {
    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    database = FirebaseDatabase.getInstance();
    reference = database.getReference();
  }

  public static Repositorio getInstance() {
    if (INSTANCE == null)
      INSTANCE = new RepositorioImpl();
    return INSTANCE;
  }

  @Override
  public void setPonto(Ponto ponto) {
    String uid = reference.child("pontos").push().getKey();
    ponto.setUid(uid);
    reference.child("pontos").child(uid).setValue(ponto);
  }

  @Override
  public void getPontos(final Listener listener) {
    final List<Ponto> pontos = new ArrayList<>();
    reference.child("paradas").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d("PONTOS", dataSnapshot.getChildrenCount() + "|||sads");
        for (DataSnapshot pontoSnapshot: dataSnapshot.getChildren()) {
          Ponto ponto = pontoSnapshot.getValue(Ponto.class);
          Log.d("PONTOS", ponto.getDescricao() + ponto.getLatitude() + "  " + ponto.getLongitude());
          pontos.add(ponto);
          listener.onready(pontos);
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }
}
