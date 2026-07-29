package com.fitness.tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Size(max = 120) private String name;
    @NotBlank @Size(max = 60) private String category;
    @NotNull @Positive private Integer durationMinutes;
    @NotNull @PositiveOrZero private Integer caloriesBurned;
    @NotNull private LocalDateTime performedAt;
    @Size(max = 1000) private String notes;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getCategory() { return category; } public void setCategory(String category) { this.category = category; }
    public Integer getDurationMinutes() { return durationMinutes; } public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public Integer getCaloriesBurned() { return caloriesBurned; } public void setCaloriesBurned(Integer caloriesBurned) { this.caloriesBurned = caloriesBurned; }
    public LocalDateTime getPerformedAt() { return performedAt; } public void setPerformedAt(LocalDateTime performedAt) { this.performedAt = performedAt; }
    public String getNotes() { return notes; } public void setNotes(String notes) { this.notes = notes; }
}
