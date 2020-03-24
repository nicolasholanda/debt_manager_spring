package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {
}
