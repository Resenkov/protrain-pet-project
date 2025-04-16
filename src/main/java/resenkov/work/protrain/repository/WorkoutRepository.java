package resenkov.work.protrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import resenkov.work.protrain.entity.Workout;
import resenkov.work.protrain.entity.User;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("SELECT w FROM Workout w " +
            "LEFT JOIN FETCH w.exercises we " +
            "LEFT JOIN FETCH we.exercise " +
            "WHERE w.id = :workoutId")
    Optional<Workout> findByIdWithExercises(@Param("workoutId") Long workoutId);

    List<Workout> findByUser(User user);
}
