package com.charityapp.charity.repository;

import com.charityapp.charity.Entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByActionId(Long actionId);
    List<Donation> findByUserId(Long userId);
} 