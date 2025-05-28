package com.charityapp.charity.controller;

import com.charityapp.charity.service.ActionCaritativeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/don")
public class DonController {

    private final ActionCaritativeService actionCaritativeService;

    public DonController(ActionCaritativeService actionCaritativeService) {
        this.actionCaritativeService = actionCaritativeService;
    }

    @GetMapping
    public String donPage(Model model) {
        // Récupérer toutes les actions caritatives actives
        model.addAttribute("actions", actionCaritativeService.getAllActions());
        return "don";
    }
} 