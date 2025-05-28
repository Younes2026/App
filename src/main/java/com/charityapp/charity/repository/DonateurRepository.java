package com.charityapp.charity.repository;

import com.charityapp.charity.Entity.Donateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateurRepository extends JpaRepository<Donateur, Long> {
    // Méthodes de base fournies par JpaRepository
} 