package com.dantas.bruno.sysbus.data;

import android.util.Log;

import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 07/06/17.
 */

public class RepositorioImpl implements Repositorio {


  private static RepositorioImpl INSTANCE;
  private FirebaseDatabase database;
  private DatabaseReference raiz;

  private RepositorioImpl() {
    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    database = FirebaseDatabase.getInstance();
    raiz = database.getReference();
    raiz.keepSynced(true);
  }

  public static Repositorio getInstance() {
    if (INSTANCE == null)
      INSTANCE = new RepositorioImpl();
    return INSTANCE;
  }

  @Override
  public void setPonto(Parada parada) {
    String uid = raiz.child("pontos").push().getKey();
    parada.setUid(uid);
    raiz.child("pontos").child(uid).setValue(parada);
  }

  @Override
  public void buscarTrajetosNoPonto(Parada parada, final Listener.Trajetos listener) {
    final List<Trajeto> trajetos = new ArrayList<>();
    raiz.child("paradas").child(parada.getUid()).child("trajetos").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        for (final DataSnapshot data : dataSnapshot.getChildren()) {
          raiz.child("trajetos").child(data.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              Trajeto trajeto = dataSnapshot.getValue(Trajeto.class);
              Log.d("TRAJETO", String.valueOf(dataSnapshot.getRef()));
              trajetos.add(trajeto);
              Log.d("AAA", "Log" + trajetos.size());
              listener.onReady(trajetos);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
          });
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  @Override
  public void buscarParadas(final Listener.Paradas listener) {
    final List<Parada> paradas = new ArrayList<>();
    raiz.child("paradas").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d("PONTOS", dataSnapshot.getChildrenCount() + "|||sads");
        for (DataSnapshot pontoSnapshot : dataSnapshot.getChildren()) {
          Parada parada = pontoSnapshot.getValue(Parada.class);
          Log.d("PONTOS", "Parada :" + parada.getUid() + parada.getLongitude() + parada.getLatitude() + parada.getDescricao() + parada.getNome());
          paradas.add(parada);
          listener.onReady(paradas);
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  @Override
  public void buscarOnibus() {

  }

  @Override
  public void buscarRotas() {
  }
}
