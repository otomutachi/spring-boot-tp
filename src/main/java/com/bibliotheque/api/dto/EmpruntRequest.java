package com.bibliotheque.api.dto;

import java.time.LocalDateTime;

public class EmpruntRequest {

    private Long utilisateurId;
    private Long exemplaireId;
    private LocalDateTime dateEmprunt;
    private LocalDateTime dateRetourPrevue;

    public Long getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(Long utilisateurId) { this.utilisateurId = utilisateurId; }

    public Long getExemplaireId() { return exemplaireId; }
    public void setExemplaireId(Long exemplaireId) { this.exemplaireId = exemplaireId; }

    public LocalDateTime getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(LocalDateTime dateEmprunt) { this.dateEmprunt = dateEmprunt; }

    public LocalDateTime getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(LocalDateTime dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
}