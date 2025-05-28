package com.charityapp.charity.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "role")
    private String role = "USER"; // Par défaut, tous les utilisateurs ont le rôle USER
} 