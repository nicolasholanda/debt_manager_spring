package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.DemandItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandItemRepository extends JpaRepository<DemandItem, Integer> {
}
