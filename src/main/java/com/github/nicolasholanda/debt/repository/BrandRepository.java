package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query("SELECT b FROM Brand b WHERE b.name LIKE %?1% OR " +
            " LOWER(b.description) LIKE LOWER(?1)")
    List<Brand> find(String query);
}
