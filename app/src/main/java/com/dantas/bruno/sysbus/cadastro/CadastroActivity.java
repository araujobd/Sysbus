package com.dantas.bruno.sysbus.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dantas.bruno.sysbus.BaseActivity;
import com.dantas.bruno.sysbus.MainsActiity;
import com.dantas.bruno.sysbus.R;

public class CadastroActivity extends BaseActivity implements CadastroContrato.View {

  private CadastroContrato.Presenter presenter;

  private ConstraintLayout layout;
  private TextView tvMsgErro;
  private EditText edNome, edEmail, edSenha;
  private Button btCadastro;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro);

    configurarTela();
    configurarBotao();
  }

  private void configurarTela() {
    layout = (ConstraintLayout) findViewById(R.id.layout);

    tvMsgErro = (TextView) findViewById(R.id.tv_msg_erro);

    edNome = (EditText) findViewById(R.id.user_nome);
    edEmail = (EditText) findViewById(R.id.user_email);
    edSenha = (EditText) findViewById(R.id.user_senha);

    btCadastro = (Button) findViewById(R.id.bt_cadastro);

    setLayout(layout);
    presenter = new CadastroPresenter(this);
  }

  private void configurarBotao() {
    btCadastro.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        cadastrar();
      }
    });
  }

  private boolean validar() {
    boolean valido = true;
    if (TextUtils.isEmpty(edNome.getText().toString())) {
      edNome.setError(getString(R.string.msg_campo_obrigatorio));
      valido = false;
    }

    if (TextUtils.isEmpty(edEmail.getText().toString())) {
      edEmail.setError(getString(R.string.msg_campo_obrigatorio));
      valido = false;
    }

    if (TextUtils.isEmpty(edSenha.getText().toString())) {
      edSenha.setError(getString(R.string.msg_campo_obrigatorio));
      valido = false;
    }

    return valido;
  }

  private void cadastrar() {
    if (!validar())
      return;

    mostrarProgresso();

    String nome = edNome.getText().toString();
    String email = edEmail.getText().toString();
    String senha = edSenha.getText().toString();
    presenter.cadastrar(nome, email, senha);
  }

  @Override
  public void mostrarErroCadastro() {
    esconderProgresso();
    tvMsgErro.setVisibility(View.VISIBLE);
    mostrarMensagem(getString(R.string.msg_erro_cadastro));
  }

  @Override
  public void iniciarPrincipal() {
    esconderProgresso();
    startActivity(new Intent(CadastroActivity.this, MainsActiity.class));
    finish();
  }
}
