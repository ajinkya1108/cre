package com.fitness.tracker.api;

import com.fitness.tracker.model.Workout;
import com.fitness.tracker.repository.WorkoutRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.*;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    private final WorkoutRepository repository;
    public WorkoutController(WorkoutRepository repository) { this.repository = repository; }
    @GetMapping public List<Workout> all(@RequestParam(required = false) LocalDate date) {
        if (date == null) return repository.findAll();
        return repository.findByPerformedAtBetweenOrderByPerformedAtDesc(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }
    @GetMapping("/{id}") public Workout one(@PathVariable Long id) { return get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Workout create(@Valid @RequestBody Workout workout) { workout.setId(null); return repository.save(workout); }
    @PutMapping("/{id}") public Workout update(@PathVariable Long id, @Valid @RequestBody Workout workout) { get(id); workout.setId(id); return repository.save(workout); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { repository.delete(get(id)); }
    private Workout get(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout not found: " + id)); }
}
