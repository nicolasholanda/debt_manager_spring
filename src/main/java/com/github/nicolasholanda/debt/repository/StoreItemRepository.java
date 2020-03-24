package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Integer> {
}
