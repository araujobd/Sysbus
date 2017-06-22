package com.dantas.bruno.sysbus.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dantas.bruno.sysbus.BaseActivity;
import com.dantas.bruno.sysbus.cadastro.CadastroActivity;
import com.dantas.bruno.sysbus.R;
import com.dantas.bruno.sysbus.main.PrincipalActivity;

public class LoginActivity extends BaseActivity implements LoginContrato.View {

  private LoginContrato.Presenter presenter;

  private ConstraintLayout layout;
  private TextView tvMsgError;
  private EditText ed_email, ed_senha;
  private Button bt_login, bt_cadastrar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    configurarTela();
    configurarBotoes();
  }

  private void configurarTela() {
    layout = (ConstraintLayout) findViewById(R.id.layout);

    tvMsgError = (TextView) findViewById(R.id.tv_msg_erro);

    ed_email = (EditText) findViewById(R.id.ed_email);
    ed_senha = (EditText) findViewById(R.id.ed_senha);

    bt_login = (Button) findViewById(R.id.bt_login);
    bt_cadastrar = (Button) findViewById(R.id.bt_cadastrar);

    setLayout(layout);
    setProgressBar(R.id.progress);

    presenter = new LoginPresenter(this);
   }

  private void configurarBotoes() {
   bt_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        login();
      }
    });

    bt_cadastrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        iniciarCadastro();
      }
    });
  }

  private boolean validarFormulario() {
    boolean valido = true;
    if (TextUtils.isEmpty(ed_email.getText().toString())) {
      ed_email.setError(getString(R.string.msg_campo_obrigatorio));
      valido = false;
    } else {
      ed_email.setError(null);
    }

     if (TextUtils.isEmpty(ed_senha.getText().toString())) {
      ed_senha.setError(getString(R.string.msg_campo_obrigatorio));
      valido = false;
    } else {
      ed_senha.setError(null);
    }

    return valido;
  }

  private void login() {
    if (!validarFormulario())
      return;

    String email = ed_email.getText().toString();
    String senha = ed_senha.getText().toString();
    mostrarProgresso();
    presenter.loginComFirebase(email, senha);
  }

  @Override
  public void mostrarErroLogin() {
    esconderProgresso();
    tvMsgError.setVisibility(View.VISIBLE);
  }

  private void iniciarCadastro() {
    startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
    finish();
  }

  @Override
  public void iniciarPrincipal() {
    startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
    finish();
  }

  @Override
  public void finish() {
    presenter.finish();
    super.finish();
  }
}
