package com.charityapp.charity.controller;

import com.charityapp.charity.Entity.Utilisateur;
import com.charityapp.charity.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String nom,
                         @RequestParam String prenom,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String confirmPassword,
                         RedirectAttributes redirectAttributes) {
        
        // Vérifier si les mots de passe correspondent
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Les mots de passe ne correspondent pas");
            return "redirect:/register";
        }

        // Vérifier si l'email existe déjà
        if (utilisateurService.existsByEmail(email)) {
            redirectAttributes.addFlashAttribute("error", "Cet email est déjà utilisé");
            return "redirect:/register";
        }

        // Créer le nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(password); // Le service s'occupera du hachage

        try {
            utilisateurService.save(utilisateur);
            redirectAttributes.addFlashAttribute("success", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenue lors de l'inscription");
            return "redirect:/register";
        }
    }
} 