package com.pcs.compensation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcs.compensation.model.Compensation;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Integer> {
    
}
