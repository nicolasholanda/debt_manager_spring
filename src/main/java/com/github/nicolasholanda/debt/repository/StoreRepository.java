package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
}
