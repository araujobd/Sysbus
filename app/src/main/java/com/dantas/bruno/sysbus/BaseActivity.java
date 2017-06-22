package com.dantas.bruno.sysbus;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by bruno on 03/06/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

  private ViewGroup layout;
  private ProgressBar progressBar;

  public void setLayout(ViewGroup layout) {
    this.layout = layout;
  }

  public View getLayout() {
    return layout;
  }

  public void mostrarMensagem(String mensagem) {
    Snackbar.make(layout, mensagem, Snackbar.LENGTH_SHORT)
        .setAction("Action", null)
        .show();
  }

  public void setProgressBar(int id) {
    this.progressBar = (ProgressBar) findViewById(id);
  }

  public void mostrarProgresso() {
    if (progressBar != null)
      progressBar.setVisibility(View.VISIBLE);
  }

  public void esconderProgresso() {
    if (progressBar != null)
      progressBar.setVisibility(View.INVISIBLE);
  }

}
