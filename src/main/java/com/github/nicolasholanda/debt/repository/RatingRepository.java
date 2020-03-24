package com.github.nicolasholanda.debt.repository;

import com.github.nicolasholanda.debt.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
