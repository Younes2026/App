package com.charityapp.charity.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "donateurs")
public class Donateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    
    // Constructeurs, getters et setters générés par Lombok
} 