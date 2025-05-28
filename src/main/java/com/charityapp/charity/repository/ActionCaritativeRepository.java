package com.charityapp.charity.repository;

import com.charityapp.charity.Entity.ActionCaritative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionCaritativeRepository extends JpaRepository<ActionCaritative, Long> {
    // Méthodes de base fournies par JpaRepository
    List<ActionCaritative> findTop3ByOrderByDateCreationDesc();
} 