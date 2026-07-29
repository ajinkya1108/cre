package com.fitness.tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals")
public class Meal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Size(max = 120) private String name;
    @NotNull @Enumerated(EnumType.STRING) private MealType mealType;
    @NotNull @PositiveOrZero private Integer calories;
    @PositiveOrZero private Integer proteinGrams = 0;
    @PositiveOrZero private Integer carbohydrateGrams = 0;
    @PositiveOrZero private Integer fatGrams = 0;
    @NotNull private LocalDateTime consumedAt;
    @Size(max = 1000) private String notes;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public MealType getMealType() { return mealType; } public void setMealType(MealType mealType) { this.mealType = mealType; }
    public Integer getCalories() { return calories; } public void setCalories(Integer calories) { this.calories = calories; }
    public Integer getProteinGrams() { return proteinGrams; } public void setProteinGrams(Integer proteinGrams) { this.proteinGrams = proteinGrams; }
    public Integer getCarbohydrateGrams() { return carbohydrateGrams; } public void setCarbohydrateGrams(Integer carbohydrateGrams) { this.carbohydrateGrams = carbohydrateGrams; }
    public Integer getFatGrams() { return fatGrams; } public void setFatGrams(Integer fatGrams) { this.fatGrams = fatGrams; }
    public LocalDateTime getConsumedAt() { return consumedAt; } public void setConsumedAt(LocalDateTime consumedAt) { this.consumedAt = consumedAt; }
    public String getNotes() { return notes; } public void setNotes(String notes) { this.notes = notes; }
}
