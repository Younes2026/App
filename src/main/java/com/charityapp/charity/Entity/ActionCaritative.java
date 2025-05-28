package com.charityapp.charity.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "actions_caritatives")
public class ActionCaritative {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titre;
    private String description;
    private BigDecimal montantObjectif;
    private BigDecimal montantCollecte;
    private LocalDateTime dateCreation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String statut;
    
    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        if (statut == null) {
            statut = "EN_COURS";
        }
        if (montantCollecte == null) {
            montantCollecte = BigDecimal.ZERO;
        }
    }
    
    // Constructeurs, getters et setters générés par Lombok
} 