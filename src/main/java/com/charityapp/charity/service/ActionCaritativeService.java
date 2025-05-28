package com.charityapp.charity.service;

import com.charityapp.charity.Entity.ActionCaritative;
import com.charityapp.charity.repository.ActionCaritativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionCaritativeService {

    private final ActionCaritativeRepository actionCaritativeRepository;

    @Autowired
    public ActionCaritativeService(ActionCaritativeRepository actionCaritativeRepository) {
        this.actionCaritativeRepository = actionCaritativeRepository;
    }

    public List<ActionCaritative> getAllActions() {
        return actionCaritativeRepository.findAll();
    }

    public Optional<ActionCaritative> getActionById(Long id) {
        return actionCaritativeRepository.findById(id);
    }

    public ActionCaritative saveAction(ActionCaritative action) {
        return actionCaritativeRepository.save(action);
    }

    public void deleteAction(Long id) {
        actionCaritativeRepository.deleteById(id);
    }

    public List<ActionCaritative> getLatestActions(int limit) {
        return actionCaritativeRepository.findTop3ByOrderByDateCreationDesc();
    }
} 