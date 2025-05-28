package com.charityapp.charity.controller;

import com.charityapp.charity.Entity.ActionCaritative;
import com.charityapp.charity.Entity.Donation;
import com.charityapp.charity.Entity.User;
import com.charityapp.charity.service.ActionCaritativeService;
import com.charityapp.charity.service.DonationService;
import com.charityapp.charity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/actions")
public class ActionCaritativeController {

    private final ActionCaritativeService actionCaritativeService;
    private final DonationService donationService;
    private final UserService userService;

    @Autowired
    public ActionCaritativeController(ActionCaritativeService actionCaritativeService,
                                    DonationService donationService,
                                    UserService userService) {
        this.actionCaritativeService = actionCaritativeService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @GetMapping
    public String listActions(Model model) {
        model.addAttribute("actions", actionCaritativeService.getAllActions());
        model.addAttribute("newAction", new ActionCaritative());
        return "actions";
    }

    @PostMapping
    public String createAction(@ModelAttribute("newAction") ActionCaritative action,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        try {
            // Initialiser les valeurs par défaut
            action.setDateCreation(LocalDateTime.now());
            action.setDateDebut(LocalDateTime.now());
            action.setMontantCollecte(BigDecimal.ZERO);
            action.setStatut("EN_COURS");

            // Sauvegarder l'action
            actionCaritativeService.saveAction(action);
            redirectAttributes.addFlashAttribute("success", "L'action a été créée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenue lors de la création de l'action.");
        }
        return "redirect:/actions";
    }

    @GetMapping("/{id}")
    public String viewAction(@PathVariable Long id, Model model) {
        ActionCaritative action = actionCaritativeService.getActionById(id)
            .orElseThrow(() -> new RuntimeException("Action not found"));
        model.addAttribute("action", action);
        model.addAttribute("donations", donationService.getDonationsByAction(id));
        return "actions/view";
    }

    @PostMapping("/{id}/don")
    public String makeDonation(@PathVariable Long id,
                             @RequestParam BigDecimal montant,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByEmail(authentication.getName());
            Donation donation = donationService.createDonation(id, montant, user);
            redirectAttributes.addFlashAttribute("success", 
                "Merci pour votre don de " + montant + " € !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Une erreur est survenue lors du traitement de votre don.");
        }
        return "redirect:/actions/" + id;
    }
} 