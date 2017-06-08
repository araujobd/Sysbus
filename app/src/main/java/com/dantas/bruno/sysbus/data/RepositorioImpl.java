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

  public void setPontos() {
    Ponto ponto = new Ponto("PWQ", -37.377, -37.2342);
    String uid = reference.child("pontos").push().getKey();
    reference.child("pontos").child(uid).setValue(ponto);
  }

  @Override
  public void getPontos() {
    final List<Ponto> pontos = new ArrayList<>();
    reference.child("pontos").addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d("AAA", s + "?????????????" + dataSnapshot.getValue().toString());
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        Log.d("AAA", s + "?????????????" + dataSnapshot.getValue().toString());
      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {

        Log.d("AAA", "?????????????" + dataSnapshot.getValue().toString());
      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        Log.d("AAA", s + "?????????????" + dataSnapshot.getValue().toString());

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

        Log.d("AAA", "!!!!!!!!!" + databaseError.getDetails());
      }
    });

  }
}
