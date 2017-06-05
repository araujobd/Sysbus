package com.dantas.bruno.sysbus.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import com.dantas.bruno.sysbus.BaseActivity;
import com.dantas.bruno.sysbus.login.LoginActivity;
import com.dantas.bruno.sysbus.MainsActiity;
import com.dantas.bruno.sysbus.R;

public class SplashActivity extends BaseActivity implements SplashContrato.View{

  private SplashPresenter presenter;
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);


    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    presenter = new SplashPresenter(this);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        verificarLogin();
      }
    }, 3000);
  }

  private void verificarLogin() {
    presenter.verificarLogin();
  }

  @Override
  public void iniciarLogin() {
    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    finish();
  }

  @Override
  public void iniciarPrincipal() {
    startActivity(new Intent(SplashActivity.this, MainsActiity.class));
    finish();
  }

}
