package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query(value = "SELECT DISTINCT s FROM Store s JOIN s.brands b WHERE " +
                    "LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
                    "AND (:brands) IS NULL OR b.id IN :brands")
    Page<Store> findPaginated(@Param("query") String query, @Param("brands") List<Integer> brands, Pageable pageable);
}
