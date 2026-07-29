package com.fitness.tracker.repository;

import com.fitness.tracker.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByConsumedAtBetweenOrderByConsumedAtDesc(LocalDateTime from, LocalDateTime to);
}
