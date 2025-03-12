package resenkov.work.protrain.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import resenkov.work.protrain.entity.Exercise;
import resenkov.work.protrain.repository.ExerciseRepository;
import resenkov.work.protrain.search.ExerciseSearch;

import java.util.List;

@Service
@Transactional

public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public List<Exercise> findByMuscleDivision(String muscleDivision) {
        return exerciseRepository.findExerciseByMuscleDivision(muscleDivision);
    }

    public Exercise addExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(String exerciseName) {
        exerciseRepository.deleteByExerciseName(exerciseName);
    }
}
