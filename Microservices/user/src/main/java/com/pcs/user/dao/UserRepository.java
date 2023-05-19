package com.pcs.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcs.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    boolean existsByEmail(String email);
    User findByEmail(String email);
    
}
