package resenkov.work.protrain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resenkov.work.protrain.entity.DifficultyLevel;
import resenkov.work.protrain.repository.DifficultyRepository;

import java.util.List;


@Service
@Transactional
public class DifficultyService {
    DifficultyRepository difficultyRepo;

    public DifficultyService(DifficultyRepository difficultyRepo) {
        this.difficultyRepo = difficultyRepo;
    }

    public List<DifficultyLevel> findAll() {
        return difficultyRepo.findAll();
    }

    public void deleteDifficulty(Long id) {
        difficultyRepo.deleteById(id);
    }

    public DifficultyLevel updateDifficulty(DifficultyLevel difficultyLevel) {
        return difficultyRepo.save(difficultyLevel);
    }

    public DifficultyLevel addDifficulty(DifficultyLevel difficultyLevel) {
        return difficultyRepo.save(difficultyLevel);
    }
}
