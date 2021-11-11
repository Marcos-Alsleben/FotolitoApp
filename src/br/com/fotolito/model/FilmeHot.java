/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fotolito.model;

/**
 *
 * @author gmg
 */
public class FilmeHot {
    
    // Atributos    
  private int id;
  private String rp;
  private String cliente;
  private int fichaTecnica;
  private String faca;
  private String turno;
  private String ocorrencia;
  private String obs;
  
  //Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(int fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public String getFaca() {
        return faca;
    }

    public void setFaca(String faca) {
        this.faca = faca;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
