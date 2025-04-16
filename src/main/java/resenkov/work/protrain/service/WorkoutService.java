package resenkov.work.protrain.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import resenkov.work.protrain.dto.WorkoutDTO;
import resenkov.work.protrain.dto.WorkoutDetailsDTO;
import resenkov.work.protrain.entity.Workout;
import resenkov.work.protrain.mapping.WorkoutMapper;
import resenkov.work.protrain.repository.WorkoutRepository;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }
    public List<WorkoutDTO> findAll() {
        return workoutRepository.findAll().stream()
                .map(WorkoutMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }

    public WorkoutDTO updateWorkout(WorkoutDTO workoutDTO) {
        Workout workout = WorkoutMapper.toEntity(workoutDTO);
        workout = workoutRepository.save(workout);
        return WorkoutMapper.toDto(workout);
    }

    public WorkoutDTO addWorkout(WorkoutDTO workoutDTO) {
        Workout workout = WorkoutMapper.toEntity(workoutDTO);
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
        return dto;
    }
}