package com.bibliotheque.api.dto;

public class ExemplaireRequest {

    private Long livreId;
    private int numero;
    private String etat;

    public Long getLivreId() { return livreId; }
    public void setLivreId(Long livreId) { this.livreId = livreId; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
}