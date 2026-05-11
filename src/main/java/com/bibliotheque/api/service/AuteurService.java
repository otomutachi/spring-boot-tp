package com.bibliotheque.api.service;

import com.bibliotheque.api.entity.Auteur;
import com.bibliotheque.api.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service    // Indique que cette classe est un service Spring
public class AuteurService {

    @Autowired    // Spring injecte automatiquement le repository
    private AuteurRepository auteurRepository;

    // Récupérer tous les Auteurs
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }

    // Récupérer un Auteur par son ID
    public Optional<Auteur> getAuteurById(Long id) {
        return auteurRepository.findById(id);
    }

    // Créer un nouveau Auteur
    public Auteur createAuteur(Auteur Auteur) {
        // Règle métier : l'ISBN doit être unique
        // (vous pouvez ajouter des validations ici)
        return auteurRepository.save(Auteur);
    }

    // Modifier un Auteur existant
    public Optional<Auteur> updateAuteur(Long id, Auteur auteurModifie) {
        return auteurRepository.findById(id).map(auteurExistant -> {
            auteurExistant.setNom(auteurModifie.getNom());
            auteurExistant.setPrenom(auteurModifie.getPrenom());
            return auteurRepository.save(auteurExistant);
        });
    }

    // Supprimer un Auteur
    public boolean deleteAuteur(Long id) {
        if (auteurRepository.existsById(id)) {
            auteurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Rechercher des Auteurs par titre
    public List<Auteur> searchByNom(String nom) {
        return auteurRepository.findByNomContainingIgnoreCase(nom);
    }
}