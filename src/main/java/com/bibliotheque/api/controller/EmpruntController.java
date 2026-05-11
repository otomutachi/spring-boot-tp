package com.bibliotheque.api.controller;

import com.bibliotheque.api.dto.EmpruntRequest;
import com.bibliotheque.api.entity.Emprunt;
import com.bibliotheque.api.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    // GET /api/emprunts → Récupérer tous les emprunts
    @GetMapping
    public ResponseEntity<List<Emprunt>> getAllEmprunts() {
        List<Emprunt> emprunts = empruntService.getAllEmprunts();
        return ResponseEntity.ok(emprunts);
    }

    // GET /api/emprunts/{id} → Récupérer un emprunt par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        return empruntService.getEmpruntById(id)
                .map(emprunt -> ResponseEntity.ok(emprunt))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/emprunts → Créer un nouveau emprunt
    @PostMapping
    public ResponseEntity<Emprunt> createEmprunt(@RequestBody EmpruntRequest request) {
        Emprunt nouvelEmprunt = empruntService.createEmprunt(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelEmprunt);
    }

    // PUT /api/emprunts/{id} → Modifier un emprunt
    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable Long id,
                                                 @RequestBody Emprunt emprunt) {
        return empruntService.updateEmprunt(id, emprunt)
                .map(empruntModifie -> ResponseEntity.ok(empruntModifie))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/emprunts/{id} → Supprimer un emprunt
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        if (empruntService.deleteEmprunt(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}