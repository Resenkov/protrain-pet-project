package resenkov.work.protrain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import resenkov.work.protrain.dto.WorkoutDTO;
import resenkov.work.protrain.dto.WorkoutDetailsDTO;
import resenkov.work.protrain.entity.User;
import resenkov.work.protrain.service.UserService;
import resenkov.work.protrain.service.WorkoutService;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    @GetMapping("/user/name")
    public ResponseEntity<String> getCurrentUserName(@AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        return ResponseEntity.ok(userDetails.getUsername());
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "user/profile";
    }

    @PostMapping("/train/{id}")
    public ResponseEntity<WorkoutDetailsDTO> getWorkout(@PathVariable("id") Long id) {
        WorkoutDetailsDTO dto = workoutService.getWorkoutDetails(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/train/{id}")
    public ResponseEntity<WorkoutDetailsDTO> getWorkoutGet(@PathVariable("id") Long id) {
        WorkoutDetailsDTO dto = workoutService.getWorkoutDetails(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/add")
    public ResponseEntity<WorkoutDTO> addWorkout(@RequestBody WorkoutDTO workoutDTO, @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        WorkoutDTO workout = workoutService.addWorkout(workoutDTO, userDetails.getUsername());
        return ResponseEntity.ok(workout);
    }

    @PostMapping("/update")
    public ResponseEntity<WorkoutDTO> updateWorkout(@RequestBody WorkoutDTO workoutDTO, @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        WorkoutDTO workout = workoutService.updateWorkout(workoutDTO, userDetails.getUsername());
        return ResponseEntity.ok(workout);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("id") Long id) {
        workoutService.deleteWorkout(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/my")
    public ResponseEntity<List<WorkoutDTO>> getMyWorkoutsGet(@AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        List <WorkoutDTO> workouts = workoutService.getWorkoutsByUser(user);
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/types")
    public ResponseEntity<List<?>> getWorkoutTypes() {
        List<?> types = workoutService.getAllWorkoutTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/difficulties")
    public ResponseEntity<List<?>> getDifficultyLevels() {
        List<?> difficulties = workoutService.getAllDifficultyLevels();
        return ResponseEntity.ok(difficulties);
    }
}
