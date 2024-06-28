package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
    User getUserByEmail(String email);
}
