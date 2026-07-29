package com.fitness.tracker.api;

import com.fitness.tracker.model.Meal;
import com.fitness.tracker.repository.MealRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.*;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    private final MealRepository repository;
    public MealController(MealRepository repository) { this.repository = repository; }

    @GetMapping public List<Meal> all(@RequestParam(required = false) LocalDate date) {
        if (date == null) return repository.findAll();
        return repository.findByConsumedAtBetweenOrderByConsumedAtDesc(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }
    @GetMapping("/{id}") public Meal one(@PathVariable Long id) { return get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Meal create(@Valid @RequestBody Meal meal) { meal.setId(null); return repository.save(meal); }
    @PutMapping("/{id}") public Meal update(@PathVariable Long id, @Valid @RequestBody Meal meal) { get(id); meal.setId(id); return repository.save(meal); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { repository.delete(get(id)); }
    private Meal get(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found: " + id)); }
}
