package com.bibliotheque.api.controller;

import com.bibliotheque.api.entity.Auteur;
import com.bibliotheque.api.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController               // Indique que cette classe est un controller REST
@RequestMapping("/api/auteurs") // Préfixe de toutes les routes de ce controller
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    // GET /api/Auteurs → Récupérer tous les Auteurs
    @GetMapping
    public ResponseEntity<List<Auteur>> getAllAuteurs() {
        List<Auteur> auteurs = auteurService.getAllAuteurs();
        return ResponseEntity.ok(auteurs);    // HTTP 200 + liste des Auteurs en JSON
    }

    // GET /api/Auteurs/{id} → Récupérer un Auteur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Long id) {
        return auteurService.getAuteurById(id)
                .map(auteur -> ResponseEntity.ok(auteur))           // HTTP 200 si trouvé
                .orElse(ResponseEntity.notFound().build());       // HTTP 404 si non trouvé
    }

    // POST /api/Auteurs → Créer un nouveau Auteur
    @PostMapping
    public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur Auteur) {
        Auteur nouveauAuteur = auteurService.createAuteur(Auteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauAuteur);  // HTTP 201
    }

    // PUT /api/Auteurs/{id} → Modifier un Auteur
    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Long id,
                                              @RequestBody Auteur auteur) {
        return auteurService.updateAuteur(id, auteur)
                .map(auteurModifie -> ResponseEntity.ok(auteurModifie))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/Auteurs/{id} → Supprimer un Auteur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Long id) {
        if (auteurService.deleteAuteur(id)) {
            return ResponseEntity.noContent().build();    // HTTP 204 : succès sans contenu
        }
        return ResponseEntity.notFound().build();         // HTTP 404 si non trouvé
    }

    // GET /api/Auteurs/search?titre=harry → Rechercher par titre
    @GetMapping("/search")
    public ResponseEntity<List<Auteur>> searchByNom(@RequestParam String nom) {
        List<Auteur> auteurs = auteurService.searchByNom(nom);
        return ResponseEntity.ok(auteurs);
    }
}