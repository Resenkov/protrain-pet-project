package resenkov.work.protrain.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import resenkov.work.protrain.dto.WorkoutDTO;
import resenkov.work.protrain.dto.WorkoutDetailsDTO;
import resenkov.work.protrain.entity.User;
import resenkov.work.protrain.entity.Workout;
import resenkov.work.protrain.mapping.WorkoutMapper;
import resenkov.work.protrain.repository.WorkoutRepository;
import resenkov.work.protrain.repository.DifficultyRepository;
import resenkov.work.protrain.repository.TypeRepository;
import resenkov.work.protrain.entity.DifficultyLevel;
import resenkov.work.protrain.entity.WorkoutType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final TypeRepository typeRepository;
    private final DifficultyRepository difficultyRepository;
    private final UserService userService;

    public WorkoutService(WorkoutRepository workoutRepository, TypeRepository typeRepository, DifficultyRepository difficultyRepository, UserService userService) {
        this.workoutRepository = workoutRepository;
        this.typeRepository = typeRepository;
        this.difficultyRepository = difficultyRepository;
        this.userService = userService;
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }

    public WorkoutDTO updateWorkout(WorkoutDTO workoutDTO, String userEmail) {
        Workout workout = WorkoutMapper.toEntity(workoutDTO);

        if (workoutDTO.getWorkoutTypeId() != null) {
            Optional<WorkoutType> workoutType = typeRepository.findById(workoutDTO.getWorkoutTypeId());
            workoutType.ifPresent(workout::setWorkoutType);
        } else {
            workout.setWorkoutType(null);
        }

        if (workoutDTO.getDifficultyLevelId() != null) {
            Optional<DifficultyLevel> difficultyLevel = difficultyRepository.findById(workoutDTO.getDifficultyLevelId());
            difficultyLevel.ifPresent(workout::setDifficultyLevel);
        } else {
            workout.setDifficultyLevel(null);
        }

        User user = userService.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        workout.setUser(user);

        workout = workoutRepository.save(workout);
        return WorkoutMapper.toDto(workout);
    }

    public WorkoutDTO addWorkout(WorkoutDTO workoutDTO, String userEmail) {
        Workout workout = WorkoutMapper.toEntity(workoutDTO);

        if (workoutDTO.getWorkoutTypeId() != null) {
            Optional<WorkoutType> workoutType = typeRepository.findById(workoutDTO.getWorkoutTypeId());
            workoutType.ifPresent(workout::setWorkoutType);
        }

        if (workoutDTO.getDifficultyLevelId() != null) {
            Optional<DifficultyLevel> difficultyLevel = difficultyRepository.findById(workoutDTO.getDifficultyLevelId());
            difficultyLevel.ifPresent(workout::setDifficultyLevel);
        }

        User user = userService.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        workout.setUser(user);

        workout = workoutRepository.save(workout);
        return WorkoutMapper.toDto(workout);
    }




    public WorkoutDetailsDTO getWorkoutDetails(Long workoutId) {
        Workout workout = workoutRepository.findByIdWithExercises(workoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Тренировка не найдена"));
        return mapToDto(workout);
    }

    private WorkoutDetailsDTO mapToDto(Workout workout) {
        WorkoutDetailsDTO dto = new WorkoutDetailsDTO();
        dto.setId(workout.getId());
        dto.setWorkoutDate(workout.getWorkoutDate());
        dto.setNotes(workout.getNotes());

        List<WorkoutDetailsDTO.ExerciseDTO> exercises = workout.getExercises().stream()
                .map(ex -> new WorkoutDetailsDTO.ExerciseDTO(
                        ex.getExercise().getExerciseName(),
                        ex.getSets(),
                        ex.getReps(),
                        ex.getWeightKg(),
                        ex.getExercise().getMuscleDivision()
                ))
                .collect(Collectors.toList());
        dto.setExercises(exercises);

        if (workout.getWorkoutType() != null) {
            dto.setWorkoutTypeName(workout.getWorkoutType().getTypeName());
        }
        if (workout.getDifficultyLevel() != null) {
            dto.setDifficultyLevelName(workout.getDifficultyLevel().getLevelName());
        }

        return dto;
    }

    public List<WorkoutDTO> getWorkoutsByUser(User user) {
        List<Workout> workouts = workoutRepository.findByUser(user);
        return workouts.stream()
                .map(WorkoutMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<WorkoutType> getAllWorkoutTypes() {
        return typeRepository.findAll();
    }

    public List<DifficultyLevel> getAllDifficultyLevels() {
        return difficultyRepository.findAll();
    }
}
