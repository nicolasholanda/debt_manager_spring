package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    @Transactional(readOnly = true)
    Optional<ApplicationUser> findByEmail(String email);
}
