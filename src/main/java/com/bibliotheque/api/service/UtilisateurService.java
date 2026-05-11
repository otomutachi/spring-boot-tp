package com.bibliotheque.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.api.dto.UtilisateurRequest;
import com.bibliotheque.api.entity.Utilisateur;
import com.bibliotheque.api.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Récupérer tous les Utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Récupérer un Utilisateur par son ID
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    // Créer un nouveau Utilisateur
    public Utilisateur createUtilisateur(UtilisateurRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setTelephone(request.getTelephone());
        return utilisateurRepository.save(utilisateur);
    }

    // Modifier un Utilisateur existant
    public Optional<Utilisateur> updateUtilisateur(Long id, Utilisateur utilisateurModifie) {
        return utilisateurRepository.findById(id).map(utilisateurExistant -> {
            utilisateurExistant.setNom(utilisateurModifie.getNom());
            utilisateurExistant.setPrenom(utilisateurModifie.getPrenom());
            utilisateurExistant.setEmail(utilisateurModifie.getEmail());
            utilisateurExistant.setTelephone(utilisateurModifie.getTelephone());
            return utilisateurRepository.save(utilisateurExistant);
        });
    }

    // Supprimer un Utilisateur
    public boolean deleteUtilisateur(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }
}