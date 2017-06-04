package com.dantas.bruno.sysbus.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by bruno on 03/06/17.
 */

public class LoginPresenter implements LoginContrato.Presenter {

  private final LoginContrato.View view;
  private final FirebaseAuth auth;

  public LoginPresenter(LoginContrato.View view) {
    this.view = view;
    auth = FirebaseAuth.getInstance();
  }

  @Override
  public void loginComFirebase(String email, String senha) {
    Log.d("FIREBASE", "LOGIN + " + auth.getCurrentUser());
    auth.signInWithEmailAndPassword(email, senha)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful())
              view.iniciarPrincipal();
            else
              view.mostrarErroLogin();
          }
        });

    Log.d("FIREBASE", "LOGIN + " + auth.getCurrentUser());
  }
}
