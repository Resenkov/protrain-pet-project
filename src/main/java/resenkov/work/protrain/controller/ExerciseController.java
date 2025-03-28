package resenkov.work.protrain.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resenkov.work.protrain.dto.ExerciseDTO;
import resenkov.work.protrain.service.ExerciseService;

import java.util.List;


@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PutMapping("/update")
    public ResponseEntity<ExerciseDTO> updateExercise(@Valid @RequestBody ExerciseDTO exerciseDTO) {
        if(exerciseDTO.getId() == null) {
            return new ResponseEntity("ID не должен быть равен нулю", HttpStatus.BAD_REQUEST);
        }
        if(exerciseDTO.getExerciseName() == null) {
            return new ResponseEntity("Имя упражнение не может быть пустым!", HttpStatus.BAD_REQUEST);
        }
        ExerciseDTO updatedExercise = exerciseService.updateExercise(exerciseDTO);
        return ResponseEntity.ok(updatedExercise);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
        List<ExerciseDTO> allExercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(allExercises);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<ExerciseDTO> addExercise(@Valid @RequestBody ExerciseDTO exerciseDTO) {
        if(exerciseDTO.getExerciseName() == null) {
            return new ResponseEntity("Имя упражнение не может быть пустым!", HttpStatus.BAD_REQUEST);
        }
        ExerciseDTO createdExercise = exerciseService.addExercise(exerciseDTO);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<ExerciseDTO>> findExercise(
            @RequestParam("muscleDivision") String muscleDivision) {
        List<ExerciseDTO> exercises = exerciseService.findByMuscleDivision(muscleDivision);
        return ResponseEntity.ok(exercises);
    }
}