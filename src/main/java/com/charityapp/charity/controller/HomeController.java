package com.charityapp.charity.controller;

import com.charityapp.charity.service.ActionCaritativeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ActionCaritativeService actionCaritativeService;

    public HomeController(ActionCaritativeService actionCaritativeService) {
        this.actionCaritativeService = actionCaritativeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Récupérer les 3 dernières actions caritatives
        model.addAttribute("actions", actionCaritativeService.getLatestActions(3));
        return "index";
    }
} 