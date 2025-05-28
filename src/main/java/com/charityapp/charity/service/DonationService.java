package com.charityapp.charity.service;

import com.charityapp.charity.Entity.ActionCaritative;
import com.charityapp.charity.Entity.Donation;
import com.charityapp.charity.Entity.User;
import com.charityapp.charity.repository.ActionCaritativeRepository;
import com.charityapp.charity.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final ActionCaritativeRepository actionCaritativeRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository, 
                          ActionCaritativeRepository actionCaritativeRepository) {
        this.donationRepository = donationRepository;
        this.actionCaritativeRepository = actionCaritativeRepository;
    }

    @Transactional
    public Donation createDonation(Long actionId, BigDecimal montant, User user) {
        ActionCaritative action = actionCaritativeRepository.findById(actionId)
            .orElseThrow(() -> new RuntimeException("Action not found"));

        Donation donation = new Donation();
        donation.setAction(action);
        donation.setMontant(montant);
        donation.setUser(user);
        
        // Mettre à jour le montant collecté de l'action
        action.setMontantCollecte(action.getMontantCollecte().add(montant));
        actionCaritativeRepository.save(action);
        
        return donationRepository.save(donation);
    }

    public List<Donation> getDonationsByAction(Long actionId) {
        return donationRepository.findByActionId(actionId);
    }

    public List<Donation> getDonationsByUser(Long userId) {
        return donationRepository.findByUserId(userId);
    }
} 