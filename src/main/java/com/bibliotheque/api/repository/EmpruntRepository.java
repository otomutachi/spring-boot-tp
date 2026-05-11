package com.bibliotheque.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bibliotheque.api.entity.Emprunt;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}