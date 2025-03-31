package resenkov.work.protrain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resenkov.work.protrain.dto.WorkoutDTO;
import resenkov.work.protrain.dto.WorkoutDetailsDTO;
import resenkov.work.protrain.service.WorkoutService;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/train/{id}")
    public ResponseEntity<WorkoutDetailsDTO> getWorkout(@PathVariable("id") Long id) {
        WorkoutDetailsDTO dto = workoutService.getWorkoutDetails(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkoutDTO>> getAllWorkouts() {
        List<WorkoutDTO> workoutDTOS = workoutService.findAll();
        return ResponseEntity.ok(workoutDTOS);
    }

    @PostMapping("/add")
    public ResponseEntity<WorkoutDTO> addWorkout(@RequestBody WorkoutDTO workoutDTO) {
        WorkoutDTO workout = workoutService.addWorkout(workoutDTO);
        return ResponseEntity.ok(workout);
    }

    @PostMapping("/update")
    public ResponseEntity<WorkoutDTO> updateWorkout(@RequestBody WorkoutDTO workoutDTO) {
        WorkoutDTO workout = workoutService.updateWorkout(workoutDTO);
        return ResponseEntity.ok(workout);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<WorkoutDTO> deleteWorkout(@PathVariable("id") Long id) {
        workoutService.deleteWorkout(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
