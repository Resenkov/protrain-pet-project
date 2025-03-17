package resenkov.work.protrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import resenkov.work.protrain.entity.WorkoutType;


@Repository
public interface TypeRepository extends JpaRepository<WorkoutType, Long> {

}
