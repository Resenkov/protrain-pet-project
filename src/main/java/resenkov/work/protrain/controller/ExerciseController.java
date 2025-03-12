package resenkov.work.protrain.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resenkov.work.protrain.entity.Exercise;
import resenkov.work.protrain.repository.ExerciseRepository;
import resenkov.work.protrain.search.ExerciseSearch;
import resenkov.work.protrain.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseRepository exerciseRepository;

    public ExerciseController(ExerciseService exerciseService, ExerciseRepository exerciseRepository) {
        this.exerciseService = exerciseService;
        this.exerciseRepository = exerciseRepository;
    }

    @PostMapping("/all")
    List<Exercise> getAll() {
        return exerciseService.findAll();
    }

    @PostMapping("/allDivision")
    List<Exercise> getByDivision(@RequestBody String division) {
        return exerciseService.findByMuscleDivision(division);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody String exerciseName) {
        exerciseService.deleteExercise(exerciseName);
    }

    @PostMapping("/add")
    public ResponseEntity<Exercise> add(@RequestBody Exercise exercise) {
        if(exercise.getExerciseName() == null || exercise.getExerciseName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(exerciseService.addExercise(exercise));
    }

    @PutMapping("/update")
    public ResponseEntity<Exercise> update(@RequestBody ExerciseSearch exerciseSearch) {
        if(exerciseSearch.getExerciseName() == null || exerciseSearch.getExerciseName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(exerciseSearch.getOldDivision() == null || exerciseSearch.getOldDivision().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Exercise exercise = exerciseRepository.findByExerciseNameAndMuscleDivision(exerciseSearch.getExerciseName(), exerciseSearch.getOldDivision());
        exercise.setMuscleDivision(exerciseSearch.getNewDivision());

        exerciseService.updateExercise(exercise);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
