package resenkov.work.protrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import resenkov.work.protrain.entity.DifficultyLevel;

import java.util.List;

@Repository
public interface DifficultyRepository extends JpaRepository<DifficultyLevel, Long> {

    List <DifficultyLevel> findAll();

}
