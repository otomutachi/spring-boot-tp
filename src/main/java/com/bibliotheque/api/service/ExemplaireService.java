package com.bibliotheque.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.api.dto.ExemplaireRequest;
import com.bibliotheque.api.entity.Exemplaire;
import com.bibliotheque.api.entity.Livre;
import com.bibliotheque.api.repository.ExemplaireRepository;
import com.bibliotheque.api.repository.LivreRepository;

@Service
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private LivreRepository livreRepository;

    // Récupérer tous les Exemplaires
    public List<Exemplaire> getAllExemplaires() {
        return exemplaireRepository.findAll();
    }

    // Récupérer un Exemplaire par son ID
    public Optional<Exemplaire> getExemplaireById(Long id) {
        return exemplaireRepository.findById(id);
    }

    // Créer un nouveau Exemplaire
    public Exemplaire createExemplaire(ExemplaireRequest request) {
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setNumero(request.getNumero());
        exemplaire.setEtat(request.getEtat());

        if (request.getLivreId() != null) {
            Livre livre = livreRepository.findById(request.getLivreId())
                    .orElseThrow(() -> new IllegalArgumentException("Livre introuvable avec l'id : " + request.getLivreId()));
            exemplaire.setLivre(livre);
        }

        return exemplaireRepository.save(exemplaire);
    }

    // Modifier un Exemplaire existant
    public Optional<Exemplaire> updateExemplaire(Long id, Exemplaire exemplaireModifie) {
        return exemplaireRepository.findById(id).map(exemplaireExistant -> {
            exemplaireExistant.setLivre(exemplaireModifie.getLivre());
            exemplaireExistant.setNumero(exemplaireModifie.getNumero());
            exemplaireExistant.setEtat(exemplaireModifie.getEtat());
            return exemplaireRepository.save(exemplaireExistant);
        });
    }

    // Supprimer un Exemplaire
    public boolean deleteExemplaire(Long id) {
        if (exemplaireRepository.existsById(id)) {
            exemplaireRepository.deleteById(id);
            return true;
        }
        return false;
    }
}