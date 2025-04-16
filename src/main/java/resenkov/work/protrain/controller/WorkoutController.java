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

    @GetMapping("/profile")
    public String profilePage() {
        return "user/profile";
    }

    @GetMapping("/train/{id}")
    public ResponseEntity<WorkoutDetailsDTO> getWorkoutGet(@PathVariable("id") Long id) {
        WorkoutDetailsDTO dto = workoutService.getWorkoutDetails(id);
        return ResponseEntity.ok(dto);
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

    @GetMapping("/my")
    public ResponseEntity<List<WorkoutDTO>> getMyWorkoutsGet(@AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        String email = userDetails.getUsername();
        System.out.println("Получен GET запрос на тренировки пользователя: " + email);
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        List<WorkoutDTO> workouts = workoutService.getWorkoutsByUser(user);
        System.out.println("Найдено тренировок (GET): " + workouts.size());
        return ResponseEntity.ok(workouts);
    }
}
