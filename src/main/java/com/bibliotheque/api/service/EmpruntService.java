package com.bibliotheque.api.service;

import com.bibliotheque.api.dto.EmpruntRequest;
import com.bibliotheque.api.entity.Emprunt;
import com.bibliotheque.api.entity.Exemplaire;
import com.bibliotheque.api.entity.Utilisateur;
import com.bibliotheque.api.repository.EmpruntRepository;
import com.bibliotheque.api.repository.ExemplaireRepository;
import com.bibliotheque.api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    // Récupérer tous les Emprunts
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    // Récupérer un Emprunt par son ID
    public Optional<Emprunt> getEmpruntById(Long id) {
        return empruntRepository.findById(id);
    }

    // Créer un nouveau Emprunt
    public Emprunt createEmprunt(EmpruntRequest request) {
        Emprunt emprunt = new Emprunt();
        emprunt.setDateEmprunt(request.getDateEmprunt());
        emprunt.setDateRetourPrevue(request.getDateRetourPrevue());

        if (request.getUtilisateurId() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(request.getUtilisateurId())
                    .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'id : " + request.getUtilisateurId()));
            emprunt.setUtilisateur(utilisateur);
        }

        if (request.getExemplaireId() != null) {
            Exemplaire exemplaire = exemplaireRepository.findById(request.getExemplaireId())
                    .orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable avec l'id : " + request.getExemplaireId()));
            if (!"DISPONIBLE".equals(exemplaire.getEtat())) {
                throw new IllegalArgumentException("Exemplaire non disponible pour emprunt");
            }
            exemplaire.setEtat("EMPRUNTE");
            exemplaireRepository.save(exemplaire);
            emprunt.setExemplaire(exemplaire);
        }

        return empruntRepository.save(emprunt);
    }

    // Modifier un Emprunt existant
    public Optional<Emprunt> updateEmprunt(Long id, Emprunt empruntModifie) {
        return empruntRepository.findById(id).map(empruntExistant -> {
            empruntExistant.setUtilisateur(empruntModifie.getUtilisateur());
            empruntExistant.setExemplaire(empruntModifie.getExemplaire());
            empruntExistant.setDateEmprunt(empruntModifie.getDateEmprunt());
            empruntExistant.setDateRetourPrevue(empruntModifie.getDateRetourPrevue());
            empruntExistant.setDateRetourEffective(empruntModifie.getDateRetourEffective());

            // Si dateRetourEffective est définie, marquer l'exemplaire comme disponible
            if (empruntModifie.getDateRetourEffective() != null && empruntExistant.getDateRetourEffective() == null) {
                Exemplaire exemplaire = empruntExistant.getExemplaire();
                if (exemplaire != null) {
                    exemplaire.setEtat("DISPONIBLE");
                    exemplaireRepository.save(exemplaire);
                }
            }

            return empruntRepository.save(empruntExistant);
        });
    }

    // Supprimer un Emprunt
    public boolean deleteEmprunt(Long id) {
        if (empruntRepository.existsById(id)) {
            empruntRepository.deleteById(id);
            return true;
        }
        return false;
    }
}