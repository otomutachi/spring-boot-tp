INSERT INTO auteurs (nom, prenom) VALUES ('Tolkien', 'J.R.R.');
INSERT INTO auteurs (nom, prenom) VALUES ('Kishimoto', 'Masashi');
INSERT INTO auteurs (nom, prenom) VALUES ('Baudelaire', 'Charles');
INSERT INTO auteurs (nom, prenom) VALUES ('Goscinny', 'René');

INSERT INTO livres (titre, isbn, categorie, auteur_id) VALUES ('Le Seigneur des Anneaux', '978-2-07-061190-3', 'ROMAN', 1);
INSERT INTO livres (titre, isbn, categorie, auteur_id) VALUES ('Naruto Vol.1', '978-2-87-128280-2', 'MANGA', 2);
INSERT INTO livres (titre, isbn, categorie, auteur_id) VALUES ('Les Fleurs du Mal', '978-2-07-036024-3', 'POESIE', 3);
INSERT INTO livres (titre, isbn, categorie, auteur_id) VALUES ('Astérix le Gaulois', '978-2-86-497001-9', 'COMEDIE', 4);

INSERT INTO utilisateur (nom, prenom, email, telephone) VALUES ('Dupont', 'Jean', 'jean.dupont@email.com', '0123456789');
INSERT INTO utilisateur (nom, prenom, email, telephone) VALUES ('Martin', 'Marie', 'marie.martin@email.com', '0987654321');

INSERT INTO exemplaire (livre_id, numero, etat) VALUES (1, 1, 'DISPONIBLE');
INSERT INTO exemplaire (livre_id, numero, etat) VALUES (1, 2, 'DISPONIBLE');
INSERT INTO exemplaire (livre_id, numero, etat) VALUES (2, 1, 'DISPONIBLE');
INSERT INTO exemplaire (livre_id, numero, etat) VALUES (3, 1, 'DISPONIBLE');

INSERT INTO emprunt (utilisateur_id, exemplaire_id, date_emprunt, date_retour_prevue) VALUES (1, 1, TIMESTAMP '2023-01-01 10:00:00', TIMESTAMP '2023-01-15 10:00:00');
INSERT INTO emprunt (utilisateur_id, exemplaire_id, date_emprunt, date_retour_prevue, date_retour_effective) VALUES (2, 2, TIMESTAMP '2023-01-05 10:00:00', TIMESTAMP '2023-01-20 10:00:00', TIMESTAMP '2023-01-18 10:00:00');