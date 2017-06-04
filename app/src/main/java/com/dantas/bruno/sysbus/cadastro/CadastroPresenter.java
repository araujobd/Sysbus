package com.dantas.bruno.sysbus.cadastro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dantas.bruno.sysbus.domain.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by bruno on 03/06/17.
 */

public class CadastroPresenter implements CadastroContrato.Presenter{

  private CadastroContrato.View view;
  private FirebaseAuth auth;
  private DatabaseReference database;

  public CadastroPresenter(CadastroContrato.View view) {
    this.view = view;
    auth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance().getReference();
  }

  @Override
  public void cadastrar(final String nome, final String email, final String senha) {
    auth.createUserWithEmailAndPassword(email, senha)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            Log.d("FIREBASE", task.toString());
            if (task.isSuccessful()) {
              salvarUsuario(task.getResult().getUser().getUid(), nome, email, senha);
              view.iniciarPrincipal();
            }
            else
              view.mostrarErroCadastro();
          }
        });
  }

  private void salvarUsuario(String userId, String nome, String email, String senha) {
    User user = new User(nome, email);

    database.child("users").child(userId).setValue(user);
  }

}
