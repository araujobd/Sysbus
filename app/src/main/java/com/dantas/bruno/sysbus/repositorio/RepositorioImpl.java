package com.dantas.bruno.sysbus.repositorio;

import android.util.Log;

import com.dantas.bruno.sysbus.Listener;
import com.dantas.bruno.sysbus.model.Localizacao;
import com.dantas.bruno.sysbus.model.Parada;
import com.dantas.bruno.sysbus.model.Trajeto;
import com.dantas.bruno.sysbus.model.User;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.ChildEventListener;
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
              trajetos.add(trajeto);
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
  public void salvarUsuario(String uid, User user) {
    raiz.child("users").child(uid).setValue(user);
  }

  @Override
  public void buscarParadas(final Listener.Paradas listener) {
    final List<Parada> paradas = new ArrayList<>();
    raiz.child("paradas").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot pontoSnapshot : dataSnapshot.getChildren()) {
          Parada parada = pontoSnapshot.getValue(Parada.class);
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
  public void buscarOnibus(final Listener.Localizacao listener) {
    ValueEventListener childEventListener = new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        Localizacao localizacao = dataSnapshot.getValue(Localizacao.class);
        if (localizacao.getOnibusatual().getStatus().contentEquals("true"))
          listener.onchange(new LatLng(localizacao.getLatitude(), localizacao.getLongitude()));
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    };
    raiz.child("localizacao").addValueEventListener(childEventListener);
  }

  @Override
  public void buscarTrajetos(final Listener.Trajetos listener) {
    final List<Trajeto> trajetos = new ArrayList<>();
    raiz.child("trajetos").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot data : dataSnapshot.getChildren()) {
          Trajeto trajeto = data.getValue(Trajeto.class);
          trajetos.add(trajeto);
        }
        listener.onReady(trajetos);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }
}
