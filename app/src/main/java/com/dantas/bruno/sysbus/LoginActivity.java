package com.dantas.bruno.sysbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dantas.bruno.sysbus.domain.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

  private EditText ed_email, ed_senha;
  private Button bt_login, bt_cadastrar;

  private DatabaseReference mDatabase;
  private FirebaseAuth mAuth;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    inicializarFirebase();
    configurarTela();
  }

  private void configurarTela() {
    ed_email = (EditText) findViewById(R.id.ed_email);
    ed_senha = (EditText) findViewById(R.id.ed_senha);

    bt_login = (Button) findViewById(R.id.bt_login);
    bt_cadastrar = (Button) findViewById(R.id.bt_cadastrar);

    bt_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        login();
      }
    });

    bt_cadastrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        cadastrar();
      }
    });
  }

  private void inicializarFirebase() {
    mAuth = FirebaseAuth.getInstance();
    mDatabase = FirebaseDatabase.getInstance().getReference();
  }

  @Override
  protected void onStart() {
    super.onStart();

    if (mAuth.getCurrentUser() != null) {
      onLoginSucesso(mAuth.getCurrentUser());
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  private boolean validarFormulario() {
    boolean result = true;
    if (TextUtils.isEmpty(ed_email.getText().toString())) {
      ed_email.setError(getString(R.string.msg_campo_obrigatorio));
      result = false;
    } else {
      ed_email.setError(null);
    }

     if (TextUtils.isEmpty(ed_senha.getText().toString())) {
      ed_senha.setError(getString(R.string.msg_campo_obrigatorio));
      result = false;
    } else {
      ed_senha.setError(null);
    }

    return result;
  }

  private void login() {
    if (!validarFormulario())
      return;

    String email = ed_email.getText().toString();
    String senha = ed_senha.getText().toString();

    mAuth.signInWithEmailAndPassword(email, senha)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              onLoginSucesso(task.getResult().getUser());
            } else {
              Toast.makeText(LoginActivity.this, "Error no Login!", Toast.LENGTH_LONG).show();
            }
          }
        });
  }

  private void cadastrar() {

    if (!validarFormulario())
      return;

    String email = ed_email.getText().toString();
    String senha = ed_senha.getText().toString();

    mAuth.createUserWithEmailAndPassword(email, senha)
        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>(){

      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
          onLoginSucesso(task.getResult().getUser());
        } else {
          Toast.makeText(LoginActivity.this, "O Cadastro Falhou", Toast.LENGTH_SHORT).show();
        }
      }
    });

  }

  private void onLoginSucesso(FirebaseUser user) {
    String username = usernameFromEmail(user.getEmail());
    writeNewUser(user.getUid(), username, user.getEmail());

    startActivity(new Intent(LoginActivity.this, MainsActiity.class));
    finish();
  }

  private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }
}
