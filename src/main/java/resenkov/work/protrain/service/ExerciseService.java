package resenkov.work.protrain.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import resenkov.work.protrain.dto.ExerciseDTO;
import resenkov.work.protrain.entity.Exercise;
import resenkov.work.protrain.mapping.ExerciseMapper;
import resenkov.work.protrain.repository.ExerciseRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll().stream()
                .map(ExerciseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ExerciseDTO> findByMuscleDivision(String muscleDivision) {
        return exerciseRepository.findExerciseByMuscleDivision(muscleDivision).stream()
                .map(ExerciseMapper::toDTO).collect(Collectors.toList());
    }

    public ExerciseDTO addExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = ExerciseMapper.toEntity(exerciseDTO);
        exercise = exerciseRepository.save(exercise);
        return ExerciseMapper.toDTO(exercise);
    }

    public ExerciseDTO updateExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = ExerciseMapper.toEntity(exerciseDTO);
        exercise = exerciseRepository.save(exercise);
        return ExerciseMapper.toDTO(exercise);
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
