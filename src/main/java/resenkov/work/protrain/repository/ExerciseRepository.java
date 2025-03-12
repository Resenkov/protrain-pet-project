package resenkov.work.protrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resenkov.work.protrain.entity.Exercise;
import resenkov.work.protrain.search.ExerciseSearch;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findExerciseByMuscleDivision(String muscleDivision);
    void deleteByExerciseName(String exerciseName);

    Exercise findByExerciseNameAndMuscleDivision(String exerciseName, String muscleDivision);

}