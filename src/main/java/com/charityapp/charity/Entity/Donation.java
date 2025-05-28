package com.charityapp.charity.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "donations")
public class Donation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "action_id", nullable = false)
    private ActionCaritative action;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private BigDecimal montant;
    private LocalDateTime dateDon;
    private String statut; // EN_ATTENTE, VALIDE, ANNULE
    
    @PrePersist
    protected void onCreate() {
        dateDon = LocalDateTime.now();
        if (statut == null) {
            statut = "EN_ATTENTE";
        }
    }
} 