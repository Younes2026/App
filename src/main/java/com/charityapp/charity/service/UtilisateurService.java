package com.charityapp.charity.service;

import com.charityapp.charity.Entity.Utilisateur;
import com.charityapp.charity.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByEmail(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        // Encoder le mot de passe avant de sauvegarder
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
} 