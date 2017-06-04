package com.dantas.bruno.sysbus.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bruno on 02/06/17.
 */

public class Ponto implements Serializable {

  private String descricao;
  private ArrayList<Rota> rotas;
  private Coordenada coordenada;

}
