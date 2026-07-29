package com.fitness.tracker.api;

import java.time.LocalDate;

public record DailySummary(LocalDate date, int caloriesConsumed, int caloriesBurned, int netCalories,
                           int mealCount, int workoutCount) { }
