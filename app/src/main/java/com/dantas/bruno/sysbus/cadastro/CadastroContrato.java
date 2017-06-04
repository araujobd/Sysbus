package com.dantas.bruno.sysbus.cadastro;

/**
 * Created by bruno on 03/06/17.
 */

public interface CadastroContrato {

  interface View {
    void mostrarErroCadastro();
    void iniciarPrincipal();
  }

  interface Presenter {
    void cadastrar(String nome, String email, String senha);
  }
}
