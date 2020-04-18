package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.Product;
import com.github.nicolasholanda.debt.model.StoreItem;
import com.github.nicolasholanda.debt.model.enuns.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Integer> {
    @Query(value = "SELECT DISTINCT p FROM StoreItem i " +
                    "JOIN i.id.product AS p " +
                    "JOIN i.id.store AS s " +
                    "JOIN p.categories AS c " +
                    "WHERE s.id = :storeId " +
                    "AND p.price <= :maxPrice " +
                    "AND p.brand.id IN :brands " +
                    "AND p.gender IN :genders " +
                    "AND c.id IN :categories " +
                    "ORDER BY p.name ASC")
    Page<Product> findPaginated(@Param("storeId") Integer storeId,
                                @Param("maxPrice") BigDecimal maxPrice,
                                @Param("brands") List<Integer> brands,
                                @Param("genders") List<Integer> genders,
                                @Param("categories") List<Integer> categories,
                                Pageable pageable);
}
