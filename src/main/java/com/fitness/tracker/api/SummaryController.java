package com.fitness.tracker.api;

import com.fitness.tracker.model.Meal;
import com.fitness.tracker.model.Workout;
import com.fitness.tracker.repository.MealRepository;
import com.fitness.tracker.repository.WorkoutRepository;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.List;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {
    private final MealRepository meals; private final WorkoutRepository workouts;
    public SummaryController(MealRepository meals, WorkoutRepository workouts) { this.meals = meals; this.workouts = workouts; }
    @GetMapping public DailySummary daily(@RequestParam(required = false) LocalDate date) {
        if (date == null) date = LocalDate.now();
        LocalDateTime from = date.atStartOfDay(), to = date.plusDays(1).atStartOfDay();
        List<Meal> dayMeals = meals.findByConsumedAtBetweenOrderByConsumedAtDesc(from, to);
        List<Workout> dayWorkouts = workouts.findByPerformedAtBetweenOrderByPerformedAtDesc(from, to);
        int consumed = dayMeals.stream().mapToInt(Meal::getCalories).sum();
        int burned = dayWorkouts.stream().mapToInt(Workout::getCaloriesBurned).sum();
        return new DailySummary(date, consumed, burned, consumed - burned, dayMeals.size(), dayWorkouts.size());
    }
}
