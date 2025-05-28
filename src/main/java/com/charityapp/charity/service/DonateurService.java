package com.charityapp.charity.service;

import com.charityapp.charity.Entity.Donateur;
import com.charityapp.charity.repository.DonateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonateurService {

    private final DonateurRepository donateurRepository;

    @Autowired
    public DonateurService(DonateurRepository donateurRepository) {
        this.donateurRepository = donateurRepository;
    }

    public List<Donateur> getAllDonateurs() {
        return donateurRepository.findAll();
    }

    public Optional<Donateur> getDonateurById(Long id) {
        return donateurRepository.findById(id);
    }

    public Donateur saveDonateur(Donateur donateur) {
        return donateurRepository.save(donateur);
    }

    public void deleteDonateur(Long id) {
        donateurRepository.deleteById(id);
    }
} 