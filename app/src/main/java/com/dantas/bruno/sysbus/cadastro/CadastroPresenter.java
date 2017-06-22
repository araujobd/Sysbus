package com.dantas.bruno.sysbus.cadastro;

import android.support.annotation.NonNull;

import com.dantas.bruno.sysbus.repositorio.Repositorio;
import com.dantas.bruno.sysbus.repositorio.RepositorioImpl;
import com.dantas.bruno.sysbus.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by bruno on 03/06/17.
 */

public class CadastroPresenter implements CadastroContrato.Presenter{

  private CadastroContrato.View view;
  private FirebaseAuth auth;
  private Repositorio repositorio;

  public CadastroPresenter(CadastroContrato.View view) {
    this.view = view;
    auth = FirebaseAuth.getInstance();
    repositorio = RepositorioImpl.getInstance();
  }

  @Override
  public void cadastrar(final String nome, final String email, final String senha) {
    auth.createUserWithEmailAndPassword(email, senha)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              salvarUsuario(task.getResult().getUser(), nome, email);
              view.iniciarPrincipal();
            }
            else
              view.mostrarErroCadastro();
          }
        });
  }

  private void salvarUsuario(FirebaseUser firebaseUser, String nome, String email) {
    UserProfileChangeRequest atualizarPerfil = new UserProfileChangeRequest.Builder()
        .setDisplayName(nome)
        .build();
    firebaseUser.updateProfile(atualizarPerfil);

    User user = new User(nome, email);
    repositorio.salvarUsuario(firebaseUser.getUid(), user);
  }

  @Override
  public void finish() {
    auth = null;
    repositorio = null;
    view = null;
  }

}
